package com.testproject.test.dtomapping.mappers;

import com.testproject.test.domain.AnswerOption;
import com.testproject.test.domain.Question;
import com.testproject.test.domain.QuestionType;
import com.testproject.test.dtomapping.dto.QuestionInputDto;
import com.testproject.test.dtomapping.dto.QuestionOutputDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    @Mapping(target = "questionType", ignore = true)
    Question inputDtoToQuestion(QuestionInputDto dto);

    @AfterMapping
    default void setQuestionType(@MappingTarget Question question, QuestionInputDto dto) {
        long numOfCorrectAnswers = dto.getAnswerOptions().stream()
                .filter(AnswerOption::isCorrect)
                .count();
        QuestionType questionType = numOfCorrectAnswers == 1 ? QuestionType.SINGLE_ANSWER : QuestionType.MULTI_ANSWER;
        question.setQuestionType(questionType);
    }

    QuestionOutputDto dtoFromQuestion(Question question);
}
