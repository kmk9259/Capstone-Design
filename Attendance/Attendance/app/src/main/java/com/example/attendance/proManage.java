package com.example.attendance;

public class proManage {

    private int courseID;
    private String courseTitle;
    private  String u_name;
    private  String courseTime;
    private  String courseRoom;
    private  String three;
    private  String Check_time;

    public proManage(String courseTitle, String u_name, String courseTime, String courseRoom, String three, String check_time) {
        this.courseTitle = courseTitle;
        this.u_name = u_name;
        this.courseTime = courseTime;
        this.courseRoom = courseRoom;
        this.three = three;
        this.Check_time = check_time;
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

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getCourseTime() { return courseTime; }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseRoom() {
        return courseRoom;
    }

    public void setCourseRoom(String courseRoom) {
        this.courseRoom = courseRoom;
    }

    public String getCheck_time() { return Check_time; }

    public void setCheck_time(String Check_time) { this.Check_time = Check_time; }


}