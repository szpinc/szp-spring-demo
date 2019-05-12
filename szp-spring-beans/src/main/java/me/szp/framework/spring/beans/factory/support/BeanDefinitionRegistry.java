package me.szp.framework.spring.beans.factory.support;


import me.szp.framework.spring.beans.exception.BeanDefinitionRegistryException;
import me.szp.framework.spring.beans.factory.config.BeanDefinition;

/**
 * bean定义BeanDefinition定义好了就需要告诉IOC容器(bean工厂):
 * 通过bean定义注册接口BeanDefinitionRegistry把bean定义BeanDefinition注册到IOC容器(bean工厂)BeanFactory
 *
 * @author Ghost Dog
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册bean定义
     *
     * @param beanName       bean名称
     * @param beanDefinition Bean定义
     * @throws BeanDefinitionRegistryException Bean定义异常
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) throws BeanDefinitionRegistryException;

    /**
     * 获取bean定义
     *
     * @param beanName bean名称
     * @return Bean定义对象
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * 判断是否包含bean定义
     *
     * @param beanName Bean名称
     * @return 结果
     */
    boolean containsBeanDefinition(String beanName);
}
