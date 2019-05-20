package me.szp.framework.spring.web.mvc;

import lombok.extern.slf4j.Slf4j;
import me.szp.framework.spring.common.utils.AnnotationUtils;
import me.szp.framework.spring.common.utils.ConvertUtils;
import me.szp.framework.spring.common.utils.GetMethodName;
import me.szp.framework.spring.common.utils.StringUtils;
import me.szp.framework.spring.web.exception.MvcException;
import me.szp.framework.spring.web.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Slf4j
public class BindingByModelAttribute implements BindingParamter {

    @Override
    public Object bindingParamter(Parameter parameter, HttpServletRequest request) throws IllegalAccessException, InstantiationException, NoSuchMethodException {

        ModelAttribute myModelAttribute = parameter.getAnnotation(ModelAttribute.class);
        Class<?> aClass = parameter.getType();
        if (!AnnotationUtils.isEmpty(myModelAttribute)) {
            if (!aClass.getSimpleName().equals(myModelAttribute.value())) {
                throw new MvcException("实体类绑定异常，请重新检查");
            }
        }

        Field[] fields = aClass.getDeclaredFields();
        Object object = aClass.newInstance();
        for (Field field :
                fields) {
            String parameter1 = request.getParameter(field.getName());
            if (!StringUtils.isEmpty(parameter1)) {
                Object setObject = ConvertUtils.convert(field.getType().getSimpleName(), parameter1);
                String methodName = GetMethodName.getSetMethodNameByField(field.getName());
                Method method = aClass.getMethod(methodName, field.getType());
                try {
                    method.invoke(object, setObject);

                } catch (InvocationTargetException e) {
                    log.error("{}属性赋值异常", field.getName());
                    e.printStackTrace();
                }

            }
        }

        return object;

    }


}
