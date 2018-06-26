package com.ffxl.admin.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ffxl.admin.controller.base.BaseController;

@Controller
public class DashboardController extends BaseController {
	private String PREFIX = "/";
	
	
	/**
	 * 跳转到首页
	 * @return
	 */
	@RequestMapping(value = "/")
    public String list() {
		return PREFIX + "index.html";
    }
	
	/**
     * 跳转到黑板
     */
    @RequestMapping("/blackboard")
    public String blackboard(Model model) {
        return  PREFIX + "system/blackboard.html";
    }
    
    /**
     * 跳转404
     */
    @RequestMapping("/404")
    public String t404(Model model) {
        return  PREFIX + "error/404.html";
    }
    
    /**
     * 跳转500
     */
    @RequestMapping("/500")
    public String t500(Model model) {
        return  PREFIX + "error/500.html";
    }
}
