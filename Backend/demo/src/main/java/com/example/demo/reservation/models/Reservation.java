package com.example.demo.reservation.models;

import java.time.LocalTime;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.transport.models.Seat;
import com.example.demo.transport.models.Transport;
import com.example.demo.trip.models.Trip;
import com.example.demo.users.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation extends AbstractEntity{



    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalTime reservationTimer;

    @ManyToOne
    @JoinColumn(name = "transport_id")
    private Transport transport;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;
    private boolean isPaid;
}
