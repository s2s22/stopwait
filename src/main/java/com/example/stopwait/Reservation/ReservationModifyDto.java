package com.example.stopwait.Reservation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ReservationModifyDto {

    @FutureOrPresent
    private LocalDate ReservationDt;

    @Future
    private LocalTime ReservationTime;

    private int deposit;

    @NotNull
    private ReservationStatus reservationStatus;

    private LocalDate modifyDt;

    @Min(1)
    private int ReserveNumber;
}
