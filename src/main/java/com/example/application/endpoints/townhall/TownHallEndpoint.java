package com.example.application.endpoints.townhall;

import java.util.List;

import com.example.application.data.Question;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class TownHallEndpoint {
    public List<Question> getQuestions() {
        return List.of(
          new Question("Should we launch the lunch train?"),
          new Question("When is the Vaadin 25 released planned?")
        );
    }

    public void submitQuestion(Question question) {
        // no-op
    }

    public void vote(Question question, boolean up) {
        // no-op
        System.out.println("Voted " + (up ? "up" : "down") + " for " + question.getText() + "");
    }
}
