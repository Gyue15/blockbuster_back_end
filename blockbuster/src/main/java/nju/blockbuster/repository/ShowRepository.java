package nju.blockbuster.repository;

import nju.blockbuster.entities.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer>{
    Show findShowBySid(Integer sid);

    Page<Show> findByLikeNumGreaterThanEqual(Integer base, Pageable pageable);

    List<Show> findByEmail(String email);

    List<Show> findByTitleLikeOrDescriptionLike(String title, String description);
}
