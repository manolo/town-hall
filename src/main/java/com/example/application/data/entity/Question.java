package com.example.application.data.entity;

import java.util.Set;

import dev.hilla.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.Email;

@Entity
public class Question extends AbstractEntity {

    @Email
    @Nonnull
    private String owner;
    @Nonnull
    private String question;
    @Nonnull
    private Integer value;

    @ManyToOne
    @Nonnull
    private Session session;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @OrderColumn
    @JoinColumn
    private Set<Vote> votes;

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
    public Session getSession() {
        return session;
    }
    public void setSession(Session session) {
        this.session = session;
    }

}
