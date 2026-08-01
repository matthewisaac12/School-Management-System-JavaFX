package com.example.matthewsms.entity;

public class Enrollment {

    private int enrollmentId;
    private int studentId;
    private int subjectId;
    private int termId;
    private String enrollmentDate;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Enrollment() {
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Enrollment(int enrollmentId, int studentId, int subjectId,
                      int termId, String enrollmentDate, String status,
                      String createdAt, String updatedAt) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.termId = termId;
        this.enrollmentDate = enrollmentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }
}