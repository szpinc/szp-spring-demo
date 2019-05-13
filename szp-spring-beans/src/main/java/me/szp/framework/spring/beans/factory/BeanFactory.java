package me.szp.framework.spring.beans.factory;

import me.szp.framework.spring.beans.factory.config.BeanPostProcessor;

public interface BeanFactory {
    /**
     * 获取bean
     *
     * @param name bean的名字
     * @return bean 实例
     * @throws Exception Exception
     */
    Object getBean(String name) throws Throwable;

    /**
     * 注册Bean处理器
     *
     * @param bpp
     */
    void registerBeanPostProcessor(BeanPostProcessor bpp);
}
