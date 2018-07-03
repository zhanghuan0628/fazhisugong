package ffyyapi_webapp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.alibaba.fastjson.JSON;
import com.ffxl.cloud.model.DaySet;
import com.ffxl.cloud.model.SCounselorExperience;
import com.ffxl.platform.util.DateUtil;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.Message;

public class Test {

    public static void main(String[] args) {
//        Date d = new Date();
//        
//        long validateL = d.getTime();
//        String sd1 = "2017-01-06 12:15";
//        Date d1 = DateUtil.parseDate(sd1);
//        int valid = DateUtil.validateTime(d.getTime(), d1, 15, 60);
//        
//        String sd2 = "2017-01-07 17:37";
//        Date d2 = DateUtil.parseDate(sd2);
//        int valid2 = DateUtil.validateTime(d.getTime(), d2, 15, 60);
//        
//        String sd3 = "2017-01-08 12:15";
//        Date d3 = DateUtil.parseDate(sd3);
//        int valid3 = DateUtil.validateTime(d.getTime(), d3, 15, 60);
//        System.out.println("超时"+valid);
//        System.out.println("进行"+valid2);
//        System.out.println("等待"+valid3);
        
//        List<Integer> durList =DateUtil.getCountTimes(120, 15);
//        for(Integer dur : durList){
//            System.out.println(dur);
//        }
//        
//        JSONArray json = JSONArray.fromObject(data);
        List<List<Map<String, Integer>>> dsl = new ArrayList<List<Map<String, Integer>>>();
        for(int i=0; i<7; i++){
            List<Map<String, Integer>> li = new ArrayList<Map<String, Integer>>();
            for(int j=0;j<5;j++){
                Map<String, Integer> mm =new HashMap<String, Integer>();
                mm.put("begin", 12);
                mm.put("end", 50);
                li.add(mm);
            }
            dsl.add(li);
        }
        Map<String,List<List<Map<String, Integer>>>> mm =new HashMap<String,List<List<Map<String, Integer>>>>();
        mm.put("phone", dsl);
//        System.out.println( m.toString());
        
//        List<List<Map<String, Integer>>> phoneList = m.get("phone");
//        int i =1;
//        for(List<Map<String, Integer>> li : phoneList){
//            for(Map<String, Integer> l : li ){
//                System.out.println("第"+i+"天");
//                System.out.println(l.get("begin"));
//                System.out.println(l.get("end"));
//            }
//            i++;
//        }
        
        List<List<Map<String, Integer>>> phoneList = mm.get("phone");
        int i =1;
        Map<Object,Object> resultMap =new HashMap<Object, Object>();
        for(List<Map<String, Integer>> li : phoneList){
            resultMap.put(i, i); //第几天
            Map<Integer, Integer> m = new HashMap<Integer, Integer>();
            for(Map<String, Integer> l : li ){
                List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
                int bi = Integer.parseInt(l.get("begin").toString());
                int ei = Integer.parseInt(l.get("end").toString());
                for (int j = bi; j <= ei; j++) {
                    m.put(j, j);
                }
        }
            i++;
            resultMap.put("data", m);
        }
        System.out.println("w");
        
//        
         
//        experienceList = JSON.parseArray(experienceJson, SCounselorExperience.class);
        
//        Date d = new Date();
//        System.out.print(d);
        
        
    }
    


}
