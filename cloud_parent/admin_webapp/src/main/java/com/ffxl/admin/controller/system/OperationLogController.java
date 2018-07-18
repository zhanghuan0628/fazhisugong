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
import com.ffxl.admin.core.common.constant.dictmap.OperationLogDic;
import com.ffxl.cloud.model.SysLoginLog;
import com.ffxl.cloud.model.SysOperationLog;
import com.ffxl.cloud.service.SysOperationLogService;
import com.ffxl.platform.constant.JsonResult;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

/**
 * 业务业务
 * @author feifan
 *
 */
@Controller
@RequestMapping("/operation_log")
public class OperationLogController extends BaseController{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private String PREFIX = "/system/operation_log/";
	@Autowired
    private SysOperationLogService sysOperationLogService;
	/**
     * 业务首页
     */
    @RequestMapping("/log_list")
    public String index() {
        return PREFIX + "log_list.html";
    }
    /**
     * 查询业务列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public JsonResult list(DataTablesUtil dataTables, Page page, SysOperationLog sysOperationLog) {
        page = this.getPageInfo(page, dataTables);
        List<SysOperationLog> dataList = sysOperationLogService.queryPageList(sysOperationLog, page);
        dataTables = this.getDataTables(page, dataTables, dataList);
        return new JsonResult(true, dataTables);

    }
    /**
     * 删除
     * @param menuId
     * @return
     */
    @RequestMapping(value = "/del")
    @BussinessLog(value = "删除业务", key = "id", dict = OperationLogDic.class)
    @ResponseBody
    public JsonResult del(String ids){
    	String[] idss = ids.split(",");
    	int i = 0;
    	for(String id:idss){
    		i = sysOperationLogService.deleteByPrimaryKey(id);
    	}
    	if (i > 0) {
            return new JsonResult(Message.M2000);
        }else{
        	return new JsonResult(Message.M5000);
        }
    	
    }

}
