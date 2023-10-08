package com.example.stopwait.Reservation;

import com.example.stopwait.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ReservationSaveDto {

    private Restaurant restaurant;

    @FutureOrPresent
    private LocalDate ReservationDt;

    @Future
    private LocalTime ReservationTime;

    private int deposit;

    @NotNull
    private ReservationStatus reservationStatus;

    @Min(1)
    private int ReserveNumber;
}
