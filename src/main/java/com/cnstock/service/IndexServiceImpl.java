package com.cnstock.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnstock.entity.*;
import com.cnstock.mapper.TbErrorMapper;
import com.cnstock.mapper.TbJobMapper;
import com.cnstock.mapper.TbLogMapper;
import com.cnstock.utils.AESUtils;
import com.cnstock.utils.RandomUtils;
import com.cnstock.utils.TimeUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2019/1/14.
 */
@Service
public class IndexServiceImpl{
    private Logger logger = Logger.getLogger(IndexServiceImpl.class);
    public LinkedBlockingQueue<Es> jobQueue = new LinkedBlockingQueue<>();
    public LinkedBlockingQueue<ArrayList<JSONObject>> objQueue = new LinkedBlockingQueue<>();
    public LinkedHashMap<String , LinkedHashMap<String ,Boolean>> cache = new LinkedHashMap<>();
    public HashMap<String , ArrayList<JSONObject>> workerCache = new HashMap<>();
    public HashMap<String , Long> heartCache = new HashMap<>();
    public HashMap<String , TbJob> jobCache = new HashMap<>();
    public HashMap<String , TbJob> upJobCache = new HashMap<>();
    public List<TbJob> alltaks = new ArrayList<>();
    public boolean isruning = true;
    public int runJobNum=0;
    @Autowired
    private ESRestServiceImpl esRestService;
    @Value(value = "${com.cnstock.token}")
    private String token;
    @Value(value = "${com.cnstock.cachesize}")
    private int cachesize;
    @Value(value = "${com.cnstock.taskcount}")
    private int taskcount;
    @Value(value = "${com.cnstock.errorcount}")
    private int errorcount;
    public String query() {
        return "query";
    }
    @Autowired
    private TbJobMapper tbJobMapper;
    @Autowired
    private TbErrorMapper tbErrorMapper;
    @Autowired
    private TbLogMapper tbLogMapper;


    class JobRunnables implements Runnable{
        @Override
        public void run() {
            main();
        }
    }


    public void main(){
        //现在缓存中去重 解析workerde的数据,进行拼接
        for(int i=0;i<objQueue.size();i++){
            cacheRemoveRepeat();
        }
        while (isruning){
            if(jobQueue.size()>0){
                isruning = true;
            }else{
                isruning = false;
            }
            //存入es
            removeRepeatData();
        }
    }

    public void cacheRemoveRepeat(){
        ArrayList<JSONObject> list =objQueue.poll();

        if(list!=null){
            String cachaName = "";
            for(JSONObject ob :list){
                cachaName = ob.get("jobUrl").toString();
            }
            LinkedHashMap<String , Boolean> linkCache= cache.get(cachaName);
            if(linkCache == null){
                linkCache = new LinkedHashMap<String , Boolean>() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    protected boolean removeEldestEntry(Map.Entry<String , Boolean> eldest) {
                        return size() > cachesize;
                    }
                };
            }
            for(JSONObject ob :list){
                if(ob.get("content")!=null||ob.get("content").toString().length()>0){
                    String str[] = ob.get("content").toString().split("####");
                    for(int i=0;i<str.length;i++){
                        String twos[] = str[i].split("###");
                        Es es= new Es();
                        try{
                            es.setLink(twos[0]);
                        }catch (ArrayIndexOutOfBoundsException e){
                            es.setLink("");
                        }
                        try{
                            es.setTitle(twos[1]);
                        }catch (ArrayIndexOutOfBoundsException e){
                            es.setTitle("");
                        }
                        if(es.getLink()==null||es.getLink().equals(""))
                            continue;
                        String lindMd5 = AESUtils.encryption(es.getLink());
                        if(linkCache.size()>0){
                            //存在则不做操作，不存在则添加
                            if(linkCache.containsKey(lindMd5)){
                                pojoEs(es,ob.get("jobName").toString(),lindMd5,ob.get("jobId").toString());
                                linkCache.put(lindMd5,true);
                                jobQueue.offer(es);
                            }else{
                                pojoEs(es,ob.get("jobName").toString(),lindMd5,ob.get("jobId").toString());
                                linkCache.put(lindMd5,true);
                                jobQueue.offer(es);
                            }
                        }else{
                            pojoEs(es,ob.get("jobName").toString(),lindMd5,ob.get("jobId").toString());
                            linkCache.put(lindMd5,true);
                            jobQueue.offer(es);
                        }
                    }
                    insertTbLog(ob.get("jobId").toString(),str.length);
                }
            }
            cache.put(cachaName,linkCache);
        }
    }

    public Es pojoEs(Es es,String name,String md5,String jobId){
        es.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        es.setJobId(jobId);
        es.setJobName(name);
        es.setName(name);
        es.setUrlMd5(md5);
        es.setCreateTime(new Date());
        return es;
    }

    public void removeRepeatData(){
        Es es = jobQueue.poll();
        if(es!=null){
            List<Es> esList = esRestService.getIndexData(es.getUrlMd5(),"spidernews_index",Es.class);
            if(esList.size()==0){
                String id = "";
                if(es.getUrlMd5()==null||es.getUrlMd5().equals("")){
                    id = es.getId();
                }else{
                    id = es.getUrlMd5();
                }
                //添加数据
                boolean result = esRestService.putSync("spidernews_index","spidernews_type",id,es);
                if(result){
//                    logger.info("insert es success");
                }
            }
        }
    }

    public void insertTbLog(String jobId,int linkCount){
        TbLog tbLog = new TbLog();
        tbLog.setJobId(jobId);
        tbLog.setUpdateTime(TimeUtils.getTime(new Date()));
        tbLog.setLinkCount(linkCount);
        tbLogMapper.insertSelective(tbLog);
    }

    public Map<String,Object>  getTaskList(ArrayList<JSONObject> list,String droneId){
        if(!workerCache.containsKey(droneId)&&list.size()>0)
            list = new ArrayList<>();

        //worker缓存
        workerCache.put(droneId,list);
        heartCache.put(droneId,System.currentTimeMillis());
        Map<String,Object> maps = new HashMap<>();
        //获取当前worker数量
        int workerNum = workerCache.size();
        //加载所有启用状态任务
        if(jobCache.size()==0){
            getAllRunJob();
        }
        if(list.size()>0){
            ArrayList<TbJob> errorList=errorRecord(list,droneId);
            logger.info("接收的任务数："+list.size());
        }
        //计算平均数
        int avgNum = new Double(jobCache.size()/workerNum).intValue();
        List<TbJob> alltaks = new ArrayList<>();
        //提取所有未分配任务
        for (String key : jobCache.keySet()) {
            TbJob job = jobCache.get(key);
            if(job.getDroneId()!=null&&!job.getDroneId().equals("")){
                continue;
            }else {
                alltaks.add(job);
            }
        }
        logger.info("所有的任务数量： "+jobCache.size());
        logger.info("未分配的任务数量： "+alltaks.size());
        logger.info("分配任务总数： "+taskcount+" 任务平均数： "+avgNum +" woker数量： "+workerNum);
        List<TbJob> task = new ArrayList<>();

        //针对worker重启，cc任务不变
        if(workerCache.containsKey(droneId)&&list.size()==0){
            logger.info("================Worker Restart================");
            list = workerCache.get(droneId);
        }
        //alltaks 所有未分配任务
        if(list.size()==0&&alltaks.size()>0){
            //分配任务
            task=assignmentTask(alltaks,avgNum,droneId);
            maps.put("status","1");
        }else if(list.size()<avgNum&&alltaks.size()==0){
            //接收任务小于平均任务数，且没有未分配任务，直接返回
            if(list.size()>0){
                for(JSONObject object: list){
                    task.add(JSON.parseObject(object.toJSONString(), TbJob.class));
                }
                logger.info("接收任务小于平均任务数，且没有未分配任务，直接返回");
            }
            maps.put("status","0");
        }else if(list.size()<avgNum&&alltaks.size()>0){
            //接收任务小于平均任务数，且有未分配任务
            if(list.size()==taskcount){
                for(JSONObject object: list){
                    if(object==null)
                        continue;
                    task.add(JSON.parseObject(object.toJSONString(), TbJob.class));
                }
                maps.put("status","0");
                logger.info("接收任务小于平均任务数，且有未分配任务，任务数等于系统分配数，直接返回");
            }else{
                if(taskcount<avgNum){
                    for(JSONObject object: list){
                        if(object==null)
                            continue;
                        task.add(JSON.parseObject(object.toJSONString(), TbJob.class));
                    }
                    task.addAll(assignmentTask(taskcount-list.size(),droneId));
                }else{
                    for(JSONObject object: list){
                        if(object==null)
                            continue;
                        task.add(JSON.parseObject(object.toJSONString(), TbJob.class));
                    }
                    task.addAll(assignmentTask(avgNum-list.size(),droneId));
                }
                maps.put("status","1");
                logger.info("添加任务 :接受任务数 "+list.size()+" 平均任务数 "+avgNum +" 添加后任务数 "+task.size());
            }
        }else if(list.size()>avgNum){
            //执行任务数大于平均数，释放任务
            for(int i=0;i<list.size();i++){
                if(i<avgNum){
                    task.add(JSON.parseObject(list.get(i).toJSONString(), TbJob.class));
                }else{
                    if(list.get(i)!=null){
                        TbJob taskjob = JSON.parseObject(list.get(i).toJSONString(), TbJob.class);
                        taskjob.setDroneId(null);
                        jobCache.put(taskjob.getJobId(),taskjob);
                    }

                }
            }
            logger.info("释放任务 :接受任务数 "+list.size()+" 平均任务数 "+avgNum +" 释放后任务数 "+task.size());
            maps.put("status","1");
        }else{
            if(list.size()>0){
                for(JSONObject object: list){
                    if(object==null)
                        continue;
                    task.add(JSON.parseObject(object.toJSONString(), TbJob.class));
                }
            }
            maps.put("status","0");
            logger.info("没有未分配任务，直接返回");
        }

        workerCache.put(droneId,JSON.parseObject(JSON.toJSONString(task), ArrayList.class));
        maps.put("list",task);
        logger.info("返回结果 :接收任务数 "+list.size()+" 平均任务数 "+avgNum +" 返回任务数 "+task.size()+" 返回状态码 "+maps.get("status"));
        return maps;
    }

    public ArrayList<TbJob> errorRecord(ArrayList<JSONObject> list,String droneId){
        ArrayList<TbJob> errorList = new ArrayList<>();
        try{
            //遍历返回的任务队列
            if(!list.isEmpty()&&list.size()>0){
                ArrayList<JSONObject> errors = new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    //错误标记
                    if(list.get(i)!=null){
                        TbJob job = JSON.parseObject(list.get(i).toJSONString(),TbJob.class);
                        if(job.getErrorCount()!=null&&job.getErrorCount()>=5){
                            TbError tbError = new TbError();
                            tbError.setErrorId(UUID.randomUUID().toString().replaceAll("-", ""));
                            tbError.setJobId(job.getJobId());
                            tbError.setDroneId(droneId);
                            tbError.setErrorCount(job.getErrorCount());
                            tbError.setErrorType(Integer.parseInt(StringUtils.isEmpty(job.getErrorType())?"0":job.getErrorType()));
                            tbError.setCreateTime(TimeUtils.getDate(new Date()));
                            tbErrorMapper.insert(tbError);
                            //查找错误表中任务错误数
                            TbErrorExample tbErrorExample = new TbErrorExample();
                            TbErrorExample.Criteria criteria = tbErrorExample.createCriteria();
                            criteria.andJobIdEqualTo(job.getJobId()).andCreateTimeEqualTo(TimeUtils.getDate(new Date()));
                            List<TbError> s = tbErrorMapper.selectByExample(tbErrorExample);

                            //当任务错误数大于某个数值时，停用该任务
                            if(s!=null&&s.size()>=errorcount){
                                logger.info("任务Id："+job.getJobId()+" 任务名称："+job.getJobName() + " 错误类型："
                                        + job.getErrorType() +" 暂停");
                                TbJob tbJob = new TbJob();
                                tbJob.setJobId(job.getJobId());
                                tbJob.setIsEnable("暂停");
                                tbJob.setUpdateTime(TimeUtils.getTime(new Date()));
                                tbJobMapper.updateByPrimaryKeySelective(tbJob);
                                jobCache.remove(job.getJobId());
                            }else{
                                errors.add(list.get(i));
                                errorList.add(job);
                                job.setErrorCount(0);
                                job.setDroneId(null);
                                jobCache.put(job.getJobId(),job);
                            }
                        }
                    }
                }

                if(errors.size()>0){
                    list.removeAll(errors);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return errorList;
    }

    /**
     * 初始化任务，查询数据库中所有启用的任务连接
     */
    public void getAllRunJob(){
        TbJobExample example = new TbJobExample();
        TbJobExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo("启用");
        List<TbJob> tbJobs = new ArrayList<>();
        tbJobs = tbJobMapper.selectByExample(example);
        if(tbJobs.size()>0){
            for(int i=0;i<tbJobs.size();i++){
                String jobId = tbJobs.get(i).getJobId();
                if(jobCache.containsKey(jobId)){
                    continue;
                }else{
                    jobCache.put(jobId,tbJobs.get(i));
                }
            }
        }
    }

    public List<TbJob> assignmentTask(List<TbJob> alltaks,int avaTaskNum,String droneId){

        int taskNum = 0;
        if(alltaks.size()>taskcount){
            taskNum = taskcount;
        }else{
            taskNum = alltaks.size();
        }


        return assignmentTask(taskNum,droneId);
    }

    public List<TbJob> assignmentTask(int taskNum,String droneId){
        List<TbJob> taks= new ArrayList<>();
        for(String key:jobCache.keySet()){
            TbJob job = jobCache.get(key);
            if(job.getDroneId()==null||job.getDroneId().equals("")){
                job.setDroneId(droneId);
                jobCache.put(key,job);
                job.setUpdateTime(TimeUtils.getTime(new Date()));
                upJobCache.put(key,job);
                taks.add(job);
            }
            if(taks.size()==taskNum){
                break;
            }
        }
        return taks;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void taskJob(){
        logger.info("start run taskJob");
        if(objQueue.size()>0){
            logger.info("run taskJob objQueue size "+objQueue.size());
            isruning = true;
            JobRunnables r = new JobRunnables();
            if(objQueue.size()>0) {
                for (int i=0;i<10;i++){
                    new Thread(r).start();
                }
            }
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void heart(){
        logger.info("scan heart");
        if(heartCache.size()>0){
            List<String> keyList = new ArrayList<>();
            for(String key : heartCache.keySet()){
                Long workHeart = Long.parseLong(heartCache.get(key).toString());
                if((workHeart+60000*6)<System.currentTimeMillis()){
                    logger.info("worker :"+key+"十分钟未发送心跳至overload");
                    ArrayList<JSONObject> list  = workerCache.get(key);
                    if(list!=null&&list.size()>0){
                        for(JSONObject object: list){
                            TbJob taskjob = JSON.parseObject(object.toJSONString(), TbJob.class);
                            taskjob.setDroneId(null);
                            jobCache.put(taskjob.getJobId(),taskjob);
                        }
                    }
                    keyList.add(key);
                }
            }
            if(keyList.size()>0){
                for(String key : keyList){
                    ArrayList<JSONObject> list  = workerCache.get(key);
                    workerCache.remove(key);
                    heartCache.remove(key);
                    logger.info("移除worker :"+key+" 将任务标记为未分配，总共修改任务数："+list.size());
                }
            }
        }
    }

    /**
     *
     * 每天六点十五将已经暂停的任务修改成启用状态
     */
    @Scheduled(cron = "0 15 06 ? * *")
    public void updateTbJobStatus(){
        TbJobExample example = new TbJobExample();
        TbJobExample.Criteria criteria = example.createCriteria();
        criteria.andIsEnableEqualTo("暂停");
        List<TbJob> tbJobs = new ArrayList<>();
        tbJobs = tbJobMapper.selectByExample(example);
        for(TbJob tbJob:tbJobs){
            tbJob.setIsEnable("启用");
            tbJob.setErrorCount(0);
            tbJobMapper.updateByPrimaryKeySelective(tbJob);
        }
        cache.clear();
        workerCache.clear();
        heartCache.clear();
        jobCache.clear();
        upJobCache.clear();
    }
}
