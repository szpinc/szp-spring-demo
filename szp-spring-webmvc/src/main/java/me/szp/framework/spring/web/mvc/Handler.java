package me.szp.framework.spring.web.mvc;

import lombok.extern.slf4j.Slf4j;
import me.szp.framework.spring.common.utils.AnnotationUtils;
import me.szp.framework.spring.core.annotation.Controller;
import me.szp.framework.spring.web.exception.MvcException;
import me.szp.framework.spring.web.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Handler
 * @Description 遍历bean容器，在有controller注解的类中有requestmapping扫描的方法，则将方法和url和方法绑定
 * @Data 2018/7/3
 * @Author xiao liang
 */
@Slf4j
public class Handler {

    public static Map<String, Method> bindingRequestMapping(Map<String, Object> beanContainerMap) {
        Map<String, Method> handlerMapping = new ConcurrentHashMap<>();
        if (beanContainerMap != null) {
            Set<Map.Entry<String, Object>> entries = beanContainerMap.entrySet();

            for (Map.Entry<String, Object> entry :
                    entries) {
                Class aClass = entry.getValue().getClass();
                Annotation annotation = aClass.getAnnotation(Controller.class);
                Method[] methods = aClass.getMethods();
                if (!AnnotationUtils.isEmpty(annotation) && methods != null) {
                    for (Method method :
                            aClass.getMethods()) {
                        RequestMapping requestMappingAnnotation = method.getAnnotation(RequestMapping.class);
                        if (!AnnotationUtils.isEmpty(requestMappingAnnotation)) {
                            String key = requestMappingAnnotation.value();
                            handlerMapping.put(key, method);
                        }
                    }
                }

            }

        } else {
            throw new MvcException("实例化bean异常，没有找到容器");
        }

        return handlerMapping;

    }

}
