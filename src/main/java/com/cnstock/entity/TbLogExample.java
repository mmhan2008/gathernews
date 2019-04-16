package com.cnstock.entity;

import java.util.ArrayList;
import java.util.List;

public class TbLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbLogExample() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("logId is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("logId is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(Integer value) {
            addCriterion("logId =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(Integer value) {
            addCriterion("logId <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(Integer value) {
            addCriterion("logId >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("logId >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(Integer value) {
            addCriterion("logId <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(Integer value) {
            addCriterion("logId <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<Integer> values) {
            addCriterion("logId in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<Integer> values) {
            addCriterion("logId not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(Integer value1, Integer value2) {
            addCriterion("logId between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(Integer value1, Integer value2) {
            addCriterion("logId not between", value1, value2, "logId");
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

        public Criteria andNewLinkIsNull() {
            addCriterion("newLink is null");
            return (Criteria) this;
        }

        public Criteria andNewLinkIsNotNull() {
            addCriterion("newLink is not null");
            return (Criteria) this;
        }

        public Criteria andNewLinkEqualTo(Integer value) {
            addCriterion("newLink =", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkNotEqualTo(Integer value) {
            addCriterion("newLink <>", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkGreaterThan(Integer value) {
            addCriterion("newLink >", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkGreaterThanOrEqualTo(Integer value) {
            addCriterion("newLink >=", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkLessThan(Integer value) {
            addCriterion("newLink <", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkLessThanOrEqualTo(Integer value) {
            addCriterion("newLink <=", value, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkIn(List<Integer> values) {
            addCriterion("newLink in", values, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkNotIn(List<Integer> values) {
            addCriterion("newLink not in", values, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkBetween(Integer value1, Integer value2) {
            addCriterion("newLink between", value1, value2, "newLink");
            return (Criteria) this;
        }

        public Criteria andNewLinkNotBetween(Integer value1, Integer value2) {
            addCriterion("newLink not between", value1, value2, "newLink");
            return (Criteria) this;
        }

        public Criteria andLinkCountIsNull() {
            addCriterion("linkCount is null");
            return (Criteria) this;
        }

        public Criteria andLinkCountIsNotNull() {
            addCriterion("linkCount is not null");
            return (Criteria) this;
        }

        public Criteria andLinkCountEqualTo(Integer value) {
            addCriterion("linkCount =", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountNotEqualTo(Integer value) {
            addCriterion("linkCount <>", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountGreaterThan(Integer value) {
            addCriterion("linkCount >", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("linkCount >=", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountLessThan(Integer value) {
            addCriterion("linkCount <", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountLessThanOrEqualTo(Integer value) {
            addCriterion("linkCount <=", value, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountIn(List<Integer> values) {
            addCriterion("linkCount in", values, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountNotIn(List<Integer> values) {
            addCriterion("linkCount not in", values, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountBetween(Integer value1, Integer value2) {
            addCriterion("linkCount between", value1, value2, "linkCount");
            return (Criteria) this;
        }

        public Criteria andLinkCountNotBetween(Integer value1, Integer value2) {
            addCriterion("linkCount not between", value1, value2, "linkCount");
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

        public Criteria andVar1IsNull() {
            addCriterion("var1 is null");
            return (Criteria) this;
        }

        public Criteria andVar1IsNotNull() {
            addCriterion("var1 is not null");
            return (Criteria) this;
        }

        public Criteria andVar1EqualTo(String value) {
            addCriterion("var1 =", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1NotEqualTo(String value) {
            addCriterion("var1 <>", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1GreaterThan(String value) {
            addCriterion("var1 >", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1GreaterThanOrEqualTo(String value) {
            addCriterion("var1 >=", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1LessThan(String value) {
            addCriterion("var1 <", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1LessThanOrEqualTo(String value) {
            addCriterion("var1 <=", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1Like(String value) {
            addCriterion("var1 like", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1NotLike(String value) {
            addCriterion("var1 not like", value, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1In(List<String> values) {
            addCriterion("var1 in", values, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1NotIn(List<String> values) {
            addCriterion("var1 not in", values, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1Between(String value1, String value2) {
            addCriterion("var1 between", value1, value2, "var1");
            return (Criteria) this;
        }

        public Criteria andVar1NotBetween(String value1, String value2) {
            addCriterion("var1 not between", value1, value2, "var1");
            return (Criteria) this;
        }

        public Criteria andVar2IsNull() {
            addCriterion("var2 is null");
            return (Criteria) this;
        }

        public Criteria andVar2IsNotNull() {
            addCriterion("var2 is not null");
            return (Criteria) this;
        }

        public Criteria andVar2EqualTo(String value) {
            addCriterion("var2 =", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2NotEqualTo(String value) {
            addCriterion("var2 <>", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2GreaterThan(String value) {
            addCriterion("var2 >", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2GreaterThanOrEqualTo(String value) {
            addCriterion("var2 >=", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2LessThan(String value) {
            addCriterion("var2 <", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2LessThanOrEqualTo(String value) {
            addCriterion("var2 <=", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2Like(String value) {
            addCriterion("var2 like", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2NotLike(String value) {
            addCriterion("var2 not like", value, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2In(List<String> values) {
            addCriterion("var2 in", values, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2NotIn(List<String> values) {
            addCriterion("var2 not in", values, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2Between(String value1, String value2) {
            addCriterion("var2 between", value1, value2, "var2");
            return (Criteria) this;
        }

        public Criteria andVar2NotBetween(String value1, String value2) {
            addCriterion("var2 not between", value1, value2, "var2");
            return (Criteria) this;
        }

        public Criteria andVar3IsNull() {
            addCriterion("var3 is null");
            return (Criteria) this;
        }

        public Criteria andVar3IsNotNull() {
            addCriterion("var3 is not null");
            return (Criteria) this;
        }

        public Criteria andVar3EqualTo(String value) {
            addCriterion("var3 =", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3NotEqualTo(String value) {
            addCriterion("var3 <>", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3GreaterThan(String value) {
            addCriterion("var3 >", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3GreaterThanOrEqualTo(String value) {
            addCriterion("var3 >=", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3LessThan(String value) {
            addCriterion("var3 <", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3LessThanOrEqualTo(String value) {
            addCriterion("var3 <=", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3Like(String value) {
            addCriterion("var3 like", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3NotLike(String value) {
            addCriterion("var3 not like", value, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3In(List<String> values) {
            addCriterion("var3 in", values, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3NotIn(List<String> values) {
            addCriterion("var3 not in", values, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3Between(String value1, String value2) {
            addCriterion("var3 between", value1, value2, "var3");
            return (Criteria) this;
        }

        public Criteria andVar3NotBetween(String value1, String value2) {
            addCriterion("var3 not between", value1, value2, "var3");
            return (Criteria) this;
        }

        public Criteria andVar4IsNull() {
            addCriterion("var4 is null");
            return (Criteria) this;
        }

        public Criteria andVar4IsNotNull() {
            addCriterion("var4 is not null");
            return (Criteria) this;
        }

        public Criteria andVar4EqualTo(String value) {
            addCriterion("var4 =", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4NotEqualTo(String value) {
            addCriterion("var4 <>", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4GreaterThan(String value) {
            addCriterion("var4 >", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4GreaterThanOrEqualTo(String value) {
            addCriterion("var4 >=", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4LessThan(String value) {
            addCriterion("var4 <", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4LessThanOrEqualTo(String value) {
            addCriterion("var4 <=", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4Like(String value) {
            addCriterion("var4 like", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4NotLike(String value) {
            addCriterion("var4 not like", value, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4In(List<String> values) {
            addCriterion("var4 in", values, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4NotIn(List<String> values) {
            addCriterion("var4 not in", values, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4Between(String value1, String value2) {
            addCriterion("var4 between", value1, value2, "var4");
            return (Criteria) this;
        }

        public Criteria andVar4NotBetween(String value1, String value2) {
            addCriterion("var4 not between", value1, value2, "var4");
            return (Criteria) this;
        }

        public Criteria andVar5IsNull() {
            addCriterion("var5 is null");
            return (Criteria) this;
        }

        public Criteria andVar5IsNotNull() {
            addCriterion("var5 is not null");
            return (Criteria) this;
        }

        public Criteria andVar5EqualTo(String value) {
            addCriterion("var5 =", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5NotEqualTo(String value) {
            addCriterion("var5 <>", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5GreaterThan(String value) {
            addCriterion("var5 >", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5GreaterThanOrEqualTo(String value) {
            addCriterion("var5 >=", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5LessThan(String value) {
            addCriterion("var5 <", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5LessThanOrEqualTo(String value) {
            addCriterion("var5 <=", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5Like(String value) {
            addCriterion("var5 like", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5NotLike(String value) {
            addCriterion("var5 not like", value, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5In(List<String> values) {
            addCriterion("var5 in", values, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5NotIn(List<String> values) {
            addCriterion("var5 not in", values, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5Between(String value1, String value2) {
            addCriterion("var5 between", value1, value2, "var5");
            return (Criteria) this;
        }

        public Criteria andVar5NotBetween(String value1, String value2) {
            addCriterion("var5 not between", value1, value2, "var5");
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