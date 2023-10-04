package com.example.stopwait.Reservation;

import com.example.stopwait.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter @Setter
public class Reservation {

    @Id @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Column(nullable = false)
    private LocalDate ReservationDt;

    @Column(nullable = false)
    private LocalTime ReservationTime;

    private int deposit;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private LocalDate modifyDt;

    public void changeReservationCheck() {
        if (LocalDate.now().isEqual(this.ReservationDt)
                && LocalTime.now().minusHours(1).isAfter(this.ReservationTime)) {
            throw new IllegalStateException("예약 한시간 전에는 예약을 변경 하거나 취소 할 수 없습니다");
        }

    }
}
