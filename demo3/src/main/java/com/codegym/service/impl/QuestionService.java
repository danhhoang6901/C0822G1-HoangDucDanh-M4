package com.codegym.service.impl;

import com.codegym.dto.IQuestionContentDto;
import com.codegym.model.QuestionContent;
import com.codegym.repository.IQuestionRepository;
import com.codegym.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    private IQuestionRepository questionRepository;

    @Override
    public Page<QuestionContent> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public void save(QuestionContent questionContent) {
        questionRepository.save(questionContent);
    }

    @Override
    public void delete(int id) {
        QuestionContent questionContent = this.findById(id);
        questionContent.setFlagDelete(true);
        this.questionRepository.save(questionContent);

    }

    @Override
    public QuestionContent findById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public Page<QuestionContent> showList(String title, String questionType, Pageable pageable) {
        return questionRepository.showList(title, questionType, pageable);
    }

    @Override
    public List<IQuestionContentDto> findAll(Long id) {
        return questionRepository.findAll(id);
    }
}
