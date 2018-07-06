package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeAnswerLogMapper;
import com.ffxl.cloud.model.SgThemeAnswerLog;
import com.ffxl.cloud.model.SgThemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeAnswerLogExample.Criteria;
import com.ffxl.cloud.service.SgThemeAnswerLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgThemeAnswerLogServiceImpl extends GenericServiceImpl<SgThemeAnswerLog, SgThemeAnswerLogExample, String> implements SgThemeAnswerLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeAnswerLogServiceImpl.class);

    @Autowired
    private SgThemeAnswerLogMapper sgThemeAnswerLogMapper;

    @Override
    public GenericMapper<SgThemeAnswerLog, SgThemeAnswerLogExample, String> getGenericMapper() {
        return sgThemeAnswerLogMapper;
    }

    public SgThemeAnswerLog queryByModel(SgThemeAnswerLog sgThemeAnswerLog) {
        SgThemeAnswerLogExample example = new SgThemeAnswerLogExample();
        Criteria c= example.createCriteria();
        List<SgThemeAnswerLog> modelList =  sgThemeAnswerLogMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public SgThemeAnswerLog queryAnswerLogByUser(String userId, String themeId) {
		return sgThemeAnswerLogMapper.queryAnswerLogByUser(userId,themeId);
	}

	@Override
	public List<SgThemeAnswerLog> queryMyTheme(String userId) {
		List<SgThemeAnswerLog> list = sgThemeAnswerLogMapper.queryMyTheme(userId);
		for(SgThemeAnswerLog sl:list){
			String num = toChinese(sl.getNum());
			sl.setStage("第"+num+"期");
		}
		return list;
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