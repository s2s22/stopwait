package com.example.stopwait.Reservation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@Slf4j
public class ReservationController {

    public int DEFALUTDEPOSIT = 50000;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity createReservation(@PathVariable int restaurantId, @RequestBody @Validated ReservationSaveDto reservationSaveDto, BindingResult bindingResult) {


        //비즈니스 예외(필드 에외 제외)
        if (reservationSaveDto.getReserveNumber() * DEFALUTDEPOSIT == reservationSaveDto.getDeposit()) {
            bindingResult.reject("depositMin", new Object[]{reservationSaveDto.getDeposit() ,reservationSaveDto.getReserveNumber() * DEFALUTDEPOSIT}, null);
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors());
        }

        log.info(" [{}] [{}] [{}]", restaurantId, reservationSaveDto.getDeposit(), reservationSaveDto.getReservationDt());
        Reservation reserve = reservationService.reserve(restaurantId, reservationSaveDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("")
                .buildAndExpand(reserve)
                .toUri();

        return ResponseEntity.created(location)
                .body(reserve)
                ;
    }

    @PostMapping("/cancel/{reservationId}")
    public ResponseEntity cancelReservation(@PathVariable int reservationId) {
        Reservation cancelReservation = reservationService.cancel(reservationId);

        return ResponseEntity.ok()
                .body(cancelReservation);
    }

    @PostMapping("/modify/{reservationId}")
    public ResponseEntity<?> modifyReservation(@PathVariable int reservationId, @RequestBody ReservationModifyDto reservationModifyDto, BindingResult bindingResult) {

        if (LocalDate.now().isEqual(reservationModifyDto.getModifyDt())
                && LocalTime.now().minusHours(1).isAfter(reservationModifyDto.getReservationTime())) {
            bindingResult.reject("timeCheck", new Object[]{reservationModifyDto.getModifyDt(),reservationModifyDto.getReservationTime()} ,null);
            //예약 한시간 전에는 예약을 변경 하거나 취소 할 수 없습니다"
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors());
        }

        Reservation modifyReservation = reservationService.modify(reservationId, reservationModifyDto);

        return ResponseEntity.ok()
                .body(modifyReservation);
    }

    @GetMapping("/{restaurantId}")
    public Reservation getReservation(@PathVariable int restaurantId) {
        return reservationService.findOne(restaurantId);
    }

}
