package com.example.demo.payment.models;

import com.example.demo.account.models.Client;
import com.example.demo.reservation.models.Reservation;

import jakarta.persistence.*;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
    
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String cardNumber;
    private String name;
    private String email;
    private float totalPrice;

    public Payment() {}

    public Payment(Reservation reservation, Client client, String cardNumber, String name, String email, float totalPrice) {
        this.reservation = reservation;
        this.client = client;
        this.cardNumber = cardNumber;
        this.name = name;
        this.email = email;
        this.totalPrice = totalPrice;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }


}
