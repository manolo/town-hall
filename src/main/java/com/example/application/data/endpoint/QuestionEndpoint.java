package com.example.application.data.endpoint;

import com.example.application.data.entity.Question;
import com.example.application.data.service.QuestionService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import dev.hilla.exception.EndpointException;
import java.util.Optional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Endpoint
@AnonymousAllowed
public class QuestionEndpoint {

    private final QuestionService service;

    public QuestionEndpoint(QuestionService service) {
        this.service = service;
    }

    @Nonnull
    public Page<@Nonnull Question> list(Pageable page) {
        return service.list(page);
    }

    public Optional<Question> get(@Nonnull Long id) {
        return service.get(id);
    }

    @Nonnull
    public Question update(@Nonnull Question entity) {
        try {
            return service.update(entity);
        } catch (OptimisticLockingFailureException e) {
            throw new EndpointException("Somebody else has updated the data while you were making changes.");
        }
    }

    public void delete(@Nonnull Long id) {
        service.delete(id);
    }

    public int count() {
        return service.count();
    }

}
