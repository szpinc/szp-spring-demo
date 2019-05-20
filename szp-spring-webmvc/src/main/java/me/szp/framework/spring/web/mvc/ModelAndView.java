package me.szp.framework.spring.web.mvc;

import lombok.Data;

/**
 * @ClassName ModelAndView
 * @Description
 * @Data 2018/7/4
 * @Author xiao liang
 */
@Data
public class ModelAndView {

    private String view;
    private ModelMap modelMap;

    public ModelAndView(String view) {
        this.view = view;
    }
}
