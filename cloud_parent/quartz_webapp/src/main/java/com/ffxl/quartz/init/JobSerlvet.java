package com.ffxl.quartz.init;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import net.sf.json.JSONArray;

import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;








import com.alibaba.fastjson.JSONObject;
import com.ffxl.cloud.model.STimetask;
import com.ffxl.cloud.quartz.QuartzJobFactory;
import com.ffxl.cloud.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.ffxl.cloud.quartz.ScheduleJob;
import com.ffxl.platform.util.JsonResult;
import com.ffxl.platform.util.StringUtil;
import com.ffxl.platform.wechat.model.WechatAllOpenId;

@Component
@RequestMapping(value = "/opt")
public class JobSerlvet {
  public final Logger log = Logger.getLogger(this.getClass());

  @Autowired
  private SchedulerFactoryBean schedulerFactoryBean;

  /**
   * 获取所有计划中的任务列表
   * 
   * @return
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/getAllJob")
  @ResponseBody
  public void getAllJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
    Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    for (JobKey jobKey : jobKeys) {
      List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
      for (Trigger trigger : triggers) {
        ScheduleJob job = new ScheduleJob();
        job.setJobId(trigger.getDescription());//description 放的是job的id
        job.setJobName(jobKey.getName());
        job.setJobGroup(jobKey.getGroup());
        job.setDescription("触发器:" + trigger.getKey());
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
        job.setJobStatus(triggerState.name());
        if (trigger instanceof CronTrigger) {
          CronTrigger cronTrigger = (CronTrigger) trigger;
          String cronExpression = cronTrigger.getCronExpression();
          job.setCronExpression(cronExpression);
        }
        jobList.add(job);
      }
    }
  //输出
    if(jobList.size() >0){
      JSONArray listArray=JSONArray.fromObject(jobList);
      Map<String,Object> m =new HashMap<String, Object>();
      m.put("job", listArray);
      response.setHeader("Content-type", "text/html;charset=UTF-8"); 
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.write("{\"code\":\"2000\",\"message\":\"成功\",\"data\":"+m+"}");
      out.close();
    }else{
      response.setHeader("Content-type", "text/html;charset=UTF-8"); 
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.write("{\"code\":\"5000\",\"message\":\"没有计划任务\"}");
      out.close(); 
    }
    
  }

  /**
   * 所有正在运行的job
   * 
   * @return
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/getRunningJob")
  @ResponseBody
  public void getRunningJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>(executingJobs.size());
    for (JobExecutionContext executingJob : executingJobs) {
      ScheduleJob job = new ScheduleJob();
      JobDetail jobDetail = executingJob.getJobDetail();
      JobKey jobKey = jobDetail.getKey();
      Trigger trigger = executingJob.getTrigger();
      job.setJobName(jobKey.getName());
      job.setJobGroup(jobKey.getGroup());
      job.setDescription("触发器:" + trigger.getKey());
      Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
      job.setJobStatus(triggerState.name());
      if (trigger instanceof CronTrigger) {
        CronTrigger cronTrigger = (CronTrigger) trigger;
        String cronExpression = cronTrigger.getCronExpression();
        job.setCronExpression(cronExpression);
      }
      jobList.add(job);
    }
    //输出
    if(jobList.size() >0){
      JSONArray listArray=JSONArray.fromObject(jobList);
      Map<String,Object> m =new HashMap<String, Object>();
      m.put("job", listArray);
      response.setHeader("Content-type", "text/html;charset=UTF-8"); 
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.write("{\"code\":\"2000\",\"message\":\"成功\",\"data\":"+m+"}");
      out.close();
    }else{
      response.setHeader("Content-type", "text/html;charset=UTF-8"); 
      response.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      out.write("{\"code\":\"5000\",\"message\":\"没有正在执行的任务\"}");
      out.close(); 
    }
  }
  
  /**
   * 添加任务
   * 
   * @param 
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/addJob")
  @ResponseBody
  public void addJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in = request.getInputStream();  
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
    ScheduleJob job = new ScheduleJob();
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        job.setJobId(sTimetask.getId());
        job.setJobGroup(sTimetask.getGroupName()); //任务组
        job.setJobName(sTimetask.getName());// 任务名称
        job.setJobStatus(sTimetask.getJobStatus());  // 任务发布状态
        job.setIsConcurrent(sTimetask.getConcurrent()?"1":"0"); // 运行状态
        job.setCronExpression(sTimetask.getCron());
        job.setBeanClass(sTimetask.getBeanName());// 一个以所给名字注册的bean的实例
        job.setMethodName(sTimetask.getMethodName());
        job.setJobData(sTimetask.getJobData()); //参数
      }
    }  
    InitQuartzJob.addJob(job);
    //输入
    response.setHeader("Content-type", "text/html;charset=UTF-8"); 
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
    out.close();
  }

  /**
   * 暂停一个job
   * 
   * @param scheduleJob
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/pauseJob")
  @ResponseBody
  public void pauseJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in = request.getInputStream();  
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sTimetask.getName(), sTimetask.getGroupName());
        scheduler.pauseJob(jobKey);
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
        out.close();
      }else{
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"5000\",\"message\":\"任务不存在\"}");
        out.close();
      }
    }
    
  }

  /**
   * 恢复一个job
   * 
   * @param scheduleJob
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/resumeJob")
  @ResponseBody
  public void resumeJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in = request.getInputStream();  
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sTimetask.getName(), sTimetask.getGroupName());
        scheduler.resumeJob(jobKey);
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
        out.close();
      }else{
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"5000\",\"message\":\"任务不存在\"}");
        out.close();
      }
    }
  }

  /**
   * 删除一个job
   * 
   * @param scheduleJob
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/deleteJob")
  @ResponseBody
  public void deleteJob(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in = request.getInputStream();  
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sTimetask.getName(), sTimetask.getGroupName());
        scheduler.deleteJob(jobKey);
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
        out.close();
      }else{
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"5000\",\"message\":\"任务不存在\"}");
        out.close();
      }
    }
  }

  /**
   * 立即执行job
   * 
   * @param scheduleJob
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/runAJobNow")
  @ResponseBody
  public void runAJobNow(HttpServletRequest request,HttpServletResponse response) throws SchedulerException, IOException {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in = request.getInputStream();  
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sTimetask.getName(), sTimetask.getGroupName());
        scheduler.triggerJob(jobKey);
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
        out.close();
      }else{
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"5000\",\"message\":\"任务不存在\"}");
        out.close();
      }
    }
    
    
    
  }

  /**
   * 更新job时间表达式
   * 
   * @param scheduleJob
   * @throws SchedulerException
   * @throws IOException 
   */
  @RequestMapping(value="/updateJobCron")
  @ResponseBody
  public void updateJobCron(HttpServletRequest request,HttpServletResponse response) throws SchedulerException{
    try {
    StringBuffer info=new StringBuffer();  
    ServletInputStream in;
 
      in = request.getInputStream();
   
    BufferedInputStream buf = new BufferedInputStream(in);  
    byte[] buffer=new byte[1024];   
    int iRead;  
    while((iRead=buf.read(buffer))!=-1){  
        info.append(new String(buffer,0,iRead,"UTF-8"));  
    }
    // 释放资源
    buf.close();
    in.close();
   
    if(info!=null&&!StringUtil.isEmpty(info.toString())){  
      JSONObject json = JSONObject.parseObject(info.toString());
      STimetask sTimetask = JSONObject.toJavaObject(json, STimetask.class);
      if(sTimetask !=null){
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(sTimetask.getName(), sTimetask.getGroupName());

        TriggerKey triggerKey = TriggerKey.triggerKey(sTimetask.getName(), sTimetask.getGroupName());

        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sTimetask.getCron());

        trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

        scheduler.rescheduleJob(triggerKey, trigger);
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"2000\",\"message\":\"成功\"}");
        out.close();
      }else{
        //输出
        response.setHeader("Content-type", "text/html;charset=UTF-8"); 
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.write("{\"code\":\"5000\",\"message\":\"任务不存在\"}");
        out.close();
      }
    }
  }catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }  
  }
}
