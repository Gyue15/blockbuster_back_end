package nju.blockbuster.repository;

import nju.blockbuster.entities.Like;
import nju.blockbuster.entities.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikePK> {
    Like findLikesByLikePK(LikePK likePK);
}
