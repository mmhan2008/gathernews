package com.cnstock.entity;

import java.util.ArrayList;
import java.util.List;

public class TbConfigureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbConfigureExample() {
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

        public Criteria andConfigureIdIsNull() {
            addCriterion("configureId is null");
            return (Criteria) this;
        }

        public Criteria andConfigureIdIsNotNull() {
            addCriterion("configureId is not null");
            return (Criteria) this;
        }

        public Criteria andConfigureIdEqualTo(Integer value) {
            addCriterion("configureId =", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdNotEqualTo(Integer value) {
            addCriterion("configureId <>", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdGreaterThan(Integer value) {
            addCriterion("configureId >", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("configureId >=", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdLessThan(Integer value) {
            addCriterion("configureId <", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdLessThanOrEqualTo(Integer value) {
            addCriterion("configureId <=", value, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdIn(List<Integer> values) {
            addCriterion("configureId in", values, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdNotIn(List<Integer> values) {
            addCriterion("configureId not in", values, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdBetween(Integer value1, Integer value2) {
            addCriterion("configureId between", value1, value2, "configureId");
            return (Criteria) this;
        }

        public Criteria andConfigureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("configureId not between", value1, value2, "configureId");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIsNull() {
            addCriterion("cacheSize is null");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIsNotNull() {
            addCriterion("cacheSize is not null");
            return (Criteria) this;
        }

        public Criteria andCacheSizeEqualTo(Integer value) {
            addCriterion("cacheSize =", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotEqualTo(Integer value) {
            addCriterion("cacheSize <>", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeGreaterThan(Integer value) {
            addCriterion("cacheSize >", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cacheSize >=", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeLessThan(Integer value) {
            addCriterion("cacheSize <", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeLessThanOrEqualTo(Integer value) {
            addCriterion("cacheSize <=", value, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeIn(List<Integer> values) {
            addCriterion("cacheSize in", values, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotIn(List<Integer> values) {
            addCriterion("cacheSize not in", values, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeBetween(Integer value1, Integer value2) {
            addCriterion("cacheSize between", value1, value2, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andCacheSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("cacheSize not between", value1, value2, "cacheSize");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeIsNull() {
            addCriterion("heartbeatTime is null");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeIsNotNull() {
            addCriterion("heartbeatTime is not null");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeEqualTo(Integer value) {
            addCriterion("heartbeatTime =", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotEqualTo(Integer value) {
            addCriterion("heartbeatTime <>", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeGreaterThan(Integer value) {
            addCriterion("heartbeatTime >", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("heartbeatTime >=", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeLessThan(Integer value) {
            addCriterion("heartbeatTime <", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeLessThanOrEqualTo(Integer value) {
            addCriterion("heartbeatTime <=", value, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeIn(List<Integer> values) {
            addCriterion("heartbeatTime in", values, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotIn(List<Integer> values) {
            addCriterion("heartbeatTime not in", values, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeBetween(Integer value1, Integer value2) {
            addCriterion("heartbeatTime between", value1, value2, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andHeartbeatTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("heartbeatTime not between", value1, value2, "heartbeatTime");
            return (Criteria) this;
        }

        public Criteria andTaskProcessIsNull() {
            addCriterion("taskProcess is null");
            return (Criteria) this;
        }

        public Criteria andTaskProcessIsNotNull() {
            addCriterion("taskProcess is not null");
            return (Criteria) this;
        }

        public Criteria andTaskProcessEqualTo(Integer value) {
            addCriterion("taskProcess =", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessNotEqualTo(Integer value) {
            addCriterion("taskProcess <>", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessGreaterThan(Integer value) {
            addCriterion("taskProcess >", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskProcess >=", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessLessThan(Integer value) {
            addCriterion("taskProcess <", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessLessThanOrEqualTo(Integer value) {
            addCriterion("taskProcess <=", value, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessIn(List<Integer> values) {
            addCriterion("taskProcess in", values, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessNotIn(List<Integer> values) {
            addCriterion("taskProcess not in", values, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessBetween(Integer value1, Integer value2) {
            addCriterion("taskProcess between", value1, value2, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskProcessNotBetween(Integer value1, Integer value2) {
            addCriterion("taskProcess not between", value1, value2, "taskProcess");
            return (Criteria) this;
        }

        public Criteria andTaskCountIsNull() {
            addCriterion("taskCount is null");
            return (Criteria) this;
        }

        public Criteria andTaskCountIsNotNull() {
            addCriterion("taskCount is not null");
            return (Criteria) this;
        }

        public Criteria andTaskCountEqualTo(Integer value) {
            addCriterion("taskCount =", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotEqualTo(Integer value) {
            addCriterion("taskCount <>", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThan(Integer value) {
            addCriterion("taskCount >", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("taskCount >=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThan(Integer value) {
            addCriterion("taskCount <", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountLessThanOrEqualTo(Integer value) {
            addCriterion("taskCount <=", value, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountIn(List<Integer> values) {
            addCriterion("taskCount in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotIn(List<Integer> values) {
            addCriterion("taskCount not in", values, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountBetween(Integer value1, Integer value2) {
            addCriterion("taskCount between", value1, value2, "taskCount");
            return (Criteria) this;
        }

        public Criteria andTaskCountNotBetween(Integer value1, Integer value2) {
            addCriterion("taskCount not between", value1, value2, "taskCount");
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