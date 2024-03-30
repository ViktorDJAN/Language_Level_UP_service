package ru.kashtanov.graduation_work.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.kashtanov.graduation_work.models.Message;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MessageRepositoryTest {
    @Autowired
    MessageRepository messageRepository;

    @Test
    void findById(){
        Long id = 1L;
        Message message = messageRepository.findById(1L).get();
        assertThat(message.getMessage_id()).isEqualTo(id);
    }

}