package com.example.matthewsms.entity;

public class Term {

    private int termId;
    private int sessionId;
    private String termName;
    private String startDate;
    private String endDate;
    private boolean isCurrent;
    private String createdAt;
    private String updatedAt;

    public Term() {
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Term(int termId, int sessionId, String termName,
                String startDate, String endDate,
                boolean isCurrent,
                String createdAt,
                String updatedAt) {

        this.termId = termId;
        this.sessionId = sessionId;
        this.termName = termName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCurrent = isCurrent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }
}