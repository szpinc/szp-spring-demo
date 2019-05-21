package me.szp.framework.spring.context;

import me.szp.framework.core.io.FileSystemResource;
import me.szp.framework.core.io.Resource;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionReader;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.PathMatcher;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * 扫描指定包下的类(包含子孙包下的类),
 * 通过反射获取bean定义信息、创建bean定义对象、注册bean定义对象到bean工厂
 *
 * @author GhostDog
 */
public class ClassPathBeanDefinitionScanner {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * bean定义注册器
     */
    private BeanDefinitionRegistry registry;

    /**
     * bean定义读取器
     */
    private BeanDefinitionReader reader;

    private PathMatcher pathMatcher = new AntPathMatcher();

    private String resourcePatter = "**/*.class";

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.reader = new AnnotationBeanDefinitionReader(this.registry);
    }

    /**
     * 扫描加载bean定义
     *
     * @param basePackages
     * @throws Throwable
     */
    public void scan(String... basePackages) throws Throwable {
        if (basePackages != null && basePackages.length > 0) {
            for (String p : basePackages) {
                this.reader.loadBeanDefinitions(this.doScan(p));
            }
        }
    }

    /**
     * 扫描指定包下的类得到Resource
     *
     * @param basePackage
     * @return
     * @throws IOException
     */
    private Resource[] doScan(String basePackage) throws IOException {
        if (logger.isDebugEnabled()) {
            logger.debug("开始扫描包:[{}]", basePackage);
        }
        // 扫描包下的类
        // 构造初步匹配模式串，= 给入的包串 + / + **/*.class，替换里面的.为/
        String rootPath = "/" + StringUtils.replace(basePackage, ".", "/") + "/";

        if (logger.isDebugEnabled()) {
            logger.debug("根目录:[{}]", rootPath);
        }

        File rootDir = new File(this.getClass().getResource(rootPath).getFile());
        // 存放找到的类文件的resource集合
        Set<Resource> scanedClassFileResources = new HashSet<>();
        // 调用doRetrieveMatchingFiles来扫描class文件
        this.doRetrieveMatchingFiles(rootDir, scanedClassFileResources);

        Resource[] resources = new Resource[scanedClassFileResources.size()];

        return scanedClassFileResources.toArray(resources);
    }

    private String determineRootDir(String location) {
        int rootDirEnd = location.length();
        rootDirEnd = location.indexOf('*');
        int zi = location.indexOf('?');
        if (zi != -1 && zi < rootDirEnd) {
            rootDirEnd = location.lastIndexOf('/', zi);
        }
        if (rootDirEnd != -1) {
            return location.substring(0, rootDirEnd);
        } else {
            return location;
        }
    }

    /**
     * 递归找指定目录下的所有类，匹配模式的加入到结果中。
     *
     * @param dir
     * @param result
     * @throws IOException
     */
    protected void doRetrieveMatchingFiles(File dir, Set<Resource> result) throws IOException {

        for (File content : listDirectory(dir)) {
            String currPath = StringUtils.replace(content.getAbsolutePath(), File.separator, "/");
            if (content.isDirectory()) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("跳过目录扫描[{}]，当前目录不可读" + dir.getAbsolutePath());
                    }
                } else {
                    doRetrieveMatchingFiles(content, result);
                }
            }

            if (isClassFile(content)) {
                logger.debug("扫描到的类：[{}]", content.getName());
                result.add(new FileSystemResource(content));
            }
        }
    }


    private boolean isClassFile(File file) {
        return file.getName().endsWith(".class");
    }

    protected File[] listDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {
            if (logger.isInfoEnabled()) {
                logger.info("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return new File[0];
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        return files;
    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public BeanDefinitionReader getReader() {
        return reader;
    }

    public void setReader(BeanDefinitionReader reader) {
        this.reader = reader;
    }

    public PathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(PathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public String getResourcePatter() {
        return resourcePatter;
    }

    public void setResourcePatter(String resourcePatter) {
        this.resourcePatter = resourcePatter;
    }
}
