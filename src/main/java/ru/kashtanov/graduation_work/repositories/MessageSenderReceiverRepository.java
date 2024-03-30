package ru.kashtanov.graduation_work.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;

import java.util.List;

@Repository
public interface MessageSenderReceiverRepository extends JpaRepository<MessageSenderReceiver, Long> {
    @Query("SELECT sr FROM MessageSenderReceiver sr WHERE sr.sender_id = ?1 or sr.receiver_id=?1 ")
    List<MessageSenderReceiver> findAllMessagesBetweenSenderAndReceiverById(Integer senderId);


}
