package com.ffxl.cloud.model.base;

import java.io.Serializable;
import java.util.Date;

public class BaseAAnswers implements Serializable {
    private String id;

    private String userBaseId;

    private String questionId;

    private String content;

    private Boolean doing;

    private String doingId;

    private Date answerDate;

    private String type;

    private Byte questionerIsRead;

    private Byte doingIsRead;

    private Boolean invented;

    private String voiceSrc;

    private String voiceLength;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserBaseId() {
        return userBaseId;
    }

    public void setUserBaseId(String userBaseId) {
        this.userBaseId = userBaseId == null ? null : userBaseId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Boolean getDoing() {
        return doing;
    }

    public void setDoing(Boolean doing) {
        this.doing = doing;
    }

    public String getDoingId() {
        return doingId;
    }

    public void setDoingId(String doingId) {
        this.doingId = doingId == null ? null : doingId.trim();
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getQuestionerIsRead() {
        return questionerIsRead;
    }

    public void setQuestionerIsRead(Byte questionerIsRead) {
        this.questionerIsRead = questionerIsRead;
    }

    public Byte getDoingIsRead() {
        return doingIsRead;
    }

    public void setDoingIsRead(Byte doingIsRead) {
        this.doingIsRead = doingIsRead;
    }

    public Boolean getInvented() {
        return invented;
    }

    public void setInvented(Boolean invented) {
        this.invented = invented;
    }

    public String getVoiceSrc() {
        return voiceSrc;
    }

    public void setVoiceSrc(String voiceSrc) {
        this.voiceSrc = voiceSrc == null ? null : voiceSrc.trim();
    }

    public String getVoiceLength() {
        return voiceLength;
    }

    public void setVoiceLength(String voiceLength) {
        this.voiceLength = voiceLength == null ? null : voiceLength.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BaseAAnswers other = (BaseAAnswers) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserBaseId() == null ? other.getUserBaseId() == null : this.getUserBaseId().equals(other.getUserBaseId()))
            && (this.getQuestionId() == null ? other.getQuestionId() == null : this.getQuestionId().equals(other.getQuestionId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getDoing() == null ? other.getDoing() == null : this.getDoing().equals(other.getDoing()))
            && (this.getDoingId() == null ? other.getDoingId() == null : this.getDoingId().equals(other.getDoingId()))
            && (this.getAnswerDate() == null ? other.getAnswerDate() == null : this.getAnswerDate().equals(other.getAnswerDate()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getQuestionerIsRead() == null ? other.getQuestionerIsRead() == null : this.getQuestionerIsRead().equals(other.getQuestionerIsRead()))
            && (this.getDoingIsRead() == null ? other.getDoingIsRead() == null : this.getDoingIsRead().equals(other.getDoingIsRead()))
            && (this.getInvented() == null ? other.getInvented() == null : this.getInvented().equals(other.getInvented()))
            && (this.getVoiceSrc() == null ? other.getVoiceSrc() == null : this.getVoiceSrc().equals(other.getVoiceSrc()))
            && (this.getVoiceLength() == null ? other.getVoiceLength() == null : this.getVoiceLength().equals(other.getVoiceLength()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserBaseId() == null) ? 0 : getUserBaseId().hashCode());
        result = prime * result + ((getQuestionId() == null) ? 0 : getQuestionId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getDoing() == null) ? 0 : getDoing().hashCode());
        result = prime * result + ((getDoingId() == null) ? 0 : getDoingId().hashCode());
        result = prime * result + ((getAnswerDate() == null) ? 0 : getAnswerDate().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getQuestionerIsRead() == null) ? 0 : getQuestionerIsRead().hashCode());
        result = prime * result + ((getDoingIsRead() == null) ? 0 : getDoingIsRead().hashCode());
        result = prime * result + ((getInvented() == null) ? 0 : getInvented().hashCode());
        result = prime * result + ((getVoiceSrc() == null) ? 0 : getVoiceSrc().hashCode());
        result = prime * result + ((getVoiceLength() == null) ? 0 : getVoiceLength().hashCode());
        return result;
    }
}