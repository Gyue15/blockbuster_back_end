package nju.blockbuster.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sid;

    private String description;

    @Column(name = "`date`")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    private Integer likeNum;

    private Integer aid;

    private String email;

//    @OneToMany
//    private List<Photo> photos;

    public Integer getSid() {
        return sid;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public Integer getAid() {
        return this.aid;
    }

    public String getEmail() {
        return email;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.sid.equals(((Show) obj).sid);
    }

    @Override
    public String toString() {
        return "Show [sid = "
                + sid.toString()
                + ", description = "
                + description
                + ", date = "
                + date.toString()
                + ", likeNum = "
                + likeNum
                + ", email = "
                + email
                + "]";
    }
}