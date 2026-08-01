package com.example.matthewsms.entity;

public class Attendance {

    private int attendanceId;
    private int studentId;
    private int classId;
    private int termId;
    private String attendanceDate;
    private String status;
    private String createdAt;

    public Attendance() {
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
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

    public Attendance(int attendanceId, int studentId, int classId,
                      int termId, String attendanceDate,
                      String status, String createdAt) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.classId = classId;
        this.termId = termId;
        this.attendanceDate = attendanceDate;
        this.status = status;
        this.createdAt = createdAt;


    }
}