package nju.blockbuster.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
public class Tags {

    @Id
    private String tag;

    private Integer usedTime;

    public String getTag() {
        return tag;
    }

    public Integer getUsedTime() {
        return usedTime;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setUsedTime(Integer usedTime) {
        this.usedTime = usedTime;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass() == this.getClass() && this.tag.equals(((Tags)obj).tag);
    }

    @Override
    public String toString() {
        return "Tags [tag = "
                + tag
                + ", usedTime = "
                + usedTime.toString()
                + "]";
    }

}
