package com.example.demo.transport.models;

import com.example.demo.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Seat extends AbstractEntity{
    private boolean isOccupied;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;
}
