package pers.jl.pojo;

public class Course {
    private String cId;
    private String cName;
    private String tId;

    public Course() {
    }

    public Course(String cId, String cName, String tId) {
        this.cId = cId;
        this.cName = cName;
        this.tId = tId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", tId='" + tId + '\'' +
                '}';
    }
}
