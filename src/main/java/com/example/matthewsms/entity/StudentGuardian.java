package com.example.matthewsms.entity;

public class StudentGuardian {

    private int studentId;
    private int guardianId;
    private boolean isPrimary;

    public StudentGuardian() {
    }

    public StudentGuardian(int studentId, int guardianId, boolean isPrimary) {
        this.studentId = studentId;
        this.guardianId = guardianId;
        this.isPrimary = isPrimary;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }

    public int getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(int guardianId) {
        this.guardianId = guardianId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}