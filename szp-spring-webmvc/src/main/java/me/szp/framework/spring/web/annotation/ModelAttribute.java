package me.szp.framework.spring.web.annotation;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/10 11:46
 */
public @interface ModelAttribute {
    String value() default "";
}