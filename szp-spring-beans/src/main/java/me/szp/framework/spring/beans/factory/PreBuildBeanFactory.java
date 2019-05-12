package me.szp.framework.spring.beans.factory;

import me.szp.framework.spring.beans.factory.config.BeanDefinition;
import me.szp.framework.spring.beans.exception.BeanDefinitionRegistryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 提前实例化单例bean
 *
 * @author Ghost Dog
 */

public class PreBuildBeanFactory extends DefaultBeanFactory {

    private Logger logger = LoggerFactory.getLogger(PreBuildBeanFactory.class);

    private final List<String> beanNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionRegistryException {
        super.registerBeanDefinition(beanName, beanDefinition);
        synchronized (beanNames) {
            beanNames.add(beanName);
        }
    }


    /**
     * 预实例化Bean
     *
     * @throws Exception Exception
     */
    public void preInstantiateSingletons() throws Exception {
        synchronized (beanNames) {
            for (String name : beanNames) {
                BeanDefinition beanDefinition = this.getBeanDefinition(name);
                if (beanDefinition.isSingleton()) {
                    this.doGetBean(name);
                    if (logger.isDebugEnabled()) {
                        logger.debug("预实例化Bean：[{}]", name);
                    }
                }
            }
        }
    }
}
