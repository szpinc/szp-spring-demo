package me.szp.framework.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/20 17:58
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

    /**
     * The actual value expression: for example {@code #{systemProperties.myProp}}.
     */
    String value();

}

