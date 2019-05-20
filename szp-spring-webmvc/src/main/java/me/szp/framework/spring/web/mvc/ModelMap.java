package me.szp.framework.spring.web.mvc;

import java.util.LinkedHashMap;

public class ModelMap extends LinkedHashMap<String,Object> implements Model {


    @Override
    public Model addAttribute(String attributeName, Object attributeValue) {
        put(attributeName,attributeValue);
        return this;
    }
}
