package com.cnstock.entity;

public class TbJobCount {
    private Integer id;

    private String jobId;

    private String date;

    private Integer dateCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getDateCount() {
        return dateCount;
    }

    public void setDateCount(Integer dateCount) {
        this.dateCount = dateCount;
    }
}