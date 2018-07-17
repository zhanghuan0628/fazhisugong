package com.ffxl.cloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface DictionaryMapper extends GenericMapper<Dictionary, DictionaryExample, String> {
	/**
	 * 查询专业列表
	 * @param dictionary
	 * @param page
	 * @return
	 */
	List<Dictionary> queryPageList(@Param("model")Dictionary dictionary, @Param("page")Page page);
	/**
	 * 查询最大num
	 * @param pid
	 * @return
	 */
	int queryMaxNumByPid(@Param("pid")String pid);
	/**
	 * 查询字典列表
	 * @param dictionary
	 * @param page
	 * @return
	 */
	List<Dictionary> queryDictPageList(@Param("model")Dictionary dictionary, @Param("page")Page page);
	/**
	 * 查询专业
	 * @param d
	 * @return
	 */
	List<Dictionary> selectByMajor(@Param("model")Dictionary d);
}