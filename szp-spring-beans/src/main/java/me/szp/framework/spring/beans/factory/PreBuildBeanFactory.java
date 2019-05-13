package me.szp.framework.spring.beans.factory;

import java.util.ArrayList;
import java.util.List;

import me.szp.framework.spring.beans.factory.config.BeanDefinition;
import me.szp.framework.spring.beans.exception.BeanDefinitionRegistryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author GhostDog
 */
public class PreBuildBeanFactory extends DefaultBeanFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final List<String> beanNames = new ArrayList<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition)
            throws BeanDefinitionRegistryException {
        super.registerBeanDefinition(beanName, beanDefinition);
        synchronized (beanNames) {
            beanNames.add(beanName);
        }
    }

    public void preInstantiateSingletons() throws Throwable {
        synchronized (beanNames) {
            for (String name : beanNames) {
                BeanDefinition bd = this.getBeanDefinition(name);
                if (bd.isSingleton()) {
                    this.doGetBean(name);
                    if (logger.isDebugEnabled()) {
                        logger.debug("preInstantiate: name=" + name + " " + bd);
                    }
                }
            }
        }
    }
}
