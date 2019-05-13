package me.szp.framework.spring.beans.factory.support;

import me.szp.framework.core.io.Resource;

/**
 * bean定义读取器接口
 *
 * @author GhostDog
 */
public interface BeanDefinitionReader {

    /**
     * 从Resource里面加载bean定义、创建bean定义、注册bean定义到bean工厂
     *
     * @param resource Resource对象
     * @throws Throwable Throwable
     */
    void loadBeanDefinitions(Resource resource) throws Throwable;

    /**
     * 从多个Resource里面加载bean定义、创建bean定义、注册bean定义到bean工厂
     *
     * @param resource Resource对象
     * @throws Throwable Throwable
     */
    void loadBeanDefinitions(Resource... resource) throws Throwable;
}
