package nju.blockbuster.repository;

import nju.blockbuster.entities.Follow;
import nju.blockbuster.entities.FollowPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowPK> {
    List<Follow> findByFollowPK_FollowerEmail(String followerEmail);
}
