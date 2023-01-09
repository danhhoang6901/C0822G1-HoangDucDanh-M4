package com.codegym.service;

import com.codegym.dto.IQuestionContentDto;
import com.codegym.model.QuestionContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQuestionService {
    Page<QuestionContent> findAll(Pageable pageable);

    void save(QuestionContent questionContent);

    void delete(int id);

    QuestionContent findById(int id);

    Page<QuestionContent> showList(String title, String questionType, Pageable pageable);

    List<IQuestionContentDto> findAll(Long id);
}
