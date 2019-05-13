package me.szp.framework.spring.beans;

/**
 * 属性值依赖定义
 *
 * @author GhostDog
 */
public class PropertyValue {

    private String name;

    private Object value;

    public PropertyValue() {
        super();
    }

    public PropertyValue(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
