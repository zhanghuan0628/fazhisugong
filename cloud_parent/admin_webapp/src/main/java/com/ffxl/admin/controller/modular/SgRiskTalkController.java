package com.ffxl.admin.controller.modular;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.cloud.model.SgLawComment;
import com.ffxl.cloud.service.SgLawCommentService;
import com.ffxl.cloud.service.SgLawService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

/**
 * 法律风险评论
 * @author feifan
 *
 */
@Controller
@RequestMapping("/risk_talk")
public class SgRiskTalkController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SgRiskTalkController.class);
	@Autowired
	private SgLawService sgLawService;
	
	@Autowired
	private SgLawCommentService sgLawCommentService;
	
	private static String PREFIX = "/fzsg/law_risk/";
	
	/**
     * 查询法律风险评论列表的页面
     */
    @RequestMapping("/talk_list")
    public String lawriskDetail(String id,String title,Model model) {
        return PREFIX + "talk_list.html";
    }
	
	/**
     * 查询法律风险评论列表
     */
    @RequestMapping("/law_risk_comment")
    @ResponseBody
    public JsonResult law_risk_comment(DataTablesUtil dataTables,Page page,SgLawComment sgLawComment) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgLawComment> dataList = sgLawCommentService.queryPageList(sgLawComment,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }

}
