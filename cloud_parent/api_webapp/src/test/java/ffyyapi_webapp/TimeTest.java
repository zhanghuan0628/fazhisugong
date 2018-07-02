package ffyyapi_webapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.model.FUserBaseInfo;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.MemcachedUtils;

public class TimeTest {

    public static void main(String[] args) {
        String datetime = "2017-02-09 23:59:59";
        String datetime1 = "2017-02-09 24:00:00";
        

//       String st =  DateUtil.formatStandardDatetime(DateUtil.parseDate(datetime1));
        JSONObject js = new JSONObject();
      
        //服务器时间
        Date currentDate = new Date();
        System.out.println("----------------------当前时间："+currentDate);
        Date loseDate = DateUtil.getResultDate(currentDate, 1, 1); //后推一小时
        System.out.println("----------------------失效时间："+loseDate);
//      String loseTime = DateUtil.formatPureStandardDate(loseDate);
        js.put("loseTime", loseDate); //失效时间
        List<JSONObject> list1 = (List) MemcachedUtils.get("fxfDm");
        if (list1 == null)
            list1 = new ArrayList<JSONObject>();
        list1.add(js);
        MemcachedUtils.set("fxfDm", list1);
        
        List<JSONObject> list2 = (List<JSONObject>) MemcachedUtils.get("fxfDm");
//      WebSocketController.sendAll(JSONObject.toJSONString(list));
        if(list2 == null) list2 = new ArrayList<JSONObject>();
        List<JSONObject> visibleList = new ArrayList<JSONObject>();
        for(JSONObject jn: list2){
            if(jn.containsKey("loseTime")){
                Date loseDate2 = jn.getDate("loseTime");
                //未失效
               if(currentDate.before(loseDate2)){
                   System.out.println("1----------------------当前时间："+currentDate);
                   System.out.println("2----------------------弹幕过期时间时间-------："+loseDate2);
                   visibleList.add(jn);
               }
            }
        }
    }

}
