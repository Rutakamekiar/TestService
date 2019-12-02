package com.testproject.test.controller;

import com.testproject.test.dtomapping.dto.QuestionInputDto;
import com.testproject.test.dtomapping.dto.QuestionOutputDto;
import com.testproject.test.service.api.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsService questionsService;

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE, params = "questionArea")
    public Flux<QuestionOutputDto> findAllQuestionsByArea(@RequestParam String questionArea) {
        return questionsService.getThemedQuestions(questionArea);
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<QuestionOutputDto> findAllQuestions() {
        return questionsService.getAll();
    }

    //TODO: Implement
//    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//    public Flux<QuestionInputDto> findQuestionsForTest() {
//        return questionsService.
//    }

    @PostMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Mono<ResponseEntity<List<QuestionOutputDto>>> saveQuestionsToBase(@RequestBody List<QuestionInputDto> newQuestions) {
        return questionsService.saveAll(newQuestions)
                .collectList()
                .map(questions -> ResponseEntity.status(HttpStatus.CREATED).body(questions))
                .onErrorReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>()));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<QuestionOutputDto>> changeQuestionParameters(
            @PathVariable UUID id, @RequestBody QuestionInputDto questionInputDto) {
        return questionsService.changeQuestion(id, questionInputDto)
                .map(questionOutputDto -> ResponseEntity.status(HttpStatus.OK).body(questionOutputDto))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity> deleteQuestionFromBase(@PathVariable UUID id) {
        return questionsService.deleteQuestion(id)
                .map(question -> ResponseEntity.status(HttpStatus.NO_CONTENT))
                .cast(ResponseEntity.class);
    }

    @DeleteMapping
    public Mono<Void> deleteAllQuestions() {
        return questionsService.deleteAll();
    }
}
