package com.example.demo.transport.models;

import java.util.List;

import com.example.demo.company.models.Company;
import com.example.demo.entity.AbstractEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
public abstract class Transport extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "transport", cascade = CascadeType.ALL)
    private List<Seat> seats;

}
