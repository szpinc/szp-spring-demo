package me.szp.framework.spring.beans.factory.support;

import me.szp.framework.spring.beans.exception.BeanDefinitionRegistryException;
import me.szp.framework.spring.beans.factory.config.BeanDefinition;


/**
 * BeanDefinition注册
 *
 * @author GhostDog
 */
public interface BeanDefinitionRegistry {

    /**
     * 将Bean名称与相应的BeanDefinition注册
     *
     * @param beanName       Bean名称
     * @param beanDefinition 相应的BeanDefinition
     * @throws BeanDefinitionRegistryException BeanDefinitionRegistryException
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistryException;

    /**
     * 根据Bean名称获取BeanDefinition
     *
     * @param beanName Bean名称
     * @return 对应的BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 根据Bean名称查询是否含有BeanDefinition
     *
     * @param beanName Bean名称
     * @return 结果
     */
    boolean containsBeanDefinition(String beanName);

}
