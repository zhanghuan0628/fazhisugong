package com.ffxl.admin.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ffxl.admin.controller.base.BaseController;

/**
 * 登录控制器
 * @author jiawei
 * 2018年6月24日
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private String PREFIX = "/";
	
	
	/**
     * 跳转到主页
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
//        //获取菜单列表
//        List<Integer> roleList = ShiroKit.getUser().getRoleList();
//        if (roleList == null || roleList.size() == 0) {
//            ShiroKit.getSubject().logout();
//            model.addAttribute("tips", "该用户没有角色，无法登陆");
//            return "/login.html";
//        }
//        List<MenuNode> menus = menuService.getMenusByRoleIds(roleList);
//        List<MenuNode> titles = MenuNode.buildTitle(menus);
//        titles = ApiMenuFilter.build(titles);
//
//        model.addAttribute("titles", titles);
//
//        //获取用户头像
//        Integer id = ShiroKit.getUser().getId();
//        User user = userService.selectById(id);
//        String avatar = user.getAvatar();
//        model.addAttribute("avatar", avatar);

        return PREFIX+"login.html";
    }

//    /**
//     * 跳转到登录页面
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
//            return REDIRECT + "/";
//        } else {
//            return "/login.html";
//        }
//    }
//
//    /**
//     * 点击登录执行的动作
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String loginVali() {
//
//        String username = super.getPara("username").trim();
//        String password = super.getPara("password").trim();
//        String remember = super.getPara("remember");
//
//        //验证验证码是否正确
//        if (KaptchaUtil.getKaptchaOnOff()) {
//            String kaptcha = super.getPara("kaptcha").trim();
//            String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//            if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equalsIgnoreCase(code)) {
//                throw new InvalidKaptchaException();
//            }
//        }
//
//        Subject currentUser = ShiroKit.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
//
//        if ("on".equals(remember)) {
//            token.setRememberMe(true);
//        } else {
//            token.setRememberMe(false);
//        }
//
//        currentUser.login(token);
//
//        ShiroUser shiroUser = ShiroKit.getUser();
//        super.getSession().setAttribute("shiroUser", shiroUser);
//        super.getSession().setAttribute("username", shiroUser.getAccount());
//
//        LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
//
//        ShiroKit.getSession().setAttribute("sessionFlag", true);
//
//        return REDIRECT + "/";
//    }
//
//    /**
//     * 退出登录
//     */
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
//        ShiroKit.getSubject().logout();
//        return REDIRECT + "/login";
//    }
}
