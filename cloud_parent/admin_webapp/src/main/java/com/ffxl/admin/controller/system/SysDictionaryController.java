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
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.cloud.model.base.BaseDictionaryExample.Criteria;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.ToolUtil;

/**
 * 字典控制器
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sys_dict")
public class SysDictionaryController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String PREFIX = "/system/sys_dict/";

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 跳转到字典管理首页
     */
    @RequestMapping("/dict_list")
    public String index() {
        return PREFIX + "dict_list.html";
    }
    
    /**
     * 查询字典列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables, Page page, Dictionary dictionary) {
        page = this.getPageInfo(page, dataTables);
        List<Dictionary> dataList = dictionaryService.queryDictPageList(dictionary, page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult(true, dataTables);

    }
    /**
     * 跳转到添加字典
     */
    @RequestMapping("/dict_add")
    public String dictAdd() {
        return PREFIX + "dict_add.html";
    }
    
    /**
     * 跳转到修改字典
     */
    @RequestMapping("/dict_edit")
    public String dictEdit(String id, Model model) {
        Dictionary dict = dictionaryService.selectByPrimaryKey(id);
        model.addAttribute("dict", dict);
        DictionaryExample example = new DictionaryExample();
        Criteria c= example.createCriteria();
        c.andPidEqualTo(id);
        List<Dictionary> subDicts = dictionaryService.selectByExample(example);
        model.addAttribute("subDicts", subDicts);
        LogObjectHolder.me().set(dict);
        return PREFIX + "dict_edit.html";
    }
    
    /**
     * 新增字典
     *
     * @param dictValues 格式例如   "1:启用;2:禁用;3:冻结"
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Boolean add(String dictName, String dictValues,String tips) {
        if (ToolUtil.isOneEmpty(dictName, dictValues)) {
            return false;
        }
        int i = dictionaryService.addDict(dictName, dictValues,tips);
        if(i > 0){
        	return true;
        }else{
        	return false;
        }
    }
    
    /**
     * 修改字典
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Boolean update(String dictId, String dictName, String dictValues,String tips) {
        if (ToolUtil.isOneEmpty(dictId, dictName, dictValues)) {
        	return false;
        }
        int i = dictionaryService.editDict(dictId, dictName, dictValues,tips);
        if(i > 0){
        	return true;
        }else{
        	return false;
        }
    }
    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del")
    @ResponseBody
    public JsonResult del(String ids){
    	String[] idss = ids.split(",");
    	int i = 0;
    	for(String id:idss){
    		i = dictionaryService.deleteByPrimaryKey(id);
    		DictionaryExample example = new DictionaryExample();
            Criteria c= example.createCriteria();
            c.andPidEqualTo(id);
    		i = dictionaryService.deleteByExample(example);
    	}
    	if(i > 0){
        	return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    	
    }
}
