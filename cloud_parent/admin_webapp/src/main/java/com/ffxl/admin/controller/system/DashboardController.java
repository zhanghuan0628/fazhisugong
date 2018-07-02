package com.ffxl.admin.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.admin.core.shiro.factory.IShiro;
import com.ffxl.admin.core.shiro.factory.ShiroFactroy;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.MenuService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.core.node.MenuNode;

@Controller
@RequestMapping("/blackboard")
public class DashboardController extends BaseController {
	private String PREFIX = "/";
	
	/**
     * 跳转到黑板
     */
    @RequestMapping("")
    public String blackboard(Model model) {
        return  PREFIX + "system/blackboard.html";
    }
    
    
}
