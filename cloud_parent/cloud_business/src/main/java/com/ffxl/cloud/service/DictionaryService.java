package com.ffxl.cloud.service;

import java.util.List;

import com.ffxl.cloud.model.Dictionary;
import com.ffxl.cloud.model.DictionaryExample;
import com.ffxl.platform.core.GenericService;

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
}