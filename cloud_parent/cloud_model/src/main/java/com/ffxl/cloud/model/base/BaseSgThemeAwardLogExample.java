package com.ffxl.cloud.model.base;

import com.ffxl.platform.core.Page;
import java.util.ArrayList;
import java.util.List;

public class BaseSgThemeAwardLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public BaseSgThemeAwardLogExample() {
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

    public void setPage(Page page) {
        this.page=page;
    }

    public Page getPage() {
        return page;
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNull() {
            addCriterion("answer_id is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIsNotNull() {
            addCriterion("answer_id is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerIdEqualTo(String value) {
            addCriterion("answer_id =", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotEqualTo(String value) {
            addCriterion("answer_id <>", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThan(String value) {
            addCriterion("answer_id >", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdGreaterThanOrEqualTo(String value) {
            addCriterion("answer_id >=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThan(String value) {
            addCriterion("answer_id <", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLessThanOrEqualTo(String value) {
            addCriterion("answer_id <=", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdLike(String value) {
            addCriterion("answer_id like", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotLike(String value) {
            addCriterion("answer_id not like", value, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdIn(List<String> values) {
            addCriterion("answer_id in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotIn(List<String> values) {
            addCriterion("answer_id not in", values, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdBetween(String value1, String value2) {
            addCriterion("answer_id between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andAnswerIdNotBetween(String value1, String value2) {
            addCriterion("answer_id not between", value1, value2, "answerId");
            return (Criteria) this;
        }

        public Criteria andThemeIdIsNull() {
            addCriterion("theme_id is null");
            return (Criteria) this;
        }

        public Criteria andThemeIdIsNotNull() {
            addCriterion("theme_id is not null");
            return (Criteria) this;
        }

        public Criteria andThemeIdEqualTo(String value) {
            addCriterion("theme_id =", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotEqualTo(String value) {
            addCriterion("theme_id <>", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdGreaterThan(String value) {
            addCriterion("theme_id >", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdGreaterThanOrEqualTo(String value) {
            addCriterion("theme_id >=", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdLessThan(String value) {
            addCriterion("theme_id <", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdLessThanOrEqualTo(String value) {
            addCriterion("theme_id <=", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdLike(String value) {
            addCriterion("theme_id like", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotLike(String value) {
            addCriterion("theme_id not like", value, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdIn(List<String> values) {
            addCriterion("theme_id in", values, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotIn(List<String> values) {
            addCriterion("theme_id not in", values, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdBetween(String value1, String value2) {
            addCriterion("theme_id between", value1, value2, "themeId");
            return (Criteria) this;
        }

        public Criteria andThemeIdNotBetween(String value1, String value2) {
            addCriterion("theme_id not between", value1, value2, "themeId");
            return (Criteria) this;
        }

        public Criteria andAwardCodeIsNull() {
            addCriterion("award_code is null");
            return (Criteria) this;
        }

        public Criteria andAwardCodeIsNotNull() {
            addCriterion("award_code is not null");
            return (Criteria) this;
        }

        public Criteria andAwardCodeEqualTo(String value) {
            addCriterion("award_code =", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeNotEqualTo(String value) {
            addCriterion("award_code <>", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeGreaterThan(String value) {
            addCriterion("award_code >", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeGreaterThanOrEqualTo(String value) {
            addCriterion("award_code >=", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeLessThan(String value) {
            addCriterion("award_code <", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeLessThanOrEqualTo(String value) {
            addCriterion("award_code <=", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeLike(String value) {
            addCriterion("award_code like", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeNotLike(String value) {
            addCriterion("award_code not like", value, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeIn(List<String> values) {
            addCriterion("award_code in", values, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeNotIn(List<String> values) {
            addCriterion("award_code not in", values, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeBetween(String value1, String value2) {
            addCriterion("award_code between", value1, value2, "awardCode");
            return (Criteria) this;
        }

        public Criteria andAwardCodeNotBetween(String value1, String value2) {
            addCriterion("award_code not between", value1, value2, "awardCode");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andReceiveIsNull() {
            addCriterion("is_receive is null");
            return (Criteria) this;
        }

        public Criteria andReceiveIsNotNull() {
            addCriterion("is_receive is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveEqualTo(Boolean value) {
            addCriterion("is_receive =", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotEqualTo(Boolean value) {
            addCriterion("is_receive <>", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveGreaterThan(Boolean value) {
            addCriterion("is_receive >", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_receive >=", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveLessThan(Boolean value) {
            addCriterion("is_receive <", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveLessThanOrEqualTo(Boolean value) {
            addCriterion("is_receive <=", value, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveIn(List<Boolean> values) {
            addCriterion("is_receive in", values, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotIn(List<Boolean> values) {
            addCriterion("is_receive not in", values, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveBetween(Boolean value1, Boolean value2) {
            addCriterion("is_receive between", value1, value2, "receive");
            return (Criteria) this;
        }

        public Criteria andReceiveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_receive not between", value1, value2, "receive");
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