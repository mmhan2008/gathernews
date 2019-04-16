package com.cnstock.entity;

public class TbConfigure {
    private Integer configureId;

    private Integer cacheSize;

    private Integer heartbeatTime;

    private Integer taskProcess;

    private Integer taskCount;

    public Integer getConfigureId() {
        return configureId;
    }

    public void setConfigureId(Integer configureId) {
        this.configureId = configureId;
    }

    public Integer getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(Integer cacheSize) {
        this.cacheSize = cacheSize;
    }

    public Integer getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(Integer heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public Integer getTaskProcess() {
        return taskProcess;
    }

    public void setTaskProcess(Integer taskProcess) {
        this.taskProcess = taskProcess;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }
}