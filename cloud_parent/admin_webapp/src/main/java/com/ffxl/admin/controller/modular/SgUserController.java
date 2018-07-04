package com.ffxl.admin.controller.modular;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.SgLawCommentDic;
import com.ffxl.admin.core.common.constant.dictmap.SgUserDic;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgThmemeAnswerLog;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.SysUser;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgLawCommentService;
import com.ffxl.cloud.service.SgThmemeAnswerLogService;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.cloud.service.impl.SysUserServiceImpl;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
/**
 * 用户列表
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_user")
public class SgUserController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
	
	private static String PREFIX = "/fzsg/user/";
	
	@Autowired
	private SgUserService sgUserService;
	
	@Autowired
	private SgThmemeAnswerLogService sgThmemeAnswerLogService;
	
	@Autowired
	private SgAskService sgAskService;
	
	@Autowired
	private SgLawCommentService sgLawCommentService;
	
	/**
     * 跳转到用户列表的页面
     */
    @RequestMapping("/user_list")
    public String index() {
        return PREFIX + "userList.html";
    }
    /**
     * 跳转到虚拟用户列表的页面
     */
    @RequestMapping("/dummy_user")
    public String dummyUser() {
        return PREFIX + "dummy_user.html";
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
     * 查询用户列表
     */
    @RequestMapping("/dummy_user_list")
    @ResponseBody
    public JsonResult dummyUserList(DataTablesUtil dataTables,Page page,SgUser sgUser) {
    	sgUser.setDummy(true);
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
    @BussinessLog(value = "编辑用户密码", key = "id", dict = SgUserDic.class)
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
    /**
     * 跳转到用户详情的页面
     */
    @RequestMapping("/user_detail")
    public String userDetail(String id,Model model) {
    	SgUser user = sgUserService.selectByPrimaryKey(id);
    	SgThmemeAnswerLog sl = sgThmemeAnswerLogService.queryThemeAnswerUser(id);
    	model.addAttribute("model", sl);
    	model.addAttribute("id", id);
    	model.addAttribute("info", user);
        return PREFIX + "user_detail.html";
    }
    /**
     * 查询用户列表
     */
    @RequestMapping("/user_ask_list")
    @ResponseBody
    public JsonResult userAskList(DataTablesUtil dataTables,Page page,SgAsk sgAsk) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgAsk> dataList = sgAskService.queryPageList(sgAsk,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到用户详情的页面
     */
    @RequestMapping("/user_ask_detail")
    public String userAskDetail(String id,Model model) {
        SgAsk user = sgAskService.queryUserAsk(id);
        model.addAttribute("info", user);
        return PREFIX + "user_ask_detail.html";
    }
    /**
     * 跳转到咨询师回复的页面
     */
    @RequestMapping("/counselor_back")
    public String counselorBack(String id,Model model) {
        SgAsk user = sgAskService.queryUserAsk(id);
        model.addAttribute("info", user);
        return PREFIX + "counselor_back.html";
    }
    /**
     * 新增咨询师回复
     * @param sgLaw
     * @param session
     * @return
     */
    /*@ResponseBody
    @BussinessLog(value = "新增咨询师回复", key = "id", dict = SgLawCommentDic.class)
    @RequestMapping("/add")
    public JsonResult add(SgLawComment sgLawComment,HttpSession session){
    	sgLawComment.setId(UUIDUtil.getUUID());
    	sgLawComment.setCreateDate(new Date());
    	int i= sgLawCommentService.insertSelective(sgLawComment);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    	
    }*/
    /**
     * 修改咨询师回复
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "修改咨询师回复", key = "id", dict = SgLawCommentDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(SgLawComment sgLawComment,HttpSession session){
    	int i= sgLawCommentService.updateByPrimaryKeySelective(sgLawComment);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 删除咨询
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "删除咨询", key = "id", dict = SgLawCommentDic.class)
    @RequestMapping("/del")
    public JsonResult del(String id){
    	int i= sgAskService.deleteByPrimaryKey(id);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
    }
    /**
     * 跳转到新增虚拟用户详情的页面
     */
    @RequestMapping("/add_dummy_user")
    public String addDummyUser(String id,Model model) {
        return PREFIX + "add_dummy_user.html";
    }
    /**
     * 新增虚拟用户
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "新增虚拟用户", key = "id", dict = SgUserDic.class)
    @RequestMapping("/add_dummy")
    public JsonResult addDummy(SgUser SgUser){
    	SgUser.setId(UUIDUtil.getUUID());
    	SgUser.setPassword("123456");
    	SgUser.setDummy(true);
    	int i = sgUserService.insertSelective(SgUser);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    	
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
        SgUser model = new SgUser();
        model.setLoginName(loginName);
        SgUser theUser = sgUserService.queryByModel(model);
        if (theUser != null) {
            return false;
        }else{
            return true;
        }
    }
}
