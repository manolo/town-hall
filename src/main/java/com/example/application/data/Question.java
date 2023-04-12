package com.example.application.data;

import java.time.LocalDateTime;

public class Question {
    private long id;
    private int score;
    private String text;
    private LocalDateTime created;
    private boolean userVoted;

    public Question(String text, int score, LocalDateTime created) {
        this.text = text;
        this.score = score;
        this.created = created;
    }

    public Question(String text) {
        this(text, 0, LocalDateTime.now());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isUserVoted() {
        return userVoted;
    }

    public void setUserVoted(boolean userVoted) {
        this.userVoted = userVoted;
    }
}
