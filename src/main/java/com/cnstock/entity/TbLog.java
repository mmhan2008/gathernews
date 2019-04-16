package com.cnstock.entity;

public class TbLog {
    private Integer logId;

    private String jobId;

    private String updateTime;

    private Integer newLink;

    private Integer linkCount;

    private Integer status;

    private String var1;

    private String var2;

    private String var3;

    private String var4;

    private String var5;

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public Integer getNewLink() {
        return newLink;
    }

    public void setNewLink(Integer newLink) {
        this.newLink = newLink;
    }

    public Integer getLinkCount() {
        return linkCount;
    }

    public void setLinkCount(Integer linkCount) {
        this.linkCount = linkCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVar1() {
        return var1;
    }

    public void setVar1(String var1) {
        this.var1 = var1 == null ? null : var1.trim();
    }

    public String getVar2() {
        return var2;
    }

    public void setVar2(String var2) {
        this.var2 = var2 == null ? null : var2.trim();
    }

    public String getVar3() {
        return var3;
    }

    public void setVar3(String var3) {
        this.var3 = var3 == null ? null : var3.trim();
    }

    public String getVar4() {
        return var4;
    }

    public void setVar4(String var4) {
        this.var4 = var4 == null ? null : var4.trim();
    }

    public String getVar5() {
        return var5;
    }

    public void setVar5(String var5) {
        this.var5 = var5 == null ? null : var5.trim();
    }
}