package me.szp.framework.spring.beans.factory.config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import me.szp.framework.spring.beans.PropertyValue;
import org.apache.commons.lang3.StringUtils;

/**
 * bean定义接口
 *
 * @author Ghost Dog
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 类
     */
    Class<?> getBeanClass();

    /**
     * Scope
     */
    String getScope();

    /**
     * 是否单例
     */
    boolean isSingleton();

    /**
     * 是否原型
     */
    boolean isPrototype();

    /**
     * 工厂bean名
     */
    String getFactoryBeanName();

    /**
     * 工厂方法名
     */
    String getFactoryMethodName();

    /**
     * 初始化方法
     */
    String getInitMethodName();

    /**
     * 销毁方法
     */
    String getDestroyMethodName();

    /* 下面的四个方法是供beanFactory中使用的 */

    public Constructor<?> getConstructor();

    public void setConstructor(Constructor<?> constructor);

    public Method getFactoryMethod();

    public void setFactoryMethod(Method factoryMethod);

    public Object[] getConstructorArgumentRealValues();

    public void setConstructorArgumentRealValues(Object[] values);

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

    /**
     * 获得构造参数定义
     *
     * @return 参数定义集合
     */
    List<?> getConstructorArgumentValues();

    /**
     * 属性依赖
     *
     * @return 依赖属性集合
     */
    List<PropertyValue> getPropertyValues();

}
