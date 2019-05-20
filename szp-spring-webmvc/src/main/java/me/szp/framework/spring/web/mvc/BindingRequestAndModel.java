package me.szp.framework.spring.web.mvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public class BindingRequestAndModel {


    public static void bindingRequestAndModel(ModelAndView modelAndView, HttpServletRequest request) {

        ModelMap myModelMap = modelAndView.getModelMap();
        if (!myModelMap.isEmpty()) {
            Set<Map.Entry<String, Object>> entries1 = myModelMap.entrySet();
            for (Map.Entry<String, Object> entryMap :
                    entries1) {
                String key = entryMap.getKey();
                Object value = entryMap.getValue();
                request.setAttribute(key, value);
            }
        }

    }
}
