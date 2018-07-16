package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.platform.core.GenericService;
import com.ffxl.platform.core.Page;

 /**
 * Generate By MBG 
 **/
public interface DictionaryService extends GenericService<Dictionary, DictionaryExample, String> {
     /**
     * According to the model information query object  
     * @param BaseDictionary
     * @return 
     **/
    Dictionary queryByModel(Dictionary dictionary);

    /**
     * 查询所有字典项根据父级字典
     * jaiwei
     * 2018年7月2日下午5:36:29
     * @param qm
     * @return
     */
    List<Dictionary> queryListByPid(Dictionary qm);
    /**
     * 查询专业列表
     * @param dictionary
     * @param page
     * @return
     */
	List<Dictionary> queryPageList(Dictionary dictionary, Page page);
	/**
	 * 查询最大num
	 * @param pid
	 * @return
	 */
	int queryMaxNumByPid(String pid);
	/**
	 * 抽奖
	 * @return
	 */
	Dictionary queryAllAward();
	/**
	 * 查询字典列表
	 */
	List<Dictionary> queryDictPageList(Dictionary dictionary, Page page);
	/**
	 * 新增字典
	 * @param dictName
	 * @param dictValues
	 * @return
	 */
	int addDict(String dictName, String dictValues,String tips);
	/**
	 * 修改字典
	 * @param dictId
	 * @param dictName
	 * @param dictValues
	 * @return
	 */
	int editDict(String dictId, String dictName, String dictValues,String tips);
}