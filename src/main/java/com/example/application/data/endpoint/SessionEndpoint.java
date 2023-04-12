package com.example.application.data.endpoint;

import com.example.application.data.entity.Session;
import com.example.application.data.service.SessionService;
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
public class SessionEndpoint {

    private final SessionService service;

    public SessionEndpoint(SessionService service) {
        this.service = service;
    }

    @Nonnull
    public Page<@Nonnull Session> list(Pageable page) {
        return service.list(page);
    }

    public Optional<Session> get(@Nonnull Long id) {
        return service.get(id);
    }

    @Nonnull
    public Session update(@Nonnull Session entity) {
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
