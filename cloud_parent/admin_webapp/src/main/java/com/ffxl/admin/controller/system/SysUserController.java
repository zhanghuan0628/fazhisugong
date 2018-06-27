package com.ffxl.admin.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NoPermissionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.cloud.service.impl.SysUserServiceImpl;
import com.ffxl.platform.constant.Const;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.constant.status.SysUserStatus;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.core.log.LogObjectHolder;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 管理员控制器
 * 
 * @author jiawei 2018年6月25日
 */
@Controller
@RequestMapping("/sys_user")
public class SysUserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);

    private static String PREFIX = "/system/user/";

    @Autowired
    private SysUserService userService;

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "user.html";
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @RequestMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add.html";
    }

    // /**
    // * 跳转到角色分配页面
    // */
    // //@RequiresPermissions("/mgr/role_assign") //利用shiro自带的权限检查
    // @Permission
    // @RequestMapping("/role_assign/{userId}")
    // public String roleAssign(@PathVariable Integer userId, Model model) {
    // if (ToolUtil.isEmpty(userId)) {
    // throw new GunsException(BizExceptionEnum.REQUEST_NULL);
    // }
    // User user = (User) Db.create(UserMapper.class).selectOneByCon("id",
    // userId);
    // model.addAttribute("userId", userId);
    // model.addAttribute("userAccount", user.getAccount());
    // return PREFIX + "user_roleassign.html";
    // }
    
     /**
     * 跳转到编辑管理员页面
     */
//     @Permission
     @RequestMapping("/user_edit")
     public String userEdit(String id, Model model) {
     if (StringUtil.isEmpty(id)) {
         throw new BusinessException(Message.M6002);
     }
//     assertAuth(id);
     SysUser user = userService.selectByPrimaryKey(id);
     model.addAttribute("info", user);
//     model.addAttribute("roleName",ConstantFactory.me().getRoleName(user.getRoleid()));
//     model.addAttribute("deptName",ConstantFactory.me().getDeptName(user.getDeptid()));
     LogObjectHolder.me().set(user);
     return PREFIX + "user_edit.html";
     }
    
    // /**
    // * 跳转到查看用户详情页面
    // */
    // @RequestMapping("/user_info")
    // public String userInfo(Model model) {
    // Integer userId = ShiroKit.getUser().getId();
    // if (ToolUtil.isEmpty(userId)) {
    // throw new GunsException(BizExceptionEnum.REQUEST_NULL);
    // }
    // User user = this.userService.selectById(userId);
    // model.addAttribute(user);
    // model.addAttribute("roleName",
    // ConstantFactory.me().getRoleName(user.getRoleid()));
    // model.addAttribute("deptName",
    // ConstantFactory.me().getDeptName(user.getDeptid()));
    // LogObjectHolder.me().set(user);
    // return PREFIX + "user_view.html";
    // }
    //
    // /**
    // * 跳转到修改密码界面
    // */
    // @RequestMapping("/user_chpwd")
    // public String chPwd() {
    // return PREFIX + "user_chpwd.html";
    // }
    //
    // /**
    // * 修改当前用户的密码
    // */
    // @RequestMapping("/changePwd")
    // @ResponseBody
    // public Object changePwd(@RequestParam String oldPwd, @RequestParam String
    // newPwd, @RequestParam String rePwd) {
    // if (!newPwd.equals(rePwd)) {
    // throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
    // }
    // Integer userId = ShiroKit.getUser().getId();
    // User user = userService.selectById(userId);
    // String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
    // if (user.getPassword().equals(oldMd5)) {
    // String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
    // user.setPassword(newMd5);
    // user.updateById();
    // return SUCCESS_TIP;
    // } else {
    // throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
    // }
    // }
    //
    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables, Page page, SysUser sysUser) {
        page = this.getPageInfo(page, dataTables);
        List<SysUser> dataList = userService.queryPageList(sysUser, page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult(true, dataTables);

    }
    
    /**
     * 校验账号是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/check")
    public Boolean check(String loginName) {
        // 判断账号是否重复
        SysUser model = new SysUser();
        model.setLoginName(loginName);
        SysUser theUser = userService.queryByModel(model);
        if (theUser != null) {
            logger.info(Message.getMessage(Message.M6002));
            return false;
        }else{
            return true;
        }
    }
    /**
     * 添加管理员
     */
    @RequestMapping("/add")
    // @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public JsonResult add(SysUser user) {
        // 判断账号是否重复
        SysUser model = new SysUser();
        model.setLoginName(user.getLoginName());
        SysUser theUser = userService.queryByModel(model);
        if (theUser != null) {
            return new JsonResult(Message.M6002);
        }
        // 完善账号信息
        user.setId(UUIDUtil.getUUID());
        // user.setSalt(ShiroKit.getRandomSalt(5));
        // user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(SysUserStatus.OK.getCode());
        int ret = userService.insert(user);
        if (ret > 0) {
            return new JsonResult(true);
        } else {
            return new JsonResult(false);
        }
    }

    /**
     * 修改管理员
     *
     * @throws NoPermissionException
     */
    @RequestMapping("/edit")
    // @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public JsonResult edit(SysUser user){
        // if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
        // this.userService.updateById(UserFactory.createUser(user));
        // return SUCCESS_TIP;
        // } else {
        // assertAuth(user.getId());
        // ShiroUser shiroUser = ShiroKit.getUser();
        // if (shiroUser.getId().equals(user.getId())) {
        // this.userService.updateById(UserFactory.createUser(user));
        // return SUCCESS_TIP;
        // } else {
        // throw new GunsException(BizExceptionEnum.NO_PERMITION);
        // }
        // }
        // 更新账号
        try {
            int ret = userService.update(user);
            if (ret > 0) {
                return new JsonResult(true);
            } else {
                return new JsonResult(false);
            }
        } catch (BusinessException e) {
            return new JsonResult(Message.M6002);
        }
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view")
    @ResponseBody
    public JsonResult view(String id) {
        if (StringUtil.isEmpty(id)) {
            return new JsonResult(Message.M4002);
        }
        // assertAuth(userId);
        SysUser user = userService.selectByPrimaryKey(id);
        return new JsonResult(true, user);
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    // @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public JsonResult reset(String id) {
        if (StringUtil.isEmpty(id)) {
            return new JsonResult(Message.M4002);
        }
        // assertAuth(userId);
        SysUser user = userService.selectByPrimaryKey(id);
        // user.setSalt(ShiroKit.getRandomSalt(5));
        // user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        int ret = userService.updateByPrimaryKey(user);
        if (ret > 0) {
            return new JsonResult(true);
        } else {
            return new JsonResult(false);
        }
    }

    /**
     * 禁用用户
     */
    @RequestMapping("/stop")
    // @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public JsonResult stop(@RequestParam String id) {
        if (StringUtil.isEmpty(id)) {
            return new JsonResult(Message.M4002);
        }
        // 不能禁用超级管理员
        if (id.equals(Const.ADMIN_ID)) {
            return new JsonResult(Message.M6000);
        }
        int ret = userService.updateStatus(id, 2);
        if (ret > 0) {
            return new JsonResult(true);
        } else {
            return new JsonResult(false);
        }
    }

    /**
     * 启用用户
     */
    @RequestMapping("/start")
    // @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public JsonResult start(@RequestParam String id) {
        if (StringUtil.isEmpty(id)) {
            return new JsonResult(Message.M4002);
        }
        int ret = userService.updateStatus(id, 1);
        if (ret > 0) {
            return new JsonResult(true);
        } else {
            return new JsonResult(false);
        }
    }

    /**
     * 删除用户
     */
    @RequestMapping("/del")
    // @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public JsonResult del(@RequestParam String ids) {
        if (StringUtil.isEmpty(ids)) {
            return new JsonResult(Message.M4002);
        }
        String[] idArray = ids.split(",");
        List<String> idList = new ArrayList<String>();
        for (int i = 0; i < idArray.length; i++) {
            idList.add(idArray[i]);
        }
        // 不能删除超级管理员
        for (String id : idList) {
            if (id.equals(Const.ADMIN_ID)) {
                return new JsonResult(Message.M6001);
            }
        }
        int ret = userService.deleteByIds(idList);
        if (ret > 0) {
            return new JsonResult(true);
        } else {
            return new JsonResult(false);
        }
    }
    //
    // /**
    // * 分配角色
    // */
    // @RequestMapping("/setRole")
    // @BussinessLog(value = "分配角色", key = "userId,roleIds", dict =
    // UserDict.class)
    // @Permission(Const.ADMIN_NAME)
    // @ResponseBody
    // public Tip setRole(@RequestParam("userId") Integer userId,
    // @RequestParam("roleIds") String roleIds) {
    // if (ToolUtil.isOneEmpty(userId, roleIds)) {
    // throw new GunsException(BizExceptionEnum.REQUEST_NULL);
    // }
    // //不能修改超级管理员
    // if (userId.equals(Const.ADMIN_ID)) {
    // throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
    // }
    // assertAuth(userId);
    // this.userService.setRoles(userId, roleIds);
    // return SUCCESS_TIP;
    // }
    //
    // /**
    // * 上传图片(上传到项目的webapp/static/img)
    // */
    // @RequestMapping(method = RequestMethod.POST, path = "/upload")
    // @ResponseBody
    // public String upload(@RequestPart("file") MultipartFile picture) {
    // String fileUploadType = gunsProperties.getFileUploadType();
    // if(!isEmpty(fileUploadType)){
    // // TODO OSS 上传
    // return "";
    // }else{
    // String pictureName = UUID.randomUUID().toString() + ".jpg";
    // try {
    // String fileSavePath = gunsProperties.getFileUploadPath();
    // picture.transferTo(new File(fileSavePath + pictureName));
    // } catch (Exception e) {
    // throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
    // }
    // return pictureName;
    // }
    // }
    //
    // /**
    // * 判断当前登录的用户是否有操作这个用户的权限
    // */
    // private void assertAuth(Integer userId) {
    // if (ShiroKit.isAdmin()) {
    // return;
    // }
    // List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
    // User user = this.userService.selectById(userId);
    // Integer deptid = user.getDeptid();
    // if (deptDataScope.contains(deptid)) {
    // return;
    // } else {
    // throw new GunsException(BizExceptionEnum.NO_PERMITION);
    // }
    //
    // }
}
