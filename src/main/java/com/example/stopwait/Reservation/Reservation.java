package com.example.stopwait.Reservation;

import com.example.stopwait.restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private LocalDate reservationDt;

    @Column(nullable = false)
    private LocalTime reservationTime;

    private int deposit;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDate modifyDt;

    private int reserveNumber;

    @Builder
    public Reservation(Restaurant restaurant, LocalDate reservationDt, LocalTime reservationTime, int deposit, LocalDate modifyDt, int reserveNumber) {
        this.restaurant = restaurant;
        this.reservationDt = reservationDt;
        this.reservationTime = reservationTime;
        this.deposit = deposit;
        this.modifyDt = modifyDt;
        this.reserveNumber = reserveNumber;
    }

    public void cancel(ReservationStatus reservationStatus, LocalDate modifyDt) {
        this.reservationStatus  = reservationStatus;
        this.modifyDt = modifyDt;
    }

    public void modify(LocalDate reservationDt, LocalTime reservationTime, int deposit, LocalDate modifyDt, int reserveNumber) {
        this.reservationDt = reservationDt == null ? this.reservationDt : reservationDt;
        this.reservationTime = reservationTime == null ? this.reservationTime : reservationTime;
        this.deposit = deposit;
        this.modifyDt = modifyDt;
        this.reserveNumber = reserveNumber;
    }

}
