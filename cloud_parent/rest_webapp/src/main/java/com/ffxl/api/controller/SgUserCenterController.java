package com.ffxl.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgUser;
import com.ffxl.cloud.model.SgUserExample;
import com.ffxl.cloud.model.SgUserFavorite;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.cloud.model.base.BaseSgUserExample.Criteria;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.cloud.service.SgUserFavoriteService;
import com.ffxl.cloud.service.SgUserService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.util.StringUtil;

/**
 * 个人中心
 * @author feifan
 *
 */
@Controller
@RequestMapping(value = "/SgUserCenterController")
public class SgUserCenterController {
	@Autowired
	private SgUserService sgUserService;
	
	@Autowired
	private SgAskService sgAskService;
	
	@Autowired
	private SgUserFavoriteService sgUserFavoriteService;
	
	@Autowired
	private SgThemeAnswerLogService sgThemeAnswerLogService;
	/**
	 * 登陆
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/userLogin")
    @ResponseBody
	public JsonResult userLogin(String loginName,String password){
		if(StringUtil.isEmpty(loginName)||StringUtil.isEmpty(password)){
			return new JsonResult(Message.M4003);
		}
		SgUserExample example = new SgUserExample();
        Criteria c= example.createCriteria();
        c.andLoginNameEqualTo(loginName);
        c.andPasswordEqualTo(password);
		List<SgUser> list = sgUserService.selectByExample(example);
		if(list != null && list.size() > 0){
			SgUser user = list.get(0);
			return new JsonResult(Message.M2000,user);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserInfo")
    @ResponseBody
	public JsonResult updateUserInfo(SgUser user){
		if(StringUtil.isEmpty(user.getId())){
			return new JsonResult(Message.M4003);
		}
		int i = sgUserService.updateByPrimaryKeySelective(user);
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 登陆提醒用户修改密码
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryUserModifyPwd")
    @ResponseBody
	public JsonResult queryUserModifyPwd(String userId){
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		SgUser u = sgUserService.selectByPrimaryKey(userId);
		if(u!=null){
			if(u.getModifyPassword()==true){
				return new JsonResult(Message.M2000,"已修改过密码");
			}else{
				return new JsonResult("1","提醒用户修改密码");
			}
		}else{
			return new JsonResult(Message.M5000,"没有从此用户");
		}
		
	}
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserPassword")
    @ResponseBody
	public JsonResult updateUserPassword(String userId,String newPwd,String oldPwd){
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		SgUser u = sgUserService.selectByPrimaryKey(userId);
		int i = -1;
		if(u!=null){
			if(u.getPassword().equals(oldPwd)){
				SgUser user = new SgUser();
				user.setId(userId);
				user.setPassword(newPwd);
				user.setModifyPassword(true);
				i = sgUserService.updateByPrimaryKeySelective(user);
			}else{
				return new JsonResult("1","密码输入错误");
			}
		}else{
			return new JsonResult(Message.M5000,"没有从此用户");
		}
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 我的咨询
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/queryMyAsk")
    @ResponseBody
	public JsonResult queryMyAsk(String userId){
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		List<SgAsk> list = sgAskService.queryMyAsk(userId);
		return new JsonResult(Message.M2000,list);
	}
	/**
	 * 我的法官
	 * @return
	 */
	@RequestMapping(value = "/queryMyThemeLog")
    @ResponseBody
	public JsonResult queryMyThemeLog(String userId){
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		List<SgThemeAnswerLog> list = sgThemeAnswerLogService.queryMyTheme(userId);
		return new JsonResult(Message.M2000,list);
		
	}
	/**
	 * 我的收藏
	 * @return
	 */
	@RequestMapping(value = "/queryMyFavorite")
    @ResponseBody
	public JsonResult queryMyFavorite(String userId,String sourceType){
		if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(sourceType)){
			return new JsonResult(Message.M4003);
		}
		List<SgUserFavorite> list = sgUserFavoriteService.queryMyFavorite(userId,sourceType);
		return new JsonResult(Message.M2000,list);
		
	}
	
}
