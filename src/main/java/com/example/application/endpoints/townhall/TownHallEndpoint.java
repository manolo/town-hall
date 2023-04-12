package com.example.application.endpoints.townhall;

import java.util.List;

import com.example.application.data.Question;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class TownHallEndpoint {
    public List<Question> getQuestions() {
        var q1 = new Question("Should we launch the lunch train?");
        q1.setScore(5);
        q1.setUserVoted(true);
        var q2 = new Question("When is the Vaadin 25 released planned?");
        q2.setScore(2);
        q2.setPriority(1);
        return List.of(q1, q2);
    }

    public void submitQuestion(Question question) {
        // no-op
    }

    public void vote(Question question, boolean up) {
        // no-op
        System.out.println("Voted " + (up ? "up" : "down") + " for " + question.getText() + "");
    }
}
