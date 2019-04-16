package com.cnstock.entity;

import java.util.ArrayList;
import java.util.List;

public class TbErrorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbErrorExample() {
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

        public Criteria andErrorIdIsNull() {
            addCriterion("errorId is null");
            return (Criteria) this;
        }

        public Criteria andErrorIdIsNotNull() {
            addCriterion("errorId is not null");
            return (Criteria) this;
        }

        public Criteria andErrorIdEqualTo(String value) {
            addCriterion("errorId =", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdNotEqualTo(String value) {
            addCriterion("errorId <>", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdGreaterThan(String value) {
            addCriterion("errorId >", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdGreaterThanOrEqualTo(String value) {
            addCriterion("errorId >=", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdLessThan(String value) {
            addCriterion("errorId <", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdLessThanOrEqualTo(String value) {
            addCriterion("errorId <=", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdLike(String value) {
            addCriterion("errorId like", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdNotLike(String value) {
            addCriterion("errorId not like", value, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdIn(List<String> values) {
            addCriterion("errorId in", values, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdNotIn(List<String> values) {
            addCriterion("errorId not in", values, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdBetween(String value1, String value2) {
            addCriterion("errorId between", value1, value2, "errorId");
            return (Criteria) this;
        }

        public Criteria andErrorIdNotBetween(String value1, String value2) {
            addCriterion("errorId not between", value1, value2, "errorId");
            return (Criteria) this;
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

        public Criteria andErrorTypeIsNull() {
            addCriterion("errorType is null");
            return (Criteria) this;
        }

        public Criteria andErrorTypeIsNotNull() {
            addCriterion("errorType is not null");
            return (Criteria) this;
        }

        public Criteria andErrorTypeEqualTo(Integer value) {
            addCriterion("errorType =", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotEqualTo(Integer value) {
            addCriterion("errorType <>", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeGreaterThan(Integer value) {
            addCriterion("errorType >", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("errorType >=", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeLessThan(Integer value) {
            addCriterion("errorType <", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeLessThanOrEqualTo(Integer value) {
            addCriterion("errorType <=", value, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeIn(List<Integer> values) {
            addCriterion("errorType in", values, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotIn(List<Integer> values) {
            addCriterion("errorType not in", values, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeBetween(Integer value1, Integer value2) {
            addCriterion("errorType between", value1, value2, "errorType");
            return (Criteria) this;
        }

        public Criteria andErrorTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("errorType not between", value1, value2, "errorType");
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