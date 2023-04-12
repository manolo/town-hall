package com.example.application.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Vote extends AbstractEntity {

    @Email
    @Nonnull
    private String owner;
    @Nonnull
    private UUID question;
    private LocalDate date;
    @Nonnull
    private Integer type;

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public UUID getQuestion() {
        return question;
    }
    public void setQuestion(UUID question) {
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
