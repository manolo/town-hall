package com.example.application.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Session extends AbstractEntity {

    @Email
    @Nonnull
    private String owner;
    @Nonnull
    private String description;
    private LocalDate date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderColumn
    @JoinColumn
    private Set<Question> questions;

    public Set<Question> getQuestions() {
        return questions;
    }
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
