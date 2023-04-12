package com.example.application.data.service;

import com.example.application.data.entity.Vote;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Optional<Vote> get(Long id) {
        return repository.findById(id);
    }

    public Vote update(Vote entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Vote> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Vote> list(Pageable pageable, Specification<Vote> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
