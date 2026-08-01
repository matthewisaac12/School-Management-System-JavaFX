package com.example.matthewsms.entity;

public class Exam {

    private int examId;
    private int studentId;
    private int subjectId;
    private int termId;
    private String examType;
    private String examDate;
    private double score;
    private String grade;
    private String createdAt;
    private String updatedAt;

    public Exam() {
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public Exam(int examId, int studentId, int subjectId,
                int termId, String examType, String examDate,
                double score, String grade,
                String createdAt, String updatedAt) {
        this.examId = examId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.termId = termId;
        this.examType = examType;
        this.examDate = examDate;
        this.score = score;
        this.grade = grade;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;


    }
}