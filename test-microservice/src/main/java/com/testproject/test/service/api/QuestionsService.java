package com.testproject.test.service.api;

import com.testproject.test.dtomapping.dto.QuestionInputDto;
import com.testproject.test.dtomapping.dto.QuestionOutputDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface QuestionsService {
    Flux<QuestionOutputDto> getAll();
    Flux<QuestionOutputDto> getThemedQuestions(String questionArea);
    Flux<QuestionOutputDto> saveAll(List<QuestionInputDto> newQuestions);
    Mono<QuestionOutputDto> changeQuestion(String questionId, QuestionInputDto changedQuestion);
    Mono<Void> deleteQuestion(String id);
}
