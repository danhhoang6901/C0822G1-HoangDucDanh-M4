package com.codegym.service.impl;

import com.codegym.model.QuestionType;
import com.codegym.repository.IQuestionTypeRepository;
import com.codegym.service.IQuestionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeService implements IQuestionTypeService {
    @Autowired
    private IQuestionTypeRepository questionTypeRepository;

    @Override
    public List<QuestionType> findAll() {
        return questionTypeRepository.findAll();
    }
}
