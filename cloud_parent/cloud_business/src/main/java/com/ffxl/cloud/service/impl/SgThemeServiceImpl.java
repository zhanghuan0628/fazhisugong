package com.ffxl.cloud.service.impl;

import com.ffxl.cloud.mapper.SgThemeMapper;
import com.ffxl.cloud.model.SgTheme;
import com.ffxl.cloud.model.SgThemeExample;
import com.ffxl.cloud.model.SgThmemeAnswerLogExample;
import com.ffxl.cloud.model.base.BaseSgThemeExample.Criteria;
import com.ffxl.cloud.service.SgThemeService;
import com.ffxl.cloud.service.SgThmemeAnswerLogService;
import com.ffxl.platform.core.GenericMapper;
import com.ffxl.platform.core.GenericServiceImpl;
import com.ffxl.platform.core.Page;
import com.ffxl.platform.util.StringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 /**
 * Generate By MBG for serviceImpl
 **/
@Service
public class SgThemeServiceImpl extends GenericServiceImpl<SgTheme, SgThemeExample, String> implements SgThemeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SgThemeServiceImpl.class);

    @Autowired
    private SgThemeMapper sgThemeMapper;
    
    @Autowired
    private SgThmemeAnswerLogService sgThemeAnswerLogService;

    @Override
    public GenericMapper<SgTheme, SgThemeExample, String> getGenericMapper() {
        return sgThemeMapper;
    }

    public SgTheme queryByModel(SgTheme sgTheme) {
        SgThemeExample example = new SgThemeExample();
        Criteria c= example.createCriteria();
        List<SgTheme> modelList =  sgThemeMapper.selectByExample(example);
        if(modelList.size() > 0){
            return modelList.get(0);
        }else{
            return null;
        }
    }

	@Override
	public List<SgTheme> queryPageList(SgTheme sgTheme, Page page) {
		if(!StringUtil.isEmpty(sgTheme.getStage())){
			String str = sgTheme.getStage();
			int begin = str.indexOf("第");
			int end = str.indexOf("期");
			if(begin > -1 && end > -1){
				String num = str.substring(begin+1, end);
				int n = chineseNumber2Int(num);
				sgTheme.setNum(n);
			}else if(begin > -1 && end == -1){
				String num = str.substring(begin+1);
				int n = chineseNumber2Int(num);
				sgTheme.setNum(n);
			}else if(begin == -1 && end > -1){
				String num = str.substring(0, end);
				int n = chineseNumber2Int(num);
				sgTheme.setNum(n);
			}else if(begin == -1 && end == -1){
				int n = chineseNumber2Int(str);
				sgTheme.setNum(n);
			}
		}
		List<SgTheme> list = sgThemeMapper.queryPageList(sgTheme,page);
		for(SgTheme st:list){
			SgThmemeAnswerLogExample example = new SgThmemeAnswerLogExample();
	        com.ffxl.cloud.model.base.BaseSgThmemeAnswerLogExample.Criteria c= example.createCriteria();
	        c.andThemeIdEqualTo(st.getId());
	        int count = sgThemeAnswerLogService.countByExample(example);
			String stage = toChinese(st.getNum().toString());
			st.setStage("第"+stage+"期");
			st.setPersonNum(count);
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String n = sdf.format(now);
			try {
				Date start =  sdf.parse(st.getStartDate());
				Date endd =  sdf.parse(st.getEndDate());
				Date noww =  sdf.parse(n);
				if (noww.compareTo(endd)>0){  
			        st.setState("1");//活动已结束
				}else if(start.compareTo(noww)>0){
					st.setState("2");//活动未开始
				}else{
					st.setState("3");//活动进行中
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
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
	/**
     * 中文數字转阿拉伯数组【十万九千零六十  --> 109060】
     * @author
     * @param chineseNumber
     * @return
     */
	public int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                        case 0:
                            temp *= 10;
                            break;
                        case 1:
                            temp *= 100;
                            break;
                        case 2:
                            temp *= 1000;
                            break;
                        case 3:
                            temp *= 10000;
                            break;
                        case 4:
                            temp *= 100000000;
                            break;
                        default:
                            break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }

	@Override
	public int selectMaxNum() {
		return sgThemeMapper.selectMaxNum();
	}

	@Override
	public SgTheme selectMaxEndDate(String num) {
		return sgThemeMapper.selectMaxEndDate(num);
	}
}