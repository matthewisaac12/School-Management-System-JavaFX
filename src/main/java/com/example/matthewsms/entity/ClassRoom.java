package com.example.matthewsms.entity;

public class ClassRoom {

    private int classId;
    private String className;
    private String gradeLevel;
    private String section;
    private Integer teacherId;
    private String createdAt;
    private String updatedAt;

    public ClassRoom() {}

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
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

    public ClassRoom(int classId, String className,
                     String gradeLevel, String section,
                     Integer teacherId,
                     String createdAt,
                     String updatedAt) {

        this.classId = classId;
        this.className = className;
        this.gradeLevel = gradeLevel;
        this.section = section;
        this.teacherId = teacherId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }
}