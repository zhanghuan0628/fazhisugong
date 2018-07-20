package com.ffxl.admin.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.log.LogManager;
import com.ffxl.admin.core.log.factory.LogTaskFactory;
import com.ffxl.admin.core.shiro.ShiroKit;
import com.ffxl.admin.core.shiro.ShiroUser;
import com.ffxl.admin.core.util.KaptchaUtil;
import com.ffxl.cloud.service.MenuService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.core.exception.ValidCodeException;
import com.ffxl.platform.core.node.MenuNode;
import com.ffxl.platform.core.support.HttpKit;
import com.ffxl.platform.util.ToolUtil;
import com.google.code.kaptcha.Constants;


/**
 * 登录控制器
 * @author jiawei
 * 2018年6月24日
 */
@Controller
public class LoginController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private SysUserService userService;
    @Autowired
    private MenuService menuService;
    
	private String PREFIX = "/";
	
	/**
     * 跳转到首页
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(Model model,HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!ShiroKit.isAuthenticated() || ShiroKit.getUser() == null) {
            return PREFIX+"login.html"; //跳转登录
        } 
        //获取菜单列表
        List<String> roleList = ShiroKit.getUser().getRoleList();
        if (roleList == null || roleList.size() == 0) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return PREFIX+"login.html";
        }
//        List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);
//        List<MenuNode> titles = MenuNode.buildTitle(menus);
////        titles = ApiMenuFilter.build(titles);
//        model.addAttribute("titles", titles);
//        //用户信息
//        ShiroUser user = ShiroKit.getUser();
//        model.addAttribute("user", user);
        
        return PREFIX + "index.html";
    }
    	
	/**
     * 跳转到登录页
     */
    @RequestMapping(value = "/login_view", method = RequestMethod.GET)
    public String index(Model model) {
        super.getSession().setAttribute("a", "金花或");
        System.out.println("测试增加缓存"+super.getSession().getAttribute("a"));
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            System.out.println("跳转");
            return REDIRECT + "/";
        } else {
            return PREFIX+"login.html";
        }
    }


    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginVali() {
        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        String remember = super.getPara("remember");
        System.out.println("测试获取缓存"+super.getSession().getAttribute("a"));
        //验证验证码是否正确
        if (KaptchaUtil.getKaptchaOnOff()) {
            String kaptcha = super.getPara("kaptcha").trim();
            Subject currentUser = ShiroKit.getSubject();
            String code = (String)  super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
                logger.debug("----------------------验证码输入不正确");
                throw new ValidCodeException("验证码输入不正确");
            }
        }

        Subject currentUser = ShiroKit.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        if ("on".equals(remember)) {
            token.setRememberMe(true);
        } else {
            token.setRememberMe(false);
        }
        currentUser.login(token);

        ShiroUser shiroUser = ShiroKit.getUser();
        super.getSession().setAttribute("shiroUser", shiroUser);
        super.getSession().setAttribute("userName", shiroUser.getUserName());
        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), HttpKit.getIp()));
        ShiroKit.getSession().setAttribute("sessionFlag", true);
        return REDIRECT + "/";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), HttpKit.getIp()));
        ShiroKit.getSubject().logout();
        return REDIRECT + "/";
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
