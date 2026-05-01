package com.example.demo.account.models;

import java.util.List;

import com.example.demo.payment.models.Payment;
import com.example.demo.reservation.models.Reservation;
import jakarta.persistence.*;


@Entity
public class Client extends User {

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Reservation> reservations; 

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Payment> payments;
    
    private String passportNumber;

    public Client() {}

    public Client(String username, String firstName, String lastName, String email, String password, String passportNumber) {
        super.setUsername(username);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setEmail(email);
        super.setPassword(password);
        this.passportNumber = passportNumber;
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

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
    
}
