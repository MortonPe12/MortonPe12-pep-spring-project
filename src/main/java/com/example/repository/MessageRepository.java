package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer>{
    Boolean existsByMessageId(Integer messageId);

    Message findByMessageId(Integer messageId);

    Message deleteByMessageId(Integer messageId);

    List<Message> findByPostedBy(Integer accountId);
}
