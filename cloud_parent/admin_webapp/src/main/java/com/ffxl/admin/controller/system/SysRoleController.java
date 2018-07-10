package com.ffxl.admin.controller.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.cloud.model.SysRole;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.SysRoleService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

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
}
