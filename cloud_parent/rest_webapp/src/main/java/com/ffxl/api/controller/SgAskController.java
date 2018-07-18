package com.ffxl.api.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.cloud.model.SgAsk;
import com.ffxl.cloud.model.SgAskComment;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgSubject;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgUserFavoriteExample;
import com.ffxl.cloud.model.base.BaseDictionaryExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.cloud.service.SgAskCommentService;
import com.ffxl.cloud.service.SgAskService;
import com.ffxl.cloud.service.SgSubjectService;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.cloud.service.SgThemeAwardLogService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.HttpHeader;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;
/**
 * 答题
 * @author feifan
 *
 */
@Controller
@RequestMapping(value = "/SgAskController")
public class SgAskController {
	@Autowired
	private SgAskService sgAskService;
	
	@Autowired
	private SgAskCommentService sgAskCommentService;
	
	@Autowired
	private SgSubjectService sgSubjectService;
	
	@Autowired
	private SgThemeAnswerLogService sgThemeAnswerLogService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SgThemeAwardLogService sgThemeAwardLogService;
	/**
	 * 在线咨询
	 * @param ask
	 * @return
	 */
	@RequestMapping(value = "/insertUserAsk")
    @ResponseBody
	public JsonResult insertUserAsk(SgAsk ask){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
        if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		ask.setId(UUIDUtil.getUUID());
		ask.setCreateDate(new Date());
		ask.setUserId(userId);
		int i = sgAskService.insertSelective(ask);
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 查询所有咨询回答
	 * @return
	 */
	@RequestMapping(value = "/queryAllAskComment")
    @ResponseBody
	public JsonResult queryAllAskComment(Page page){
		List<SgAskComment> list = sgAskCommentService.queryAllAskComment(null,page);
        page.setDataList(list);
        return new JsonResult(Message.M2000, page);
		
	}
	/**
	 * 查询咨询回答详情
	 * @return
	 */
	@RequestMapping(value = "/queryAskCommentDetail")
    @ResponseBody
	public JsonResult queryAskCommentDetail(String topicId){
		if(StringUtil.isEmpty(topicId)){
			return new JsonResult(Message.M4003);
		}
		SgAsk sgAsk = sgAskService.queryUserAskById(topicId);
        return new JsonResult(Message.M2000,sgAsk);
		
	}
	/**
	 * 查找是否有活动正在进行中的
	 * @return
	 */
	@RequestMapping(value = "/queryCheckTheme")
    @ResponseBody
	public JsonResult queryCheckTheme(){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
		if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		Map map = sgSubjectService.queryCheckTheme(userId);
		if(map != null && !map.isEmpty()){
			return new JsonResult(Message.M2000,map);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 根据活动找到题库
	 * @param theme
	 * @return
	 */
	@RequestMapping(value = "/querySubjectByTheme")
    @ResponseBody
	public JsonResult querySubjectByTheme(String themeId,int num){
		if(StringUtil.isEmpty(themeId)||StringUtil.isEmpty(num+"")){
			return new JsonResult(Message.M4003);
		}
		List<SgSubject> list = sgSubjectService.querySubjectByTheme(themeId,num);
		return new JsonResult(Message.M2000,list);
		
	}
	/**
	 * 新增答题记录
	 * @param sgThemeAnswerLog
	 * @return
	 */
	@RequestMapping(value = "/insertAnswerLog")
    @ResponseBody
	public JsonResult insertAnswerLog(SgThemeAnswerLog sgThemeAnswerLog){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
        if(StringUtil.isEmpty(userId)){
			return new JsonResult(Message.M4003);
		}
		SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
        com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria c= example.createCriteria();
        c.andUserIdEqualTo(userId);
        c.andThemeIdEqualTo(sgThemeAnswerLog.getThemeId());
        List<SgThemeAnswerLog> list = sgThemeAnswerLogService.selectByExample(example);
        int i = 0;
        if(list != null && list.size() > 0){
        	return new JsonResult("4","已完成答题，不可重复答题");
        }else{
        	sgThemeAnswerLog.setId(UUIDUtil.getUUID());
    		sgThemeAnswerLog.setCreateDate(new Date());
            sgThemeAnswerLog.setUserId(userId);
    		i = sgThemeAnswerLogService.insertSelective(sgThemeAnswerLog);
        }
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 用户回答详情
	 * @param userId
	 * @param themeId
	 * @return
	 */
	@RequestMapping(value = "/queryAnswerLogByUser")
    @ResponseBody
	public JsonResult queryAnswerLogByUser(String themeId){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
		if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(themeId)){
			return new JsonResult(Message.M4003);
		}
		SgThemeAnswerLog model = sgThemeAnswerLogService.queryAnswerLogByUser(userId,themeId);
		return new JsonResult(Message.M2000,model);
		
	}
	/**
	 * 回顾考题
	 * @param userId
	 * @param themeId
	 * @return
	 */
	@RequestMapping(value = "/queryUserBackTheme")
    @ResponseBody
	public JsonResult queryUserBackTheme(String themeId){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
		if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(themeId)){
			return new JsonResult(Message.M4003);
		}
		List<SgSubject> list = sgThemeAnswerLogService.queryUserBackTheme(userId,themeId);
		return new JsonResult(Message.M2000,list);
		
	}
	/**
	 * 抽奖
	 * @return
	 */
	@RequestMapping(value = "/queryAllAward")
    @ResponseBody
	public JsonResult queryAllAward(SgThemeAwardLog model){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
		if(StringUtil.isEmpty(userId)||StringUtil.isEmpty(model.getAnswerId())||StringUtil.isEmpty(model.getThemeId())){
			return new JsonResult(Message.M4003);
		}
		Dictionary m = dictionaryService.queryAllAward();
		model.setId(UUIDUtil.getUUID());
		model.setAwardCode(m.getId());
		model.setCode(m.getCode());
		model.setUserId(userId);
		this.insertUserAwardLog(model);
		return new JsonResult(Message.M2000,m);
	}
	/**
	 * 记录用户抽奖记录
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/insertUserAwardLog")
    @ResponseBody
	public JsonResult insertUserAwardLog(SgThemeAwardLog model){
		HttpHeader local= HttpHeader.get();
        String userId = local.getUserId();
        model.setUserId(userId);
		int i = sgThemeAwardLogService.insertSelective(model);
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
	}
}
