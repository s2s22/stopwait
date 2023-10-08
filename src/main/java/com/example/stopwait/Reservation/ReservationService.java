package com.example.stopwait.Reservation;

import com.example.stopwait.restaurant.Restaurant;
import com.example.stopwait.restaurant.RestaurantJpaRepository;
import com.example.stopwait.restaurant.RestaurantUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;

@Service
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RestaurantJpaRepository restaurantJpaRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, RestaurantJpaRepository restaurantJpaRepository) {
        this.reservationRepository = reservationRepository;
        this.restaurantJpaRepository = restaurantJpaRepository;
    }

    public Reservation reserve(int restaurantId, ReservationSaveDto reservationSaveDto) {
        Restaurant restaurant = restaurantJpaRepository.findById(restaurantId).get();
        reservationSaveDto.setRestaurant(restaurant);
        reservationSaveDto.setReservationStatus(ReservationStatus.RESERVATION);
        Reservation saveReservation = reservationRepository.save(reservationSaveDto);

        return saveReservation;
    }

    public Reservation findOne(int reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Transactional
    public Reservation cancel(int reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.cancel(ReservationStatus.CANCEL, LocalDate.now());
        return reservation;
    }

    @Transactional
    public Reservation modify(int reservationId, ReservationModifyDto reservationModifyDto) {
        Reservation reservation = reservationRepository.findById(reservationId);
        reservation.modify(reservationModifyDto.getReservationDt()
                            , reservationModifyDto.getReservationTime()
                            , reservationModifyDto.getDeposit()
                            ,LocalDate.now()
                            ,reservationModifyDto.getReserveNumber());
        return reservation;

    }

}
