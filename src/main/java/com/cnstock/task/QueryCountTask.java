package com.cnstock.task;

import com.cnstock.entity.Es;
import com.cnstock.entity.TbJobCount;
import com.cnstock.mapper.TbJobCountMapper;
import com.cnstock.service.ESRestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author user01
 * @create 2019/3/1
 */

@Component
@EnableScheduling
public class QueryCountTask {

    private static Logger logger = LoggerFactory.getLogger(QueryCountTask.class);

    @Autowired
    private TbJobCountMapper tbJobCountMapper;

    @Autowired
    private ESRestServiceImpl esRestService;

    @Scheduled(cron="00 59 23 ? * *")
    public void startCount(){
        LocalDateTime start = LocalDateTime.now();
        logger.info("开始统计当天任务总数,当前时间：{}",start.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        String time = start.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        try {
            List<Es> todayEsData = esRestService.getTodayEsData();
            List<TbJobCount> list = new ArrayList<>();
            if (todayEsData.size()>0){
                List<TbJobCount> jobOfCount = esRestService.getJobOfCount(todayEsData);
                for (TbJobCount tbJobCount:jobOfCount) {
                    TbJobCount tbJobCount1 = new TbJobCount();
                    tbJobCount1.setJobId(tbJobCount.getJobId());
                    tbJobCount1.setDateCount(tbJobCount.getDateCount());
                    tbJobCount1.setDate(time);
                    list.add(tbJobCount1);
                }
                int i = tbJobCountMapper.insertForeach(list);
                LocalDateTime end = LocalDateTime.now();
                if (i>0) logger.info("统计结束数据已保存,当前时间：{};当日总计更新{}条数据",
                        end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),list.size());
                else logger.info("数据保存失败");
            } else {
                logger.info("未查询到当天数据！");
            }
        }catch (Exception e){
            logger.info("任务总数统计过程出现异常\n{}",e);
        }
    }
}

