package com.codegym.service;

import com.codegym.model.QuestionType;

import java.util.List;

public interface IQuestionTypeService {
    List<QuestionType> findAll();
}
