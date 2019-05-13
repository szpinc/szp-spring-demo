package me.szp.framework.spring.context;

import me.szp.framework.core.io.ClassPathResource;
import me.szp.framework.core.io.FileSystemResource;
import me.szp.framework.core.io.Resource;
import me.szp.framework.core.io.UrlResource;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionReader;
import me.szp.framework.spring.beans.factory.support.BeanDefinitionRegistry;
import me.szp.framework.spring.context.support.AbstractApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载xml配置文件得到Resource,
 * 然后bean定义读取器BeanDefinitionReader从Resource里面加载bean定义注册到bean工厂
 *
 * @author GhostDog
 */
public class XmlApplicationContext extends AbstractApplicationContext {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 加载xml配置文件的产物Resource
     */
    private List<Resource> resources;

    public XmlApplicationContext(String... location) throws Throwable {
        super();
        //加载resources
        load(location);
        //bean定义读取器从Resource里面加载bean定义
        BeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) this.beanFactory);
        Resource[] resourceArray = new Resource[resources.size()];
        reader.loadBeanDefinitions(resources.toArray(resourceArray));

        if (logger.isDebugEnabled()) {
            logger.debug("BeanDefinition加载完成");
        }
    }

    @Override
    public Resource getResource(String location) throws IOException {
        if (StringUtils.isNotBlank(location)) {
            if (location.startsWith(Resource.CLASS_PATH_PREFIX)) {
                return new ClassPathResource(location.substring(Resource.CLASS_PATH_PREFIX.length()));
            } else if (location.startsWith(Resource.FILE_SYSTEM_PREFIX)) {
                return new FileSystemResource(location.substring(Resource.FILE_SYSTEM_PREFIX.length()));
            } else {
                return new UrlResource(location);
            }
        }
        return null;
    }

    /**
     * 加载不同的Resource
     *
     * @param location
     * @throws IOException
     */
    private void load(String... location) throws IOException {
        if (resources == null) {
            resources = new ArrayList<>();
        }
        // 完成加载，创建好 Resource
        if (location != null && location.length > 0) {
            for (String lo : location) {
                Resource re = this.getResource(lo);
                if (re != null) {
                    this.resources.add(re);
                }
            }
        }
    }
}
