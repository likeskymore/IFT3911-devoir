package com.example.demo.company.models;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.transport.models.Transport;
import com.example.demo.trip.models.Trip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractEntity {

    private String name;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Trip> trips;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Transport> transports;
}
