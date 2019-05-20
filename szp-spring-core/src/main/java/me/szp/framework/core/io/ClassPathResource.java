package me.szp.framework.core.io;

import java.io.File;
import java.io.FileInputStream;
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
        return new FileInputStream(getFile());
    }

    @Override
    public boolean exists() {
        return getFile() != null;
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
        if (StringUtils.isNotBlank(path)) {
            if (this.clazz != null) {
                return new File(this.clazz.getResource(path).getFile());
            }

            //如果classLoader不为空，则对URL进行处理
            if (this.classLoader != null) {
                return new File(Objects.requireNonNull(this.classLoader.getResource(path.startsWith("/") ? path.substring(1) : path)).getFile());
            }

            return new File(this.getClass().getResource(path).getFile());
        }
        return null;
    }

}
