package com.example.lms;

public class TableItemCustom {

    private int sub_id;
    private String user_email;
    private String file_name;

    private String file_status;

    private String course_name;

    public TableItemCustom() {
    }

    public int getSub_id() {
        return sub_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getFile_name() {
        return file_name;
    }

    public String getFile_status() {
        return file_status;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    public void setFile_status(String file_status) {
        this.file_status = file_status;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
