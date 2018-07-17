package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.SgLaw;
import com.ffxl.cloud.model.SgLawExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface SgLawService extends GenericService<SgLaw, SgLawExample, String> {
     /**
     * According to the model information query object  
     * @param BaseSgLaw
     * @return 
     **/
    SgLaw queryByModel(SgLaw sgLaw);
    /**
     * 查询苏供法宝
     * @param sgLaw
     * @param page
     * @return
     */
	List<SgLaw> queryPageList(SgLaw sgLaw, Page page);
	/**
	 * 上下移
	 * @param bb
	 * @return
	 */
	int updateSort(SgLaw bb);
	/**
	 * 查询最大号
	 * @param code
	 * @param category
	 * @return
	 */
	int selectMaxSort(String id,String code, String category);
	/**
	 * 首页的法治动态和法律讲堂
	 * @param category
	 * @param page
	 * @return
	 */
	List<SgLaw> querySgLawByPage(String category, Page page);
	/**
	 * 查询最大号
	 * @param string
	 * @return
	 */
	int selectMaxSortNum(String string);
	/**
	 * 上一篇
	 * @param sl
	 * @return
	 */
	SgLaw queryNextLawRoom(SgLaw sl);
	/**
	 * 下一篇
	 * @param sl
	 * @return
	 */
	SgLaw queryPreLawRoom(SgLaw sl);
	/**
	 * 下一章
	 * @param sl
	 * @return
	 */
	SgLaw queryNextLawMagic(SgLaw sl);
	/**
	 * 上一章
	 * @param sl
	 * @return
	 */
	SgLaw queryPreLawMagic(SgLaw sl);
	/**
	 * 推荐阅读
	 * @param sgLaw
	 * @return
	 */
	List<SgLaw> queryRandLawRisk(SgLaw sgLaw);
	/**
	 * 苏供法宝库
	 * @param s
	 * @return
	 */
	List<SgLaw> querySgMagic(SgLaw s);
	/**
	 * 法律风险
	 * @param s
	 * @return
	 */
	List<SgLaw> queryLawRiskByMajor(SgLaw s,Page page);
}