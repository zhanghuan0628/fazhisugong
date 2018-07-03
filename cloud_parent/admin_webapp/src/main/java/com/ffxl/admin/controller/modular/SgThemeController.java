package com.ffxl.admin.controller.modular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

/**
 * 我是法官
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_theme")
public class SgThemeController extends BaseController{
	@Autowired
	private SgThemeService sgThemeService;
	
	private static String PREFIX = "/fzsg/sg_theme/";
	/**
     * 跳转到答题人数管理的页面
     */
    @RequestMapping("/answer_person_num")
    public String index() {
        return PREFIX + "answer_person_num.html";
    }
    /**
     * 查询答题人数管理列表
     */
    @RequestMapping("/answer_num_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,SgTheme sgTheme) {
    	page = this.getPageInfo(page,dataTables);
    	List<SgTheme> dataList = sgThemeService.queryPageList(sgTheme,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
}
