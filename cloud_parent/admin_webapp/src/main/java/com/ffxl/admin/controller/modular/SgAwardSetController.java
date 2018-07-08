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
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.DictionaryDic;
import com.ffxl.admin.core.log.LogObjectHolder;
import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.service.DictionaryService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.exception.BusinessException;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.util.UUIDUtil;

/**
 * 奖品设置
 * @author feifan
 *
 */
@Controller
@RequestMapping("/sg_award_set")
public class SgAwardSetController extends BaseController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SgAwardSetController.class);
private static String PREFIX = "/fzsg/sg_award_set/";
	
	@Autowired
	private DictionaryService dictionaryService;
	/**
     * 跳转到奖品列表的页面
     */
    @RequestMapping("/award_set_list")
    public String index() {
        return PREFIX + "award_list.html";
    }
    /**
     * 查询奖品列表
     */
    @RequestMapping("/award_pageList")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables,Page page,Dictionary dictionary) {
    	dictionary.setPid("6");
    	page = this.getPageInfo(page,dataTables);
    	List<Dictionary> dataList = dictionaryService.queryPageList(dictionary,page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult("2000", dataTables);
        
    }
    /**
     * 跳转到查看奖品新增的页面
     */
    @RequestMapping("/add_award")
    public String addaward(Model model) {
        return PREFIX + "add_award.html";
    }
    /**
     * 跳转到查看奖品修改的页面
     */
    @RequestMapping("/edit_award")
    public String editaward(String id,Model model) {
    	if (StringUtil.isEmpty(id)) {
            throw new BusinessException(Message.M6002);
        }
    	Dictionary info = dictionaryService.selectByPrimaryKey(id);
        model.addAttribute("info", info);
        LogObjectHolder.me().set(info);
        return PREFIX + "edit_award.html";
    }
    /**
     * 新增
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "新增奖品", key = "id", dict = DictionaryDic.class)
    @RequestMapping("/add")
    public JsonResult add(Dictionary dictionary){
    	dictionary.setId(UUIDUtil.getUUID());
    	int num = dictionaryService.queryMaxNumByPid(dictionary.getPid());
    	dictionary.setNum(num+1);
    	int i = dictionaryService.insertSelective(dictionary);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 修改
     * @param sgLaw
     * @param session
     * @return
     */
    @ResponseBody
    @BussinessLog(value = "修改奖品", key = "id", dict = DictionaryDic.class)
    @RequestMapping("/edit")
    public JsonResult edit(Dictionary dictionary){
    	int i = dictionaryService.updateByPrimaryKeySelective(dictionary);
    	if(i > 0){
    		return new JsonResult(true);
        }else{
        	return new JsonResult(false);
        }
    }
    /**
     * 删除
     * @param ids
     * @return
     */
   @RequestMapping("/del")
   @BussinessLog(value = "删除奖品", key = "id", dict = DictionaryDic.class)
   @ResponseBody
   public JsonResult del(String ids){
   	if (StringUtil.isEmpty(ids)) {
   		return new JsonResult(Message.M4002);
       }
   	int ret = -1;
   	String[] idArray = ids.split(",");
   	for(String id:idArray ){
   		ret = dictionaryService.deleteByPrimaryKey(id);
   	}
   	if(ret >0){
      	 return new JsonResult(Message.M2000);
      }else{
      	 return new JsonResult(Message.M5000);
      }
   	
   }
   

}
