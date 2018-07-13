package com.ffxl.api.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.cloud.model.base.BaseDictionaryExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.cloud.service.SgLawCommentService;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 法律风险
 * @author feifan
 *
 */
@Controller
@RequestMapping(value = "/SgLawRiskController")
public class SgLawRiskController {
	@Autowired
	private SgLawService sgLawService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	private SgLawCommentService sgLawCommentService;
	
	/**
	 * 法律风险的专业
	 * @return
	 */
	@RequestMapping(value = "/queryAllRiskMajor")
    @ResponseBody
	public JsonResult queryAllRiskMajor(){
		DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        c.andPidEqualTo("4");
        example.setOrderByClause(" num asc ");
		List<Dictionary> list = dictionaryService.selectByExample(example);
		return new JsonResult(Message.M2000,list);
	}
	/**
	 * 查询法律风险通过专业
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryLawRiskByMajor")
    @ResponseBody
	public JsonResult queryLawRiskByMajor(String id,String title,Page page){
		if(StringUtil.isEmpty(id)){
			return new JsonResult(Message.M4003);
		}
        SgLaw s = new SgLaw();
        s.setCategory("law_risk");
        s.setCategoryCode(id);
        s.setTitle(title);
        s.setStatus("publish");
		List<SgLaw> list = sgLawService.queryLawRiskByMajor(s);
		page.setDataList(list);
        return new JsonResult(Message.M2000, page);
	}
	/**
	 * 推荐阅读
	 * @return
	 */
	@RequestMapping(value = "/queryRandLawRisk")
    @ResponseBody
	public JsonResult queryRandLawRisk(){
		SgLaw sgLaw = new SgLaw();
		sgLaw.setCategory("law_risk");
        List<SgLaw> list = sgLawService.queryRandLawRisk(sgLaw);
        return new JsonResult(Message.M2000,list);
		
	}
	/**
	 * 查询法律风险详情
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryLawRiskById")
    @ResponseBody
	public JsonResult queryLawRiskById(String id){
		if(StringUtil.isEmpty(id)){
			return new JsonResult(Message.M4003);
		}
		SgLaw sgLaw = sgLawService.selectByPrimaryKey(id);
		return new JsonResult(Message.M2000,sgLaw);
		
	}
	/**
	 * 留言
	 * @param SgLawComment
	 * @return
	 */
	@RequestMapping(value = "/insertLawComment")
    @ResponseBody
	public JsonResult insertLawComment(SgLawComment record){
		record.setId(UUIDUtil.getUUID());
		record.setCreateDate(new Date());
		record.setTopicType("law");
		record.setFromUserType("sg");
		int i = sgLawCommentService.insertSelective(record);
		if(i > 0){
			return new JsonResult(Message.M2000);
		}else{
			return new JsonResult(Message.M5000);
		}
		
	}
	/**
	 * 留言详情
	 * @return
	 */
	@RequestMapping(value = "/queryLawCommentDetail")
    @ResponseBody
	public JsonResult queryLawCommentDetail(String id){
		if(StringUtil.isEmpty(id)){
			return new JsonResult(Message.M4003);
		}
		List<SgLawComment> list = sgLawCommentService.queryLawCommentDetail(id);
		return new JsonResult(Message.M2000,list);
		
	}
}
