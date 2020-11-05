package com.pyropy.work24.model;

public class CoursesModel {

    public String courseTitle;
    public String courseDesc;
    public String thumbUrl;

    public CoursesModel(String courseTitle, String courseDesc, String thumbUrl) {
        this.courseTitle = courseTitle;
        this.courseDesc = courseDesc;
        this.thumbUrl = thumbUrl;
    }

    public CoursesModel() {
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
