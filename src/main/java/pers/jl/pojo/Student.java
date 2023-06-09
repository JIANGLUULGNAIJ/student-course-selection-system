package pers.jl.pojo;

public class Student {
    private Integer stuId;
    private String stuName;
    private String stuNo;
    private String stuPwd;

    public Student() {
    }

    public Student(Integer stuId, String stuName, String stuNo, String stuPwd) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.stuPwd = stuPwd;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuPwd() {
        return stuPwd;
    }

    public void setStuPwd(String stuPwd) {
        this.stuPwd = stuPwd;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", stuPwd='" + stuPwd + '\'' +
                '}';
    }
}
