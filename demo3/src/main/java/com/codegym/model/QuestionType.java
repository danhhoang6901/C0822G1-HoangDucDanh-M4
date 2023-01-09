package com.codegym.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class QuestionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "questionType")
    private Set<QuestionContent> questionContents;

    public QuestionType() {
    }

    public Set<QuestionContent> getQuestionContents() {
        return questionContents;
    }

    public void setQuestionContents(Set<QuestionContent> questionContents) {
        this.questionContents = questionContents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
