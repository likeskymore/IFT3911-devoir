package com.example.demo.trip.models;

import com.example.demo.company.models.Company;
import com.example.demo.entity.AbstractEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Duration;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Trip extends AbstractEntity{

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private float price;
    private Duration duration;
}
