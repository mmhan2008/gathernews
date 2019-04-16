package com.cnstock.entity;

import java.util.ArrayList;
import java.util.List;

public class TbJobExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbJobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andJobIdIsNull() {
            addCriterion("jobId is null");
            return (Criteria) this;
        }

        public Criteria andJobIdIsNotNull() {
            addCriterion("jobId is not null");
            return (Criteria) this;
        }

        public Criteria andJobIdEqualTo(String value) {
            addCriterion("jobId =", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotEqualTo(String value) {
            addCriterion("jobId <>", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThan(String value) {
            addCriterion("jobId >", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("jobId >=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThan(String value) {
            addCriterion("jobId <", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLessThanOrEqualTo(String value) {
            addCriterion("jobId <=", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdLike(String value) {
            addCriterion("jobId like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotLike(String value) {
            addCriterion("jobId not like", value, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdIn(List<String> values) {
            addCriterion("jobId in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotIn(List<String> values) {
            addCriterion("jobId not in", values, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdBetween(String value1, String value2) {
            addCriterion("jobId between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobIdNotBetween(String value1, String value2) {
            addCriterion("jobId not between", value1, value2, "jobId");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNull() {
            addCriterion("jobName is null");
            return (Criteria) this;
        }

        public Criteria andJobNameIsNotNull() {
            addCriterion("jobName is not null");
            return (Criteria) this;
        }

        public Criteria andJobNameEqualTo(String value) {
            addCriterion("jobName =", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotEqualTo(String value) {
            addCriterion("jobName <>", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThan(String value) {
            addCriterion("jobName >", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameGreaterThanOrEqualTo(String value) {
            addCriterion("jobName >=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThan(String value) {
            addCriterion("jobName <", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLessThanOrEqualTo(String value) {
            addCriterion("jobName <=", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameLike(String value) {
            addCriterion("jobName like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotLike(String value) {
            addCriterion("jobName not like", value, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameIn(List<String> values) {
            addCriterion("jobName in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotIn(List<String> values) {
            addCriterion("jobName not in", values, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameBetween(String value1, String value2) {
            addCriterion("jobName between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobNameNotBetween(String value1, String value2) {
            addCriterion("jobName not between", value1, value2, "jobName");
            return (Criteria) this;
        }

        public Criteria andJobUrlIsNull() {
            addCriterion("jobUrl is null");
            return (Criteria) this;
        }

        public Criteria andJobUrlIsNotNull() {
            addCriterion("jobUrl is not null");
            return (Criteria) this;
        }

        public Criteria andJobUrlEqualTo(String value) {
            addCriterion("jobUrl =", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlNotEqualTo(String value) {
            addCriterion("jobUrl <>", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlGreaterThan(String value) {
            addCriterion("jobUrl >", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlGreaterThanOrEqualTo(String value) {
            addCriterion("jobUrl >=", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlLessThan(String value) {
            addCriterion("jobUrl <", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlLessThanOrEqualTo(String value) {
            addCriterion("jobUrl <=", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlLike(String value) {
            addCriterion("jobUrl like", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlNotLike(String value) {
            addCriterion("jobUrl not like", value, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlIn(List<String> values) {
            addCriterion("jobUrl in", values, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlNotIn(List<String> values) {
            addCriterion("jobUrl not in", values, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlBetween(String value1, String value2) {
            addCriterion("jobUrl between", value1, value2, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andJobUrlNotBetween(String value1, String value2) {
            addCriterion("jobUrl not between", value1, value2, "jobUrl");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andJobModelIsNull() {
            addCriterion("jobModel is null");
            return (Criteria) this;
        }

        public Criteria andJobModelIsNotNull() {
            addCriterion("jobModel is not null");
            return (Criteria) this;
        }

        public Criteria andJobModelEqualTo(String value) {
            addCriterion("jobModel =", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelNotEqualTo(String value) {
            addCriterion("jobModel <>", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelGreaterThan(String value) {
            addCriterion("jobModel >", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelGreaterThanOrEqualTo(String value) {
            addCriterion("jobModel >=", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelLessThan(String value) {
            addCriterion("jobModel <", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelLessThanOrEqualTo(String value) {
            addCriterion("jobModel <=", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelLike(String value) {
            addCriterion("jobModel like", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelNotLike(String value) {
            addCriterion("jobModel not like", value, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelIn(List<String> values) {
            addCriterion("jobModel in", values, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelNotIn(List<String> values) {
            addCriterion("jobModel not in", values, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelBetween(String value1, String value2) {
            addCriterion("jobModel between", value1, value2, "jobModel");
            return (Criteria) this;
        }

        public Criteria andJobModelNotBetween(String value1, String value2) {
            addCriterion("jobModel not between", value1, value2, "jobModel");
            return (Criteria) this;
        }

        public Criteria andHashCodeIsNull() {
            addCriterion("hashCode is null");
            return (Criteria) this;
        }

        public Criteria andHashCodeIsNotNull() {
            addCriterion("hashCode is not null");
            return (Criteria) this;
        }

        public Criteria andHashCodeEqualTo(String value) {
            addCriterion("hashCode =", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotEqualTo(String value) {
            addCriterion("hashCode <>", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeGreaterThan(String value) {
            addCriterion("hashCode >", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeGreaterThanOrEqualTo(String value) {
            addCriterion("hashCode >=", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLessThan(String value) {
            addCriterion("hashCode <", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLessThanOrEqualTo(String value) {
            addCriterion("hashCode <=", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeLike(String value) {
            addCriterion("hashCode like", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotLike(String value) {
            addCriterion("hashCode not like", value, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeIn(List<String> values) {
            addCriterion("hashCode in", values, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotIn(List<String> values) {
            addCriterion("hashCode not in", values, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeBetween(String value1, String value2) {
            addCriterion("hashCode between", value1, value2, "hashCode");
            return (Criteria) this;
        }

        public Criteria andHashCodeNotBetween(String value1, String value2) {
            addCriterion("hashCode not between", value1, value2, "hashCode");
            return (Criteria) this;
        }

        public Criteria andErrorCountIsNull() {
            addCriterion("errorCount is null");
            return (Criteria) this;
        }

        public Criteria andErrorCountIsNotNull() {
            addCriterion("errorCount is not null");
            return (Criteria) this;
        }

        public Criteria andErrorCountEqualTo(Integer value) {
            addCriterion("errorCount =", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountNotEqualTo(Integer value) {
            addCriterion("errorCount <>", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountGreaterThan(Integer value) {
            addCriterion("errorCount >", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("errorCount >=", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountLessThan(Integer value) {
            addCriterion("errorCount <", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountLessThanOrEqualTo(Integer value) {
            addCriterion("errorCount <=", value, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountIn(List<Integer> values) {
            addCriterion("errorCount in", values, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountNotIn(List<Integer> values) {
            addCriterion("errorCount not in", values, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountBetween(Integer value1, Integer value2) {
            addCriterion("errorCount between", value1, value2, "errorCount");
            return (Criteria) this;
        }

        public Criteria andErrorCountNotBetween(Integer value1, Integer value2) {
            addCriterion("errorCount not between", value1, value2, "errorCount");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNull() {
            addCriterion("isEnable is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableIsNotNull() {
            addCriterion("isEnable is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableEqualTo(String value) {
            addCriterion("isEnable =", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotEqualTo(String value) {
            addCriterion("isEnable <>", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThan(String value) {
            addCriterion("isEnable >", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableGreaterThanOrEqualTo(String value) {
            addCriterion("isEnable >=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThan(String value) {
            addCriterion("isEnable <", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLessThanOrEqualTo(String value) {
            addCriterion("isEnable <=", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableLike(String value) {
            addCriterion("isEnable like", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotLike(String value) {
            addCriterion("isEnable not like", value, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableIn(List<String> values) {
            addCriterion("isEnable in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotIn(List<String> values) {
            addCriterion("isEnable not in", values, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableBetween(String value1, String value2) {
            addCriterion("isEnable between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andIsEnableNotBetween(String value1, String value2) {
            addCriterion("isEnable not between", value1, value2, "isEnable");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDroneIdIsNull() {
            addCriterion("droneId is null");
            return (Criteria) this;
        }

        public Criteria andDroneIdIsNotNull() {
            addCriterion("droneId is not null");
            return (Criteria) this;
        }

        public Criteria andDroneIdEqualTo(String value) {
            addCriterion("droneId =", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdNotEqualTo(String value) {
            addCriterion("droneId <>", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdGreaterThan(String value) {
            addCriterion("droneId >", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdGreaterThanOrEqualTo(String value) {
            addCriterion("droneId >=", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdLessThan(String value) {
            addCriterion("droneId <", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdLessThanOrEqualTo(String value) {
            addCriterion("droneId <=", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdLike(String value) {
            addCriterion("droneId like", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdNotLike(String value) {
            addCriterion("droneId not like", value, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdIn(List<String> values) {
            addCriterion("droneId in", values, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdNotIn(List<String> values) {
            addCriterion("droneId not in", values, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdBetween(String value1, String value2) {
            addCriterion("droneId between", value1, value2, "droneId");
            return (Criteria) this;
        }

        public Criteria andDroneIdNotBetween(String value1, String value2) {
            addCriterion("droneId not between", value1, value2, "droneId");
            return (Criteria) this;
        }

        public Criteria andJobOwnerIsNull() {
            addCriterion("jobOwner is null");
            return (Criteria) this;
        }

        public Criteria andJobOwnerIsNotNull() {
            addCriterion("jobOwner is not null");
            return (Criteria) this;
        }

        public Criteria andJobOwnerEqualTo(String value) {
            addCriterion("jobOwner =", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerNotEqualTo(String value) {
            addCriterion("jobOwner <>", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerGreaterThan(String value) {
            addCriterion("jobOwner >", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("jobOwner >=", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerLessThan(String value) {
            addCriterion("jobOwner <", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerLessThanOrEqualTo(String value) {
            addCriterion("jobOwner <=", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerLike(String value) {
            addCriterion("jobOwner like", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerNotLike(String value) {
            addCriterion("jobOwner not like", value, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerIn(List<String> values) {
            addCriterion("jobOwner in", values, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerNotIn(List<String> values) {
            addCriterion("jobOwner not in", values, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerBetween(String value1, String value2) {
            addCriterion("jobOwner between", value1, value2, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andJobOwnerNotBetween(String value1, String value2) {
            addCriterion("jobOwner not between", value1, value2, "jobOwner");
            return (Criteria) this;
        }

        public Criteria andInclude1IsNull() {
            addCriterion("Include1 is null");
            return (Criteria) this;
        }

        public Criteria andInclude1IsNotNull() {
            addCriterion("Include1 is not null");
            return (Criteria) this;
        }

        public Criteria andInclude1EqualTo(String value) {
            addCriterion("Include1 =", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1NotEqualTo(String value) {
            addCriterion("Include1 <>", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1GreaterThan(String value) {
            addCriterion("Include1 >", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1GreaterThanOrEqualTo(String value) {
            addCriterion("Include1 >=", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1LessThan(String value) {
            addCriterion("Include1 <", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1LessThanOrEqualTo(String value) {
            addCriterion("Include1 <=", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1Like(String value) {
            addCriterion("Include1 like", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1NotLike(String value) {
            addCriterion("Include1 not like", value, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1In(List<String> values) {
            addCriterion("Include1 in", values, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1NotIn(List<String> values) {
            addCriterion("Include1 not in", values, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1Between(String value1, String value2) {
            addCriterion("Include1 between", value1, value2, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude1NotBetween(String value1, String value2) {
            addCriterion("Include1 not between", value1, value2, "include1");
            return (Criteria) this;
        }

        public Criteria andInclude2IsNull() {
            addCriterion("Include2 is null");
            return (Criteria) this;
        }

        public Criteria andInclude2IsNotNull() {
            addCriterion("Include2 is not null");
            return (Criteria) this;
        }

        public Criteria andInclude2EqualTo(String value) {
            addCriterion("Include2 =", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2NotEqualTo(String value) {
            addCriterion("Include2 <>", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2GreaterThan(String value) {
            addCriterion("Include2 >", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2GreaterThanOrEqualTo(String value) {
            addCriterion("Include2 >=", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2LessThan(String value) {
            addCriterion("Include2 <", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2LessThanOrEqualTo(String value) {
            addCriterion("Include2 <=", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2Like(String value) {
            addCriterion("Include2 like", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2NotLike(String value) {
            addCriterion("Include2 not like", value, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2In(List<String> values) {
            addCriterion("Include2 in", values, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2NotIn(List<String> values) {
            addCriterion("Include2 not in", values, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2Between(String value1, String value2) {
            addCriterion("Include2 between", value1, value2, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude2NotBetween(String value1, String value2) {
            addCriterion("Include2 not between", value1, value2, "include2");
            return (Criteria) this;
        }

        public Criteria andInclude3IsNull() {
            addCriterion("Include3 is null");
            return (Criteria) this;
        }

        public Criteria andInclude3IsNotNull() {
            addCriterion("Include3 is not null");
            return (Criteria) this;
        }

        public Criteria andInclude3EqualTo(String value) {
            addCriterion("Include3 =", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3NotEqualTo(String value) {
            addCriterion("Include3 <>", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3GreaterThan(String value) {
            addCriterion("Include3 >", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3GreaterThanOrEqualTo(String value) {
            addCriterion("Include3 >=", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3LessThan(String value) {
            addCriterion("Include3 <", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3LessThanOrEqualTo(String value) {
            addCriterion("Include3 <=", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3Like(String value) {
            addCriterion("Include3 like", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3NotLike(String value) {
            addCriterion("Include3 not like", value, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3In(List<String> values) {
            addCriterion("Include3 in", values, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3NotIn(List<String> values) {
            addCriterion("Include3 not in", values, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3Between(String value1, String value2) {
            addCriterion("Include3 between", value1, value2, "include3");
            return (Criteria) this;
        }

        public Criteria andInclude3NotBetween(String value1, String value2) {
            addCriterion("Include3 not between", value1, value2, "include3");
            return (Criteria) this;
        }

        public Criteria andIsInclude1IsNull() {
            addCriterion("IsInclude1 is null");
            return (Criteria) this;
        }

        public Criteria andIsInclude1IsNotNull() {
            addCriterion("IsInclude1 is not null");
            return (Criteria) this;
        }

        public Criteria andIsInclude1EqualTo(String value) {
            addCriterion("IsInclude1 =", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1NotEqualTo(String value) {
            addCriterion("IsInclude1 <>", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1GreaterThan(String value) {
            addCriterion("IsInclude1 >", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1GreaterThanOrEqualTo(String value) {
            addCriterion("IsInclude1 >=", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1LessThan(String value) {
            addCriterion("IsInclude1 <", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1LessThanOrEqualTo(String value) {
            addCriterion("IsInclude1 <=", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1Like(String value) {
            addCriterion("IsInclude1 like", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1NotLike(String value) {
            addCriterion("IsInclude1 not like", value, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1In(List<String> values) {
            addCriterion("IsInclude1 in", values, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1NotIn(List<String> values) {
            addCriterion("IsInclude1 not in", values, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1Between(String value1, String value2) {
            addCriterion("IsInclude1 between", value1, value2, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude1NotBetween(String value1, String value2) {
            addCriterion("IsInclude1 not between", value1, value2, "isInclude1");
            return (Criteria) this;
        }

        public Criteria andIsInclude2IsNull() {
            addCriterion("IsInclude2 is null");
            return (Criteria) this;
        }

        public Criteria andIsInclude2IsNotNull() {
            addCriterion("IsInclude2 is not null");
            return (Criteria) this;
        }

        public Criteria andIsInclude2EqualTo(String value) {
            addCriterion("IsInclude2 =", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2NotEqualTo(String value) {
            addCriterion("IsInclude2 <>", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2GreaterThan(String value) {
            addCriterion("IsInclude2 >", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2GreaterThanOrEqualTo(String value) {
            addCriterion("IsInclude2 >=", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2LessThan(String value) {
            addCriterion("IsInclude2 <", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2LessThanOrEqualTo(String value) {
            addCriterion("IsInclude2 <=", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2Like(String value) {
            addCriterion("IsInclude2 like", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2NotLike(String value) {
            addCriterion("IsInclude2 not like", value, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2In(List<String> values) {
            addCriterion("IsInclude2 in", values, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2NotIn(List<String> values) {
            addCriterion("IsInclude2 not in", values, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2Between(String value1, String value2) {
            addCriterion("IsInclude2 between", value1, value2, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude2NotBetween(String value1, String value2) {
            addCriterion("IsInclude2 not between", value1, value2, "isInclude2");
            return (Criteria) this;
        }

        public Criteria andIsInclude3IsNull() {
            addCriterion("IsInclude3 is null");
            return (Criteria) this;
        }

        public Criteria andIsInclude3IsNotNull() {
            addCriterion("IsInclude3 is not null");
            return (Criteria) this;
        }

        public Criteria andIsInclude3EqualTo(String value) {
            addCriterion("IsInclude3 =", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3NotEqualTo(String value) {
            addCriterion("IsInclude3 <>", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3GreaterThan(String value) {
            addCriterion("IsInclude3 >", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3GreaterThanOrEqualTo(String value) {
            addCriterion("IsInclude3 >=", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3LessThan(String value) {
            addCriterion("IsInclude3 <", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3LessThanOrEqualTo(String value) {
            addCriterion("IsInclude3 <=", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3Like(String value) {
            addCriterion("IsInclude3 like", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3NotLike(String value) {
            addCriterion("IsInclude3 not like", value, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3In(List<String> values) {
            addCriterion("IsInclude3 in", values, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3NotIn(List<String> values) {
            addCriterion("IsInclude3 not in", values, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3Between(String value1, String value2) {
            addCriterion("IsInclude3 between", value1, value2, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andIsInclude3NotBetween(String value1, String value2) {
            addCriterion("IsInclude3 not between", value1, value2, "isInclude3");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("createTime like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("createTime not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("updateTime is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("updateTime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("updateTime =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("updateTime <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("updateTime >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("updateTime >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("updateTime <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("updateTime <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("updateTime like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("updateTime not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("updateTime in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("updateTime not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("updateTime between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("updateTime not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}