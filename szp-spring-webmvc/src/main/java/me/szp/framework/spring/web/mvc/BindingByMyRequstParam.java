package me.szp.framework.spring.web.mvc;

import me.szp.framework.spring.common.utils.StringUtils;
import me.szp.framework.spring.web.exception.MvcException;
import me.szp.framework.spring.web.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Parameter;

/**
 * @ClassName BindingByMyRequstParam
 * @Description 参数注解是MyMyRequstParam时，绑定数据的类
 * @Data 2018/7/4
 * @Author xiao liang
 */
public class BindingByMyRequstParam implements BindingParamter {

    @Override
    public Object bindingParamter(Parameter parameter, HttpServletRequest request) {

        RequestParam requestParam = parameter.getAnnotation(RequestParam.class);
        String requestParamValue = requestParam.value();
        String parameterType = parameter.getType().getSimpleName();
        //测试，看看是什么，初步估计是空
        String parameter1 = request.getParameter(requestParamValue);
        if (StringUtils.isEmpty(parameter1)) {
            throw new MvcException("绑定参数异常");
        }

        if ("String".equals(parameterType)) {
            //parameter1赋值
            return parameter1;

        } else if ("Integer".equals(parameterType) || "int".equals(parameterType)) {
            return Integer.valueOf(parameter1);
        }
        return null;


    }


}
