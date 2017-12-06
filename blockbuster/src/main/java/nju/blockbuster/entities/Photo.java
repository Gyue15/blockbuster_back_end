package nju.blockbuster.entities;

import javax.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String pic;

    private Integer sid;

    private String aid;

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

    public String getAid() {
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

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.pid.equals(((Photo)obj).pid);
    }

    @Override
    public String toString() {
        return "Photo [pic = "
                + pic.toString()
                + ", pid = "
                + pid
                + ", sid = "
                + sid.toString()
                + ", aid = "
                + aid.toString()
                + "]";
    }
}
