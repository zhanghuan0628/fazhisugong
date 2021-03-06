package com.ffxl.cloud.model.base;

import com.ffxl.platform.core.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseSgThemeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Page page;

    public BaseSgThemeExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsIsNull() {
            addCriterion("subject_counts is null");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsIsNotNull() {
            addCriterion("subject_counts is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsEqualTo(Integer value) {
            addCriterion("subject_counts =", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsNotEqualTo(Integer value) {
            addCriterion("subject_counts <>", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsGreaterThan(Integer value) {
            addCriterion("subject_counts >", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsGreaterThanOrEqualTo(Integer value) {
            addCriterion("subject_counts >=", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsLessThan(Integer value) {
            addCriterion("subject_counts <", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsLessThanOrEqualTo(Integer value) {
            addCriterion("subject_counts <=", value, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsIn(List<Integer> values) {
            addCriterion("subject_counts in", values, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsNotIn(List<Integer> values) {
            addCriterion("subject_counts not in", values, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsBetween(Integer value1, Integer value2) {
            addCriterion("subject_counts between", value1, value2, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andSubjectCountsNotBetween(Integer value1, Integer value2) {
            addCriterion("subject_counts not between", value1, value2, "subjectCounts");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSoreIsNull() {
            addCriterion("sore is null");
            return (Criteria) this;
        }

        public Criteria andSoreIsNotNull() {
            addCriterion("sore is not null");
            return (Criteria) this;
        }

        public Criteria andSoreEqualTo(Integer value) {
            addCriterion("sore =", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreNotEqualTo(Integer value) {
            addCriterion("sore <>", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreGreaterThan(Integer value) {
            addCriterion("sore >", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("sore >=", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreLessThan(Integer value) {
            addCriterion("sore <", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreLessThanOrEqualTo(Integer value) {
            addCriterion("sore <=", value, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreIn(List<Integer> values) {
            addCriterion("sore in", values, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreNotIn(List<Integer> values) {
            addCriterion("sore not in", values, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreBetween(Integer value1, Integer value2) {
            addCriterion("sore between", value1, value2, "sore");
            return (Criteria) this;
        }

        public Criteria andSoreNotBetween(Integer value1, Integer value2) {
            addCriterion("sore not between", value1, value2, "sore");
            return (Criteria) this;
        }

        public Criteria andPassIsNull() {
            addCriterion("pass is null");
            return (Criteria) this;
        }

        public Criteria andPassIsNotNull() {
            addCriterion("pass is not null");
            return (Criteria) this;
        }

        public Criteria andPassEqualTo(String value) {
            addCriterion("pass =", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotEqualTo(String value) {
            addCriterion("pass <>", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassGreaterThan(String value) {
            addCriterion("pass >", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassGreaterThanOrEqualTo(String value) {
            addCriterion("pass >=", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassLessThan(String value) {
            addCriterion("pass <", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassLessThanOrEqualTo(String value) {
            addCriterion("pass <=", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassLike(String value) {
            addCriterion("pass like", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotLike(String value) {
            addCriterion("pass not like", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassIn(List<String> values) {
            addCriterion("pass in", values, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotIn(List<String> values) {
            addCriterion("pass not in", values, "pass");
            return (Criteria) this;
        }

        public Criteria andPassBetween(String value1, String value2) {
            addCriterion("pass between", value1, value2, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotBetween(String value1, String value2) {
            addCriterion("pass not between", value1, value2, "pass");
            return (Criteria) this;
        }

        public Criteria andNoPassIsNull() {
            addCriterion("no_pass is null");
            return (Criteria) this;
        }

        public Criteria andNoPassIsNotNull() {
            addCriterion("no_pass is not null");
            return (Criteria) this;
        }

        public Criteria andNoPassEqualTo(String value) {
            addCriterion("no_pass =", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassNotEqualTo(String value) {
            addCriterion("no_pass <>", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassGreaterThan(String value) {
            addCriterion("no_pass >", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassGreaterThanOrEqualTo(String value) {
            addCriterion("no_pass >=", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassLessThan(String value) {
            addCriterion("no_pass <", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassLessThanOrEqualTo(String value) {
            addCriterion("no_pass <=", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassLike(String value) {
            addCriterion("no_pass like", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassNotLike(String value) {
            addCriterion("no_pass not like", value, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassIn(List<String> values) {
            addCriterion("no_pass in", values, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassNotIn(List<String> values) {
            addCriterion("no_pass not in", values, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassBetween(String value1, String value2) {
            addCriterion("no_pass between", value1, value2, "noPass");
            return (Criteria) this;
        }

        public Criteria andNoPassNotBetween(String value1, String value2) {
            addCriterion("no_pass not between", value1, value2, "noPass");
            return (Criteria) this;
        }

        public Criteria andGoodIsNull() {
            addCriterion("good is null");
            return (Criteria) this;
        }

        public Criteria andGoodIsNotNull() {
            addCriterion("good is not null");
            return (Criteria) this;
        }

        public Criteria andGoodEqualTo(String value) {
            addCriterion("good =", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodNotEqualTo(String value) {
            addCriterion("good <>", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodGreaterThan(String value) {
            addCriterion("good >", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodGreaterThanOrEqualTo(String value) {
            addCriterion("good >=", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodLessThan(String value) {
            addCriterion("good <", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodLessThanOrEqualTo(String value) {
            addCriterion("good <=", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodLike(String value) {
            addCriterion("good like", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodNotLike(String value) {
            addCriterion("good not like", value, "good");
            return (Criteria) this;
        }

        public Criteria andGoodIn(List<String> values) {
            addCriterion("good in", values, "good");
            return (Criteria) this;
        }

        public Criteria andGoodNotIn(List<String> values) {
            addCriterion("good not in", values, "good");
            return (Criteria) this;
        }

        public Criteria andGoodBetween(String value1, String value2) {
            addCriterion("good between", value1, value2, "good");
            return (Criteria) this;
        }

        public Criteria andGoodNotBetween(String value1, String value2) {
            addCriterion("good not between", value1, value2, "good");
            return (Criteria) this;
        }

        public Criteria andFineIsNull() {
            addCriterion("fine is null");
            return (Criteria) this;
        }

        public Criteria andFineIsNotNull() {
            addCriterion("fine is not null");
            return (Criteria) this;
        }

        public Criteria andFineEqualTo(String value) {
            addCriterion("fine =", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotEqualTo(String value) {
            addCriterion("fine <>", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineGreaterThan(String value) {
            addCriterion("fine >", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineGreaterThanOrEqualTo(String value) {
            addCriterion("fine >=", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineLessThan(String value) {
            addCriterion("fine <", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineLessThanOrEqualTo(String value) {
            addCriterion("fine <=", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineLike(String value) {
            addCriterion("fine like", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotLike(String value) {
            addCriterion("fine not like", value, "fine");
            return (Criteria) this;
        }

        public Criteria andFineIn(List<String> values) {
            addCriterion("fine in", values, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotIn(List<String> values) {
            addCriterion("fine not in", values, "fine");
            return (Criteria) this;
        }

        public Criteria andFineBetween(String value1, String value2) {
            addCriterion("fine between", value1, value2, "fine");
            return (Criteria) this;
        }

        public Criteria andFineNotBetween(String value1, String value2) {
            addCriterion("fine not between", value1, value2, "fine");
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