package me.szp.framework.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/20 17:35
 */

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
