package me.szp.framework.spring.context.annotation;

import me.szp.framework.spring.beans.factory.config.BeanDefinition;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

    String name() default "";

    String scope() default BeanDefinition.SCOPE_SINGLETON;

    String factoryMethodName() default "";

    String factoryBeanName() default "";

    String initMethodName() default "";

    String destroyMethodName() default "";
}
