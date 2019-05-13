package me.szp.framework.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import me.szp.framework.core.io.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从指定的类路径下加载xml配置文件得到InputStream
 *
 * @author GhostDog
 */
public class ClassPathResource implements Resource {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * xml配置文件的路径
     */
    private String path;

    /**
     * 类
     */
    private Class<?> clazz;

    /**
     * 类加载器
     */
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, null);
    }

    public ClassPathResource(String path, Class<?> clazz) {
        this(path, clazz, null);
    }

    public ClassPathResource(String path, Class<?> clazz, ClassLoader classLoader) {
        super();
        this.path = path;
        this.clazz = clazz;
        this.classLoader = classLoader;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (StringUtils.isNotBlank(path)) {
            if (this.clazz != null) {
                return this.clazz.getResourceAsStream(path);
            }

            if (this.classLoader != null) {
                return this.classLoader.getResourceAsStream(path.startsWith("/") ? path.substring(1) : path);
            }

            return this.getClass().getResourceAsStream(path);
        }
        return null;
    }

    @Override
    public boolean exists() {
        if (StringUtils.isNotBlank(path)) {
            if (this.clazz != null) {
                return this.clazz.getResource(path) != null;
            }

            if (this.classLoader != null) {
                return this.classLoader.getResource(path.startsWith("/") ? path.substring(1) : path) != null;
            }

            return this.getClass().getResource(path) != null;
        }
        return false;
    }

    @Override
    public boolean isReadable() {
        return exists();
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public File getFile() {
        if (logger.isDebugEnabled()) {
            logger.debug("资源文件：[{}]", path);
            logger.debug("根目录:[{}]", Objects.requireNonNull(this.getClass().getResource("/")));
        }

        return new File(this.getClass().getResource(path).getFile());
    }

}
