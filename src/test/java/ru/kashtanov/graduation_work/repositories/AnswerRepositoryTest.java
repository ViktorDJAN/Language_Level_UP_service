package ru.kashtanov.graduation_work.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import ru.kashtanov.graduation_work.models.Answer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;


    @Test
    void findByIdTest(){
       Long id = 1L;
       Answer answer = answerRepository.findAnswerById(1L).get();
       assertThat(answer.getId()).isEqualTo(id);
    }


}