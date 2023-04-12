package com.example.application.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.util.UUID;

@Entity
public class Question extends AbstractEntity {

    @Email
    @Nonnull
    private String owner;
    @Nonnull
    private String question;
    @Nonnull
    private Integer value;
    @Nonnull
    private UUID session;

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public UUID getSession() {
        return session;
    }
    public void setSession(UUID session) {
        this.session = session;
    }

}
