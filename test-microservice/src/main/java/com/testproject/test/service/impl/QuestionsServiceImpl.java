package com.testproject.test.service.impl;

import com.testproject.test.domain.Question;
import com.testproject.test.domain.QuestionArea;
import com.testproject.test.dtomapping.dto.QuestionInputDto;
import com.testproject.test.dtomapping.dto.QuestionOutputDto;
import com.testproject.test.dtomapping.mappers.QuestionMapper;
import com.testproject.test.repository.QuestionRepository;
import com.testproject.test.service.api.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionsServiceImpl implements QuestionsService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public Flux<QuestionOutputDto> getAll() {
        return questionRepository.findAll().map(questionMapper::dtoFromQuestion);
    }

    @Override
    public Flux<QuestionOutputDto> getThemedQuestions(String questionArea) {
        return questionRepository.findAllByQuestionArea(QuestionArea.getAreaFromValue(questionArea))
                .map(questionMapper::dtoFromQuestion);
    }

    @Override
    public Flux<QuestionOutputDto> saveAll(List<QuestionInputDto> newQuestions) {
        return Flux.fromIterable(newQuestions)
                .map(questionMapper::inputDtoToQuestion)
                .flatMap(questionRepository::save)
                .map(questionMapper::dtoFromQuestion)
                .onErrorMap(ex -> new IllegalArgumentException("Couldn't save entities"));
    }

    @Override
    public Mono<QuestionOutputDto> changeQuestion(UUID questionId, QuestionInputDto changedQuestion) {
        return questionRepository.findById(questionId)
                .flatMap(question -> {
                    Question newQuestion = questionMapper.inputDtoToQuestion(changedQuestion);
                    newQuestion.setId(question.getId());
                    return questionRepository.save(newQuestion);
                })
                .map(questionMapper::dtoFromQuestion);
    }

    @Override
    public Mono<Void> deleteQuestion(UUID id) {
        return questionRepository.deleteById(id);
    }

    @Override
    public Mono<Void> deleteAll() {
        return questionRepository.deleteAll();
    }
}
