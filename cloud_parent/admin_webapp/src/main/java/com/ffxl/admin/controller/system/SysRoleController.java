package com.ffxl.admin.controller.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.SysRoleService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.core.node.ZTreeNode;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 角色
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sys_role")
public class SysRoleController extends BaseController{
	 private Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 private static String PREFIX = "/system/sys_role/";
	 
	 @Autowired
	 private SysRoleService sysRoleService;
	 
	 /**
     * 跳转到查看角色列表的页面
     */
     @RequestMapping("sys_role_list")
     public String index() {
        return PREFIX + "role_list.html";
     }
     /**
      * 查询角色列表
      */
     @RequestMapping("/role_pageList")
     @ResponseBody
     public JsonResult list(DataTablesUtil dataTables, Page page, SysRole sysRole) {
         page = this.getPageInfo(page, dataTables);
         List<SysRole> dataList = sysRoleService.queryPageList(sysRole, page);
         dataTables = this.getDataTables(page, dataTables, dataList);
         return new JsonResult(true, dataTables);

     }
     /**
      * 跳转到查看角色新增的页面
      */
     @RequestMapping("/role_add")
     public String addRole(Model model) {
    	 
         return PREFIX + "role_add.html";
     }
     /**
      * 跳转到查看角色修改的页面
      */
     @RequestMapping("/role_edit")
     public String editRole(String id,Model model) {
         SysRole role = sysRoleService.selectByPrimaryKey(id);
         model.addAttribute("role",role);
         model.addAttribute("fName", ConstantFactory.me().getSingleRoleName(role.getPid()));
         LogObjectHolder.me().set(role);
         return PREFIX + "role_edit.html";
     }
     /**
      * 获取角色列表
      */
     @RequestMapping(value = "/roleTreeList")
     @ResponseBody
     public List<ZTreeNode> roleTreeList() {
         List<ZTreeNode> roleTreeList = sysRoleService.roleTreeList();
         roleTreeList.add(ZTreeNode.createParent());
         return roleTreeList;
     }
     /**
      * 角色新增
      */
     @RequestMapping(value = "/add")
     @ResponseBody
     public JsonResult add(SysRole role) {
    	role.setId(UUIDUtil.getUUID());
    	int num = sysRoleService.selectMaxNum();
    	role.setNum(num+1);
    	int i = sysRoleService.insertSelective(role);
    	if(i > 0){
     		return new JsonResult(Message.M2000);
     	}else{
     		return new JsonResult(Message.M5000);
     	}
     }

     /**
      * 角色修改
      */
     @RequestMapping(value = "/edit")
     @ResponseBody
     public JsonResult edit(SysRole role) {
    	int i = sysRoleService.updateByPrimaryKeySelective(role);
    	if(i > 0){
    		return new JsonResult(Message.M2000);
    	}else{
    		return new JsonResult(Message.M5000);
    	}
     }
     /**
      * 校验name是否存在
      * @param name
      * @return
      */
     @ResponseBody
     @RequestMapping("/check")
     public Boolean check(String name) {
    	 SysRole model = new SysRole();
         model.setName(name);
         SysRole theUser = sysRoleService.queryByModel(model);
         if (theUser != null) {
             return false;
         }else{
             return true;
         }
     }
}
