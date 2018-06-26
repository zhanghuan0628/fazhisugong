package quartz_webapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffxl.cloud.quartz.ScheduleJob;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class T {

class User{
   private String name;
   private String id;
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
   
 }
  public static void main(String[] args) {
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    ScheduleJob job1 =new ScheduleJob();
    job1.setJobId("1");
    job1.setJobName("name1");
    jobList.add(job1);
    ScheduleJob job2 =new ScheduleJob();
    job2.setJobId("2");
    job2.setJobName("name2");
    jobList.add(job2);
    JSONArray listArray=JSONArray.fromObject(jobList);
    Map<String,Object> m =new HashMap<String, Object>();
    m.put("job", listArray);
    String res = "{\"code\":\"2000\",\"message\":\"成功\",\"data\":"+m+"}";
    
    
    
    JSONObject jsonPlanResult = JSONObject.fromObject(res);
    Map<String, ScheduleJob> planMap = new HashMap<String, ScheduleJob>();
    if(jsonPlanResult.get("code").equals("2000")){
      JSONObject js = (JSONObject) jsonPlanResult.get("data");
      JSONArray dataArray = (JSONArray) js.get("job");
      if(dataArray.size() > 0){
        List<ScheduleJob> jobqList =  JSONArray.toList(dataArray,ScheduleJob.class);
        for(ScheduleJob job: jobqList){
          planMap.put(job.getJobId().toString(), job);
        }
      }
    }
  }

}
