package com.example.application.data.service;

import com.example.application.data.entity.Session;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public Optional<Session> get(Long id) {
        return repository.findById(id);
    }

    public Session update(Session entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Session> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Session> list(Pageable pageable, Specification<Session> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
