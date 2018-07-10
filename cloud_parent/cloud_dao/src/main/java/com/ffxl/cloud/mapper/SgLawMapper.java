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
	/**
	 * 首页的法治动态和法律讲堂
	 * @param category
	 * @param page
	 * @return
	 */
	List<SgLaw> querySgLawByPage(@Param("category")String category, @Param("page")Page page);
	/**
	 * 查询最大sortnum
	 * @param category
	 * @return
	 */
	int selectMaxSortNum(@Param("category")String category);
	/**
	 * 上一篇
	 * @param sl
	 * @return
	 */
	SgLaw queryNextLawRoom(@Param("model")SgLaw sl);
	/**
	 * 下一篇
	 * @param sl
	 * @return
	 */
	SgLaw queryPreLawRoom(@Param("model")SgLaw sl);
	/**
	 * 下一章
	 * @param sl
	 * @return
	 */
	SgLaw queryNextLawMagic(@Param("model")SgLaw sl);
	/**
	 * 上一章
	 * @param sl
	 * @return
	 */
	SgLaw queryPreLawMagic(@Param("model")SgLaw sl);
	/**
	 * 推荐阅读
	 * @param sgLaw
	 * @return
	 */
	List<SgLaw> queryRandLawRisk(@Param("model")SgLaw sgLaw);
	/**
	 * 苏供法宝库
	 * @param s
	 * @return
	 */
	List<SgLaw> querySgMagic(@Param("model")SgLaw s);
	/**
	 * 法律风险
	 * @param s
	 * @return
	 */
	List<SgLaw> queryLawRiskByMajor(@Param("model")SgLaw s);
}