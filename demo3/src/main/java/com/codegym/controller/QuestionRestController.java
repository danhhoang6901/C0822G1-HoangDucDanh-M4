package com.codegym.controller;

import com.codegym.dto.IQuestionContentDto;
import com.codegym.model.QuestionContent;
import com.codegym.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("questions")
public class QuestionRestController {
    @Autowired
    private IQuestionService questionService;

    @GetMapping("{id}")
    public ResponseEntity<List<IQuestionContentDto>> showList(@PathVariable Long id) {
        List<IQuestionContentDto> questionContents = questionService.findAll(id);
        return new ResponseEntity<>(questionContents, HttpStatus.OK);
    }
}
