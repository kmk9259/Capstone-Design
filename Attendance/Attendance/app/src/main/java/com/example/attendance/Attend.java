package com.example.attendance;

public class Attend {

    private int courseID;
    public String courseTitle;
    private  String courseProfessor;
    private  String courseTime;
    private  String courseRoom;
    private  String three;
    private  String Check_time;

    public Attend(String courseTitle, String courseProfessor, String courseTime, String courseRoom, String three, String Check_time) {
        this.courseTitle = courseTitle;
        this.courseProfessor = courseProfessor;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.three = three;
        this.Check_time=Check_time;
    }
    public int getCourseID() { return courseID; }

    public void setCourseID(int courseID) { this.courseID = courseID; }

    public String getThree() { return three; }

    public void setThree(String three) { this.three = three; }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) { this.courseProfessor = courseProfessor; }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    public String getCheck_time() {
        return Check_time;
    }

    public void setCheck_time(String Check_time) {
        this.Check_time = Check_time;
    }


}