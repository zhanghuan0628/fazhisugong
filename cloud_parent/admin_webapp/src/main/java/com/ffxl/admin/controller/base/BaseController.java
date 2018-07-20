package com.ffxl.admin.controller.base;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.admin.core.util.MenuKit;
import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.core.support.HttpKit;

public class BaseController {
    @Autowired
    private HttpSession session;
    
    private Model model;
    
    protected static String REDIRECT = "redirect:";
    protected static String FORWARD = "forward:";
	
    protected HttpServletRequest getHttpServletRequest() {
        return HttpKit.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return HttpKit.getResponse();
    }

    protected HttpSession getSession() {
        return session;
    }

    protected HttpSession getSession(Boolean flag) {
        return session;
    }

    protected String getPara(String name) {
        return HttpKit.getRequest().getParameter(name);
    }

    protected void setAttr(String name, Object value) {
        HttpKit.getRequest().setAttribute(name, value);
    }
    
	

	
	public Page getPageInfo(Page page ,DataTablesUtil dataTables){
		if(dataTables.getLength()  == 0){
			dataTables.setLength(Integer.valueOf(Page.DEFAULT_PAGE_SIZE));
		}
		page.setPageNo(dataTables.getStart()/dataTables.getLength() +1);
		page.setPageSize(dataTables.getLength());
		return page;
	}
	
	/**
	 * 把service层的分页信息，封装为dataTable通用的分页封装(分页)
	 * @param page
	 * @param dataTables
	 * @param dataList
	 * @return
	 */
	public DataTablesUtil getDataTables(Page page,DataTablesUtil dataTables,List dataList){
		dataTables.setRecordsTotal(page.getTotalRecord());
		dataTables.setRecordsFiltered(page.getTotalRecord());
		dataTables.setData(dataList);
		return dataTables;
	}
	
	/**
	 *  把service层的分页信息，封装为dataTable通用的分页封装(不分页)
	 * @param dataTables
	 * @param dataList
	 * @return
	 */
	public DataTablesUtil getDataTables(DataTablesUtil dataTables,List dataList){
		dataTables.setRecordsTotal(dataList.size());
		dataTables.setRecordsFiltered(dataList.size());
		dataTables.setData(dataList);
		return dataTables;
	}
}
