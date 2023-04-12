package com.example.application.endpoints.townhall;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import com.example.application.data.Question;
import com.example.application.data.entity.Vote;
import com.example.application.data.service.QuestionService;
import com.example.application.data.service.VoteService;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import dev.hilla.Endpoint;

@Endpoint
@AnonymousAllowed
public class TownHallEndpoint {

    private final QuestionService questionService;
    private final VoteService voteService;



    public TownHallEndpoint(QuestionService questionService, VoteService voteService) {
        this.questionService = questionService;
        this.voteService = voteService;
    }

    private DefaultOidcUser getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof DefaultOidcUser) {
            return (DefaultOidcUser) authentication.getPrincipal();
        }
        return null;
    }

    public List<Question> getQuestions() {
        return questionService.list(Pageable.unpaged()).map(q -> {
            var question = new Question(q.getQuestion());
            var votes = voteService.list(Pageable.unpaged());
            question.setScore((int) votes.filter(vote -> {
                return vote.getQuestion().getId().equals(q.getId());
            }).stream().count());
            question.setId(q.getId());
            question.setPriority(q.getRank() != null ? q.getRank() : 0);
            
            if (getLoggedInUser() != null) {
                question.setUserVoted(votes.filter(vote -> {
                    return vote.getQuestion().getId().equals(q.getId()) && vote.getOwner().equals(getLoggedInUser().getEmail());
                }).stream().findFirst().isPresent());
            }
            
            return question;
        }).stream().collect(Collectors.toList());
    }

    public void submitQuestion(Question question) {
        com.example.application.data.entity.Question newQuestion = new com.example.application.data.entity.Question();
        newQuestion.setQuestion(question.getText());
        newQuestion.setOwner(getLoggedInUser().getEmail());
        questionService.update(newQuestion);
    }

    public void vote(Question question, boolean up) {
        var savedQuestion = questionService.get(question.getId());
        if (savedQuestion.isPresent()) {
            var q = savedQuestion.get();

            if (up) {
                Vote newVote = new Vote();
                newVote.setQuestion(q);
                newVote.setDate(LocalDate.now());
                newVote.setOwner(getLoggedInUser().getEmail());
                voteService.update(newVote);
            } else {
                var votes = voteService.list(Pageable.unpaged());
                var vote = votes.filter(v -> {
                    return v.getQuestion().getId().equals(q.getId()) && v.getOwner().equals(getLoggedInUser().getEmail());
                }).stream().findFirst();
                if (vote.isPresent()) {
                    voteService.delete(vote.get().getId());
                }
            }
            questionService.update(q);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }

    public void setPriority(Question question, int priority) {
        var savedQuestion = questionService.get(question.getId());
        if (savedQuestion.isPresent()) {
            var q = savedQuestion.get();
            q.setRank(priority);
            questionService.update(q);
        } else {
            throw new IllegalArgumentException("Question not found");
        }
    }
}
