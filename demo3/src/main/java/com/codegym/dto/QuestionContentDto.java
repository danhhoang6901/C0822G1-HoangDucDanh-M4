package com.codegym.dto;

import com.codegym.model.QuestionType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class QuestionContentDto {
    private int id;
    @NotEmpty(message = "Không được để trống")
    @Size(min = 5, max = 100, message = "Tiêu đề phải từ 5 đến 100 ký tự")
    private String title;
    @NotEmpty(message = "Không được để trống")
    @Size(min = 10, max = 500, message = "Nội dung phải từ 10 đến 500 ký tự")
    private String content;
    @NotEmpty(message = "Không được để trống")
    private String answer;
    @NotEmpty(message = "Không được để trống")
    private String dateCreate;
    @NotEmpty(message = "Không được để trống")
    private String status;
    private QuestionType questionType;

    public QuestionContentDto() {
    }

    public QuestionContentDto(int id, String title, String content, String answer, String dateCreate, String status, QuestionType questionType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.dateCreate = dateCreate;
        this.status = status;
        this.questionType = questionType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
