package com.ffxl.admin.controller.system;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ffxl.admin.controller.base.BaseController;
import com.ffxl.admin.core.common.annotion.BussinessLog;
import com.ffxl.admin.core.common.constant.dictmap.LoginLogDic;
import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.service.SysLoginLogService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

/**
 * 登录日志
 * @author feifan
 *
 */
@Controller
@RequestMapping("/login_log")
public class LoginLogController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String PREFIX = "/system/login_log/";
	
	@Autowired
    private SysLoginLogService sysLoginLogService;
	
	/**
     * 登录日志首页
     */
    @RequestMapping("/log_list")
    public String index() {
        return PREFIX + "log_list.html";
    }
    /**
     * 查询登录日志列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables, Page page, SysLoginLog sysLoginLog) {
        page = this.getPageInfo(page, dataTables);
        List<SysLoginLog> dataList = sysLoginLogService.queryPageList(sysLoginLog, page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult(true, dataTables);

    }
    /**
     * 删除
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/del")
    @BussinessLog(value = "删除日志", key = "id", dict = LoginLogDic.class)
    @ResponseBody
    public JsonResult del(String ids){
    	String[] idss = ids.split(",");
    	int i = 0;
    	for(String id:idss){
    		i = sysLoginLogService.deleteByPrimaryKey(id);
    	}
    	if (i > 0) {
            return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    	
    }
}
