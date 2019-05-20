package me.szp.framework.spring.web.mvc;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;


public interface BindingParamter {

    Object bindingParamter(Parameter parameter, HttpServletRequest request) throws IllegalAccessException, InstantiationException, NoSuchMethodException;

}
