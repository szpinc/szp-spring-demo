package me.szp.framework.spring.beans.factory.config;


/**
 * Bean处理接口
 * Spring容器完成Bean的实例化、配置和其他的初始化前后添加一些自己的逻辑处理
 *
 * @author GhostDog
 */
public interface BeanPostProcessor {

    /**
     * 前置处理
     * 在实例化及依赖注入完成后、在任何初始化代码调用之前调用
     *
     * @param bean     Bean实例
     * @param beanName Bean名称
     * @return 处理后对象
     * @throws Throwable Throwable
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }

    /**
     * 后置处理
     * 在初始化代码调用之后调用
     *
     * @param bean     Bean实例
     * @param beanName Bean 名称
     * @return 处理后的对象
     * @throws Throwable Throwable
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws Throwable {
        return bean;
    }
}