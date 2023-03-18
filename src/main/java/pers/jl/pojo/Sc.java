package pers.jl.pojo;

public class Sc {
    private String scId;
    private String stuId;
    private String cId;
    private String score;

    public Sc() {
    }

    public Sc(String scId, String stuId, String cId, String score) {
        this.scId = scId;
        this.stuId = stuId;
        this.cId = cId;
        this.score = score;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Sc{" +
                "scId='" + scId + '\'' +
                ", stuId='" + stuId + '\'' +
                ", cId='" + cId + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
