package me.szp.framework.spring.beans;

/**
 * @author Sun Zhi Peng
 * @version 1.0.0
 * @date 2019/5/13 11:53
 */

public enum XMLRules {
    /**
     * Bean标签规则
     */
    BEAN_RULE("bean", "id", "class"),

    /**
     * 包扫描规则
     */
    SCAN_RULE("component-scan", "base-package", "null"),

    /**
     * set注入的规则
     */
    SET_INJECT(" property", "name", "value"),
    /**
     * 构造器注入的规则
     */
    CONS_INJECT("constructor-arg", "value", "index");

    /**
     * property属性
     */
    private String property;

    /**
     * name属性
     */
    private String name;

    /**
     * value属性
     */
    private String value;

    XMLRules(String property, String name, String value) {
        this.property = property;
        this.name = name;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
