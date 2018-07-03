package com.ffxl.platform.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.ffxl.platform.constant.Message;
import com.ffxl.platform.core.exception.BusinessException;

/**
 * 渲染工具类
 * 
 * @author jiawei
 * 2018年7月3日
 */
public class RenderUtil {

    /**
     * 渲染json对象
     */
    public static void renderJson(HttpServletResponse response, Object jsonObject) {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(jsonObject));
        } catch (IOException e) {
            throw new BusinessException(Message.M5017);
        }
    }
}
