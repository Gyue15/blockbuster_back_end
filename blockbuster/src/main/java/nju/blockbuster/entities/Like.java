package nju.blockbuster.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "`like`")
public class Like {
    @EmbeddedId
    private LikePK likePK;

    public LikePK getLikePK() {
        return likePK;
    }

    public void setLikePK(LikePK likePK) {
        this.likePK = likePK;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass() != obj.getClass() && this.likePK.equals(((Like)obj).likePK);
    }

    @Override
    public String toString() {
        return "TagRelation [ email = "
                + likePK.getEmail()
                + ", sid = "
                + likePK.getSid()
                + "]";
    }
}
