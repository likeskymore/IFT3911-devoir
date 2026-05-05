package com.example.demo.account.models;

import java.util.List;

import com.example.demo.account.schema.CreateClientRequest;
import com.example.demo.payment.models.Payment;
import com.example.demo.reservation.models.Reservation;
import jakarta.persistence.*;


@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations; 

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Payment> payments;
    

    public Client() {}

    public Client(CreateClientRequest data) {
        super(data);
    }

    public Client(String username, String firstName, String lastName, String email, String password) {
        super.setUsername(username);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setEmail(email);
        super.setPassword(password);
    }


    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    
}
