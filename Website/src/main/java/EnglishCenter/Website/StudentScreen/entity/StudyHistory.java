package EnglishCenter.Website.StudentScreen.entity;

import lombok.Data;

//@Data
public class StudyHistory {
//    String courseID();
//    String nameCourse();
//    String cost();
//    String nameTeacher();
//    String numberOfStudent();

    private int stt;
    private String courseID;
    private String nameCourse;
    private String cost;
    private String nameTeacher;
    private String numberOfStudent;

    public StudyHistory(String courseID, String nameCourse, String cost, String nameTeacher, String numberOfStudent) {
        this.courseID = courseID;
        this.nameCourse = nameCourse;
        this.cost = cost;
        this.nameTeacher = nameTeacher;
        this.numberOfStudent = numberOfStudent;
    }

    public StudyHistory() {
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getNameTeacher() {
        return nameTeacher;
    }

    public void setNameTeacher(String nameTeacher) {
        this.nameTeacher = nameTeacher;
    }

    public String getNumberOfStudent() {
        return numberOfStudent;
    }

    public void setNumberOfStudent(String numberOfStudent) {
        this.numberOfStudent = numberOfStudent;
    }

    @Override
    public String toString() {
        return "StudyHistory{" +
                "courseID='" + courseID + '\'' +
                ", nameCourse='" + nameCourse + '\'' +
                ", cost='" + cost + '\'' +
                ", nameTeacher='" + nameTeacher + '\'' +
                ", numberOfStudent='" + numberOfStudent + '\'' +
                '}';
    }
}
