package com.example.matthewsms.entity;


public class Subject {

    private int subjectId;
    private String subjectName;
    private String subjectCode;
    private String description;
    private String createdAt;
    private String updatedAt;

    public Subject() {}

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Subject(int subjectId, String subjectName,
                   String subjectCode, String description,
                   String createdAt,
                   String updatedAt) {

        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }
}