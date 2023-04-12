package com.example.application.data;

import java.time.LocalDateTime;

public class Question {
    private int score;
    private String text;
    private LocalDateTime created;

    public Question(String text, int score, LocalDateTime created) {
        this.text = text;
        this.score = score;
        this.created = created;
    }

    public Question(String text) {
        this(text, 0, LocalDateTime.now());
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getScore() {
        return score;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
