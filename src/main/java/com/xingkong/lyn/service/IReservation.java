package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
public interface IReservation {
    Page<Reservation> getReservationList(Pageable pageable);
    boolean addReservation(Reservation reservation);
    boolean updateReservation(Reservation reservation);
    boolean deleteReservation(List<Long> id);
    Reservation getReservation(Long id);
}
