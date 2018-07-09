package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeAwardLogMapper;
import com.ffxl.cloud.model.SgThemeAwardLog;
import com.ffxl.cloud.model.SgThemeAwardLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeAwardLogExample.Criteria;
import com.ffxl.cloud.service.SgThemeAwardLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgThemeAwardLogServiceImpl extends GenericServiceImpl<SgThemeAwardLog, SgThemeAwardLogExample, String> implements SgThemeAwardLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeAwardLogServiceImpl.class);

    @Autowired
    private SgThemeAwardLogMapper sgThemeAwardLogMapper;

    @Override
    public GenericMapper<SgThemeAwardLog, SgThemeAwardLogExample, String> getGenericMapper() {
        return sgThemeAwardLogMapper;
    }

    public SgThemeAwardLog queryByModel(SgThemeAwardLog sgThemeAwardLog) {
        SgThemeAwardLogExample example = new SgThemeAwardLogExample();
        Criteria c= example.createCriteria();
        List<SgThemeAwardLog> modelList =  sgThemeAwardLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgThemeAwardLog> queryThemePageList(SgThemeAwardLog model, Page page) {
		List<SgThemeAwardLog> list = sgThemeAwardLogMapper.queryThemePageList(model,page);
		for(SgThemeAwardLog sd:list){
			String num = toChinese(sd.getNum());
			sd.setStage("第"+num+"期");
		}
		return sgThemeAwardLogMapper.queryThemePageList(model,page);
	}
	/**
     * 阿拉伯数组转中文數字【十万九千零六十  --> 109060】
     * @author
     * @param chineseNumber
     * @return
     */
	public String toChinese(String string) {
        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;
	}
}