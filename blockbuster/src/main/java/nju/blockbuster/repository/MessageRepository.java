package nju.blockbuster.repository;

import nju.blockbuster.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findByOwnerOrderByDateDesc(String owner);

    List<Message> findByOwnerAndFlag(String owner, boolean flag);
}
