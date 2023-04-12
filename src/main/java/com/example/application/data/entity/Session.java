package com.example.application.data.entity;

import dev.hilla.Nonnull;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;

@Entity
public class Session extends AbstractEntity {

    @Email
    @Nonnull
    private String owner;
    @Nonnull
    private String description;
    private LocalDate date;

    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
