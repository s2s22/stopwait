package com.example.stopwait.Reservation;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class ReservationRepositoty {

    private final EntityManager em;

    public ReservationRepositoty(EntityManager em) {
        this.em = em;
    }

    public void save(Reservation reservation) {
        em.persist(reservation);
    }

    public Reservation findById(int reservationid) {
        return em.find(Reservation.class, reservationid);
    }
}
