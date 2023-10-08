package com.example.stopwait.Reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional(readOnly = true)
public class ReservationRepository {

    private final EntityManager em;

    @Autowired
    public ReservationRepository(EntityManager em) {
        this.em = em;
    }

    public Reservation save(ReservationSaveDto reservationSaveDto) {

        Reservation reservation = Reservation.builder()
                .reservationDt(reservationSaveDto.getReservationDt())
                .reservationTime(reservationSaveDto.getReservationTime())
                .deposit(reservationSaveDto.getDeposit())
                .reserveNumber(reservationSaveDto.getReserveNumber()).build();

        em.persist(reservation);
        return reservation;
    }

    public Reservation findById(int reservationId) {
        return em.find(Reservation.class, reservationId);
    }
}
