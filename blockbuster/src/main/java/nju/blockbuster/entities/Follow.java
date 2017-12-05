package nju.blockbuster.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "follow")
public class Follow {
    @EmbeddedId
    private FollowPK followPK;

    public FollowPK getFollowPK() {
        return followPK;
    }

    public void setFollowPK(FollowPK followPK) {
        this.followPK = followPK;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && this.getClass() != obj.getClass() && this.followPK.equals(((Follow)obj).followPK);
    }

    @Override
    public String toString() {
        return "TagRelation [ followed = "
                + followPK.getFollowedEmail()
                + ", follower = "
                + followPK.getFollowerEmail()
                + "]";
    }
}
