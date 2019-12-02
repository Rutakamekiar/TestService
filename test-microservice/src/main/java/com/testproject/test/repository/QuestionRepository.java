package com.testproject.test.repository;

import com.testproject.test.domain.Question;
import com.testproject.test.domain.QuestionArea;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, UUID> {
    Flux<Question> findAllByQuestionArea(QuestionArea questionArea);
}
