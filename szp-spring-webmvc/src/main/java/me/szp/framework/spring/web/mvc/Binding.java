package me.szp.framework.spring.web.mvc;

import me.szp.framework.spring.common.utils.AnnotationUtils;
import me.szp.framework.spring.common.utils.ConvertUtils;
import me.szp.framework.spring.web.exception.MvcException;
import me.szp.framework.spring.web.annotation.ModelAttribute;
import me.szp.framework.spring.web.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Binding {

    public static List<Object> bingdingMethodParamters(Map<String, Method> bindingRequestMapping, HttpServletRequest request) {
        List<Object> resultParameters = new ArrayList<>();
        Set<Map.Entry<String, Method>> entries = bindingRequestMapping.entrySet();
        for (Map.Entry<String, Method> entry :
                entries) {
            Method method = entry.getValue();
            Parameter[] parameters = method.getParameters();

            //pan kong
            for (Parameter parameter :
                    parameters) {
                if (!AnnotationUtils.isEmpty(parameter.getAnnotations())) {
                    Object resultParameter = null;
                    try {
                        resultParameter = bingdingEachParamter(parameter, request);
                    } catch (IllegalAccessException | NoSuchMethodException | InstantiationException e) {
                        e.printStackTrace();
                        throw new MvcException("绑定参数异常");
                    }
                    resultParameters.add(resultParameter);
                }
            }
        }
        return resultParameters;
    }

    private static Object bingdingEachParamter(Parameter parameter, HttpServletRequest request) throws IllegalAccessException, NoSuchMethodException, InstantiationException {

        if (!AnnotationUtils.isEmpty(parameter.getAnnotation(RequestParam.class))) {
            BindingParamter bindingParamter = new BindingByMyRequstParam();
            return bindingParamter.bindingParamter(parameter, request);
        } else if (!AnnotationUtils.isEmpty(parameter.getAnnotation(ModelAttribute.class))) {
            BindingParamter bindingParamter = new BindingByModelAttribute();
            return bindingParamter.bindingParamter(parameter, request);
        } else if (parameter.getAnnotations() == null || parameter.getAnnotations().length == 0) {
            boolean flag;
            flag = ConvertUtils.isBasicType(parameter.getType().getSimpleName());
            if (flag) {
                BindingParamter bindingParamter = new BindingByMyRequstParam();
                return bindingParamter.bindingParamter(parameter, request);
            } else {
                BindingParamter bindingParamter = new BindingByModelAttribute();
                return bindingParamter.bindingParamter(parameter, request);
            }
        }
        return null;

    }

}
