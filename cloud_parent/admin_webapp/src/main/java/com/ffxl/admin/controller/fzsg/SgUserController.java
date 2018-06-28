package com.ffxl.admin.controller.fzsg;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 用户列表
 * @author feifan
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.cloud.service.impl.SysUserServiceImpl;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
@Controller
@RequestMapping("/sg_user")
public class SgUserController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private static String PREFIX = "/fzsg/user/";
	
	@Autowired
	private SgUserService sgUserService;
	
	/**
     * 跳转到用户列表的页面
     */
    @RequestMapping("/user_list")
    public String index() {
        return PREFIX + "userList.html";
    }
    
    /**
     * 查询用户列表
     */
    @RequestMapping("/userlist")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgUser sgUser) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgUser> dataList = sgUserService.queryPageList(sgUser,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 初始化用户密码
     * @return
     */
    @RequestMapping("/updatePassword")
    @ResponseBody
    public JsonResult updatePassword(String id){
    	if(StringUtil.isEmpty(id)){
    		return new JsonResult(Message.M5000);
    	}
    	SgUser record = new SgUser();
    	record.setId(id);
    	record.setPassword("123456");
    	sgUserService.updateByPrimaryKeySelective(record);
    	return new JsonResult(Message.M2000);
    	
    }
}
