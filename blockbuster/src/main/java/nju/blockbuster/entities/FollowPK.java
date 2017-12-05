package nju.blockbuster.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class FollowPK implements Serializable {
    private String followerEmail;

    private String followedEmail;

    public String getFollowerEmail() {
        return followerEmail;
    }

    public String getFollowedEmail() {
        return followedEmail;
    }

    public void setFollowerEmail(String followerEmail) {
        this.followerEmail = followerEmail;
    }

    public void setFollowedEmail(String followedEmail) {
        this.followedEmail = followedEmail;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((followerEmail == null) ? 0 : followerEmail.hashCode());
        result = prime * result
                + ((followedEmail == null) ? 0 : followedEmail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        return this == obj || obj != null
                && this.getClass() == obj.getClass()
                && this.followerEmail.equals(((FollowPK)obj).followerEmail)
                && this.followedEmail.equals(((FollowPK)obj).followedEmail);
    }
}
