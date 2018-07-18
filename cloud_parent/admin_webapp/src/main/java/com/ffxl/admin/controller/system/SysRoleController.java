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
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.Const;
import com.ffxl.admin.core.common.constant.dictmap.SysRoleDic;
import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.mapper.SysRoleMapper;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.SysRoleService;
import com.ffxl.cloud.service.SysUserService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.node.ZTreeNode;
import com.ffxl.platform.util.Convert;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.ToolUtil;
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
	 
	 @Autowired
	 private SysRoleMapper sysRoleMapper;
	 
	 @Autowired
	 private SysUserService sysUserService;
	 
	 /**
     * 跳转到查看角色列表的页面
     */
     @RequestMapping("sys_role_list")
     public String index() {
        return PREFIX + "role_list.html";
     }
     /**
      * 查询菜单列表
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
     @BussinessLog(value = "新增角色", key = "id", dict = SysRoleDic.class)
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
     @BussinessLog(value = "修改角色", key = "id", dict = SysRoleDic.class)
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
     /**
      * 跳转到角色分配
      */
     @RequestMapping(value = "/power_add")
     public String roleAssign(String id, Model model) {
         model.addAttribute("roleId", id);
         model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(id));
         return PREFIX + "/power_add.html";
     }
     /**
      * 配置权限
      */
     @RequestMapping("/setAuthority")
     @ResponseBody
     public JsonResult setAuthority(String roleId,String ids) {
         int i = sysRoleService.setAuthority(roleId, ids);
         if(i > 0){
     		return new JsonResult(Message.M2000);
     	}else{
     		return new JsonResult(Message.M5000);
     	}
     }
     /**
      * 删除角色
      */
     @RequestMapping(value = "/del")
     @BussinessLog(value = "删除角色", key = "id", dict = SysRoleDic.class)
     @ResponseBody
     public JsonResult remove(String ids) {
    	 int i = -1;
         if(!StringUtil.isEmpty(ids)){
         	String[] idss = ids.split(",");
         	for(String id:idss){
         		 //不能删除超级管理员角色
                if (id.equals(Const.ADMIN_ROLE_ID+"")) {
                	JsonResult js = new JsonResult();
                	js.setCode("1");
                	js.setMessage("不能删除超级管理员角色");
               	 	return js;
                }
             }
         	for(String id:idss){
        		 //不能删除超级管理员角色
               if (id.equals(Const.ADMIN_ROLE_ID+"")) {
            	    JsonResult js = new JsonResult();
               		js.setCode("1");
               		js.setMessage("不能删除超级管理员角色");
              	 	return js;
               }
             //缓存被删除的角色名称
               LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(id));
               i = sysRoleService.deleteByPrimaryKey(id);
               sysRoleMapper.deleteRolesById(id);
            }
        
         }
         if(i > 0){
      		return new JsonResult(Message.M2000);
      	}else{
      		return new JsonResult(Message.M5000);
      	}
     }
     /**
      * 获取角色列表
      */
     @RequestMapping(value = "/roleTreeListByUserId")
     @ResponseBody
     public List<ZTreeNode> roleTreeListByUserId(String userId) {
         SysUser theUser = sysUserService.selectByPrimaryKey(userId);
         String roleid = theUser.getRoleId();
         if (ToolUtil.isEmpty(roleid)) {
             List<ZTreeNode> roleTreeList = this.sysRoleService.roleTreeList();
             return roleTreeList;
         } else {
             String[] strArray = Convert.toStrArray(",", roleid);
             List<ZTreeNode> roleTreeListByUserId = this.sysRoleService.roleTreeListByRoleId(strArray);
             return roleTreeListByUserId;
         }
     }
}
