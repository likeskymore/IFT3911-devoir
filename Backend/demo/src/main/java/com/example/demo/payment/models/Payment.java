package com.example.demo.payment.models;

import com.example.demo.entity.AbstractEntity;
import com.example.demo.reservation.models.Reservation;
import com.example.demo.users.models.Client;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Payment extends AbstractEntity {

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
}
