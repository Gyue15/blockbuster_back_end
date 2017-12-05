package nju.blockbuster.entities;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

@Embeddable
public class TagRelationPK implements Serializable {

    private String tag;

    private Integer sid;

    public String getTag() {
        return tag;
    }

    public Integer getSid() {
        return sid;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tag == null) ? 0 : tag.hashCode());
        result = prime * result
                + ((sid == null) ? 0 : sid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        return this == obj || obj != null
                && this.getClass() == obj.getClass()
                && this.tag.equals(((TagRelationPK)obj).tag)
                && this.sid.equals(((TagRelationPK)obj).sid);
    }

}
