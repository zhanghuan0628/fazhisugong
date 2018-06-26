package com.ffxl.quartz.init;

import java.util.ArrayList;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.ffxl.cloud.model.STimetask;
import com.ffxl.cloud.model.STimetaskExample;
import com.ffxl.cloud.model.base.BaseSTimetaskExample.Criteria;
import com.ffxl.cloud.quartz.QuartzJobFactory;
import com.ffxl.cloud.quartz.QuartzJobFactoryDisallowConcurrentExecution;
import com.ffxl.cloud.quartz.ScheduleJob;
import com.ffxl.cloud.service.STimetaskService;
import com.ffxl.cloud.util.wxmsg.ApplicationContextUtils;

/**
 * 根据上下文获取spring类
 * 
 * @author
 */
public class InitQuartzJob implements ApplicationContextAware {
  private static final Logger logger = LoggerFactory.getLogger(InitQuartzJob.class);

  private static ApplicationContext appCtx;

  public static SchedulerFactoryBean schedulerFactoryBean = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (this.appCtx == null) {
      this.appCtx = applicationContext;
    }
  }

  public static void init() {
    schedulerFactoryBean = (SchedulerFactoryBean) appCtx.getBean(SchedulerFactoryBean.class);
    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    try {
      logger.info(scheduler.getSchedulerName());
    } catch (SchedulerException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
    // 这里从数据库中获取任务信息数据
    STimetaskService sTimetaskService = (STimetaskService) ApplicationContextUtils.getBean(STimetaskService.class);
    STimetaskExample example = new STimetaskExample();
    Criteria c = example.createCriteria();
    c.andJobStatusEqualTo("1"); // 已发布的定时任务
    List<STimetask> list = sTimetaskService.selectByExample(example);
    List<ScheduleJob> jobList = new ArrayList<ScheduleJob>();
    for (STimetask sTimetask : list) {
      ScheduleJob job1 = new ScheduleJob();
      job1.setJobId(sTimetask.getId());
      job1.setJobGroup(sTimetask.getGroupName()); // 任务组
      job1.setJobName(sTimetask.getName());// 任务名称
      job1.setJobStatus(sTimetask.getJobStatus()); // 任务发布状态
      job1.setIsConcurrent(sTimetask.getConcurrent() ? "1" : "0"); // 运行状态
      job1.setCronExpression(sTimetask.getCron());
      job1.setBeanClass(sTimetask.getBeanName());// 一个以所给名字注册的bean的实例
      job1.setMethodName(sTimetask.getMethodName());
      job1.setJobData(sTimetask.getJobData()); // 参数
      jobList.add(job1);
    }

    for (ScheduleJob job : jobList) {
      try {
        addJob(job);
      } catch (SchedulerException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  /**
   * 添加任务
   * 
   * @param scheduleJob
   * @throws SchedulerException
   */
  public static void addJob(ScheduleJob job) throws SchedulerException {
    if (job == null || !ScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())) {
      return;
    }

    Scheduler scheduler = schedulerFactoryBean.getScheduler();
    logger.debug(scheduler + "...........................................add");
    TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());

    CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

    // 不存在，创建一个
    if (null == trigger) {
      Class clazz = ScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent()) ? QuartzJobFactory.class
                                                                           : QuartzJobFactoryDisallowConcurrentExecution.class;

      JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).usingJobData("data", job.getJobData()).build();

      jobDetail.getJobDataMap().put("scheduleJob", job);

      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

      trigger = TriggerBuilder.newTrigger().withDescription(job.getJobId().toString()).withIdentity(job.getJobName(), job.getJobGroup())
          .withSchedule(scheduleBuilder).build();

      scheduler.scheduleJob(jobDetail, trigger);
    } else {
      // Trigger已存在，那么更新相应的定时设置
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());

      // 按新的cronExpression表达式重新构建trigger
      trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).usingJobData("data", job.getJobData()).withSchedule(scheduleBuilder).build();

      // 按新的trigger重新设置job执行
      scheduler.rescheduleJob(triggerKey, trigger);
    }
  }
  //
  // private static String pojoToCron(STimetask pojo) {
  // String type = pojo.getType();
  // String cron = "";
  // if ("1".equals(type)) { //一次性
  // Calendar c = Calendar.getInstance();
  // c.setTime(pojo.getStartTime());
  // int year = c.get(Calendar.YEAR);
  // int month = c.get(Calendar.MONTH)+1;
  // int date = c.get(Calendar.DATE);
  // int hour = c.get(Calendar.HOUR_OF_DAY);
  // int minute = c.get(Calendar.MINUTE);
  // int second = c.get(Calendar.SECOND);
  // cron = second + " " + minute + " " + hour + " " + date + " " + month + " ? " + year;
  // }else{ //周期性
  // if(StringUtils.isNotBlank(pojo.getCycle())){
  // cron = pojo.getCycle();
  // }
  // }
  // return cron;
  //
  // }

}
