package com.example.application.endpoints.townhall;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;

import com.example.application.data.Question;
import com.example.application.data.service.QuestionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class TownHallEndpoint {

    private final QuestionService service;

    public TownHallEndpoint(QuestionService service) {
        this.service = service;
    }

    public List<Question> getQuestions() {
        return service.list(Pageable.unpaged()).map(q -> {
            var question = new Question(q.getQuestion());
            question.setScore(q.getRank() != null ? q.getRank() : 0);
            question.setId(q.getId());
            return question;
        }).stream().collect(Collectors.toList());
    }

    public void submitQuestion(Question question) {
        com.example.application.data.entity.Question newQuestion = new com.example.application.data.entity.Question();
        newQuestion.setQuestion(question.getText());
        service.update(newQuestion);
    }

    public void vote(Question question, boolean up) {
        var savedQuestion = service.get(question.getId());
        if (savedQuestion.isPresent()) {
            var q = savedQuestion.get();
            q.setRank(q.getRank() + (up ? 1 : -1));
            service.update(q);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    public void setPriority(Question question, int priority) {
        // no-op
        System.out.println("Set priority " + priority + " for " + question.getText() + "");
    }
}
