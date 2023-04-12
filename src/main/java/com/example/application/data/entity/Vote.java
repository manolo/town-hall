package com.example.application.data.entity;

import java.time.LocalDate;

import dev.hilla.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;

@Entity
public class Vote extends AbstractEntity {

    @ManyToOne
    @Nonnull
    private Question question;

    @Email
    @Nonnull
    private String owner;

    private LocalDate date;
    @Nonnull
    private Integer type;

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public Question getQuestion() {
        return question;
    }
    public void setQuestion(Question question) {
        this.question = question;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

}
