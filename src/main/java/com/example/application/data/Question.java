package com.example.application.data;

public class Question {
    private int score;
    private String text;

    public Question(String text, int score) {
      this.text = text;
      this.score = score;
    }

    public Question(String text) {
      this(text, 0);
    }

    public int getScore() {
        return score;
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
