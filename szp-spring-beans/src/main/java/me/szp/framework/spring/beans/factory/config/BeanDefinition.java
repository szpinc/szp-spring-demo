package me.szp.framework.spring.beans.factory.config;

import org.apache.commons.lang3.StringUtils;

/**
 * bean定义接口:要IOC容器(bean工厂)创建bean实例，就得告诉IOC容器(bean工厂)需要创建什么样的bean-BeanDefinition
 *
 * @author Ghost Dog
 */
public interface BeanDefinition {
    /**
     * 单例模式
     */
    String SCOPE_SINGLETON = "singleton";

    /**
     * 原型模式
     */
    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 类:new 构造方法的方式创建bean时，需要告诉bean工厂怎么获取类的名称
     *
     * @return 获取的类对象
     */
    Class<?> getBeanClass();

    /**
     * 获取Scope
     *
     * @return Scope
     */
    String getScope();

    /**
     * 是否单例
     *
     * @return 结果
     */
    boolean isSingleton();

    /**
     * 是否原型
     *
     * @return 结果
     */
    boolean isPrototype();

    /**
     * 工厂bean名：成员工厂方法的方式创建bean时，需要告诉bean工厂怎么获取工厂bean名
     *
     * @return 工厂名
     */
    String getFactoryBeanName();

    /**
     * 工厂方法名:静态工厂方法的方式创建bean时，需要告诉bean工厂怎么获取工厂方法名
     *
     * @return 工厂方法名
     */
    String getFactoryMethodName();

    /**
     * 获取初始化方法
     *
     * @return 初始化方法
     */
    String getInitMethodName();

    /**
     * 获取销毁方法
     *
     * @return 销毁方法
     */
    String getDestroyMethodName();

    /**
     * 校验bean定义的合法性
     *
     * @return 校验结果
     */
    default boolean validate() {
        // 没定义class,工厂bean或工厂方法没指定，则不合法。
        if (this.getBeanClass() == null) {
            if (StringUtils.isBlank(getFactoryBeanName()) || StringUtils.isBlank(getFactoryMethodName())) {
                return false;
            }
        }

        // 定义了类，又定义工厂bean，不合法
        return this.getBeanClass() == null || !StringUtils.isNotBlank(getFactoryBeanName());
    }
}
