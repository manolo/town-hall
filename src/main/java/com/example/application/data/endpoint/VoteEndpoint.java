package com.example.application.data.endpoint;

import com.example.application.data.entity.Vote;
import com.example.application.data.service.VoteService;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import dev.hilla.Endpoint;
import dev.hilla.Nonnull;
import dev.hilla.exception.EndpointException;

import java.util.List;
import java.util.Optional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Endpoint
@AnonymousAllowed
public class VoteEndpoint {

    private final VoteService service;

    public VoteEndpoint(VoteService service) {
        this.service = service;
    }

    @Nonnull
    public List<@Nonnull Vote> listAll() {
        return service.listAll();
    }

    @Nonnull
    public Page<@Nonnull Vote> list(Pageable page) {
        return service.list(page);
    }

    public Optional<Vote> get(@Nonnull Long id) {
        return service.get(id);
    }

    @Nonnull
    public Vote update(@Nonnull Vote entity) {
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
