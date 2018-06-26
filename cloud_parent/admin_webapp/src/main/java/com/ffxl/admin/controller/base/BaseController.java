package com.ffxl.admin.controller.base;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.ffxl.platform.core.DataTablesUtil;
import com.ffxl.platform.core.Page;

public class BaseController {
	
	@Autowired  
    private HttpServletRequest request;
	
	@Autowired  
    private HttpSession session;  
	
	/**
	 * 得到ContextPath
	 */
	public String getBasePath(String url){
		return request.getContextPath()+url;
	}
	
	public Page defaultBTPage() {
		Page page =new Page();
        int limit = Integer.valueOf(request.getParameter("limit"));     //每页多少条数据
        int offset = Integer.valueOf(request.getParameter("offset"));   //每页的偏移量(本页当前有多少条)
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        page.setPageNo((offset / limit + 1));
		page.setPageSize(limit);
		return page;
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
