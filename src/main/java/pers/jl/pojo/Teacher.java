package pers.jl.pojo;

public class Teacher {
    private Integer tId;
    private String tName;
    private String userName;
    private String pwd;

    public Teacher() {
    }

    public Teacher(Integer tId, String tName, String userName, String pwd) {
        this.tId = tId;
        this.tName = tName;
        this.userName = userName;
        this.pwd = pwd;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", tName='" + tName + '\'' +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
