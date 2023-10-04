package com.example.stopwait.Reservation;

import com.example.stopwait.restaurant.Restaurant;
import com.example.stopwait.restaurant.RestaurantJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ReservationService {

    private final ReservationRepositoty reservationRepositoty;
    private final RestaurantJpaRepository restaurantJpaRepository;

    public ReservationService(ReservationRepositoty reservationRepositoty, RestaurantJpaRepository restaurantJpaRepository) {
        this.reservationRepositoty = reservationRepositoty;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    @Autowired


    public void reserve(Reservation reservation) {
        Restaurant restaurant = restaurantJpaRepository.findById(reservation.getRestaurant().getId()).get();
        reservation.setRestaurant(restaurant);
        reservation.setReservationStatus(ReservationStatus.RESERVATION);
        reservationRepositoty.save(reservation);
    }

    public Reservation findOne(int restauratnId) {
        return reservationRepositoty.findById(restauratnId);
    }

    public void cancel(int reservationId) {

        Reservation CancelReserve = reservationRepositoty.findById(reservationId);
        CancelReserve.changeReservationCheck(); //예약 validation
        CancelReserve.setReservationStatus(ReservationStatus.CANCEL);
    }

    public void change(@RequestBody updateReserveFrom updateReserveFrom, int reservationId) {
        Reservation modifyReserve = reservationRepositoty.findById(reservationId);
        modifyReserve.changeReservationCheck(); //예약 validation

        modifyReserve.setReservationDt(updateReserveFrom.getReservationDt());
        modifyReserve.setReservationTime(updateReserveFrom.getReservationTime());
    }
}
