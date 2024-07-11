package com.math.mathcha.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private int payment_id;

    @Column(name = "payment_date")
    private String payment_date;

    @Column(name = "payment_method")
    private String payment_method;



    @Column(name = "orderId")
    private String orderId;
//    @Enumerated(EnumType.STRING)
//    TransactionEnums transaction;


    @Column(name = "total_money")
    private double total_money;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

}