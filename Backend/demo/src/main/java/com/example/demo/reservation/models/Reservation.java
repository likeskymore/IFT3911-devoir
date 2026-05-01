package com.example.demo.reservation.models;

import java.time.LocalTime;

import com.example.demo.account.models.Client;
import com.example.demo.transport.models.Seat;
import com.example.demo.transport.models.Transport;
import com.example.demo.trip.models.Trip;

import jakarta.persistence.*;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

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

    public Reservation() {}

    public Reservation(Client client, LocalTime reservationTimer, Transport transport, Seat seat, Trip trip, boolean isPaid) {
        this.client = client;
        this.reservationTimer = reservationTimer;
        this.transport = transport;
        this.seat = seat;
        this.trip = trip;
        this.isPaid = isPaid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public LocalTime getReservationTimer() {
        return reservationTimer;
    }

    public void setReservationTimer(LocalTime reservationTimer) {
        this.reservationTimer = reservationTimer;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

}
