package nju.blockbuster.models;

public class PhotoModel {

    private Integer pid;

    private String pic;

    private Integer sid;

    private Integer aid;

//    @ManyToOne
//    private Show show;

    public Integer getPid() {
        return pid;
    }

    public String getPic() {
        return pic;
    }

    public Integer getSid() {
        return sid;
    }

    public Integer getAid() {
        return aid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.pid.equals(((PhotoModel) obj).pid);
    }

    @Override
    public String toString() {
        return "Photo [pic = "
                + pic
                + ", pid = "
                + (pid == null ? "null" : pid)
                + ", sid = "
                + sid.toString()
                + ", aid = "
                + aid.toString()
                + "]";
    }
}
