package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgLawMapper extends GenericMapper<SgLaw, SgLawExample, String> {
	/**
	 * 苏供法宝
	 * @param sgLaw
	 * @param page
	 * @return
	 */
	List<SgLaw> queryPageList(@Param("model")SgLaw sgLaw, @Param("page")Page page);
	/**
	 * 上下移
	 * @param bb
	 * @return
	 */
	int updateSort(@Param("model")SgLaw bb);
	/**
	 * 查询最大号
	 * @param code
	 * @param category
	 * @return
	 */
	int selectMaxSort(@Param("id")String id,@Param("code")String code, @Param("category")String category);
}