package com.ffxl.admin.core.util;

import java.util.ArrayList;
import java.util.List;

import com.ffxl.admin.core.common.constant.factory.ConstantFactory;
import com.ffxl.cloud.model.Dictionary;

/**
 * 字典工具类
 * @author jiawei
 * 2018年7月2日
 */
public class DictKit {

    /**
     * 查询字典通过pid
     * jaiwei
     * 2018年7月2日下午6:29:31
     * @param id
     * @return
     */
    public List<Dictionary> findInDict(String id) {
        ArrayList<Dictionary> dictList = new ArrayList<Dictionary>();
        dictList  = (ArrayList<Dictionary>) ConstantFactory.me().findInDict(id);
        return dictList;
    }
}
