package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Reservation;
import com.xingkong.lyn.repository.web.ReservationRepository;
import com.xingkong.lyn.service.IReservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@Service
public class ReservationService implements IReservation{
    @Resource
    private ReservationRepository reservationDao;

    @Override
    public Page<Reservation> getReservationList(Pageable pageable) {
        return reservationDao.findAll(pageable);
    }

    @Override
    public boolean addReservation(Reservation reservation) {
        if (null != reservation.getId()) {
            return false;
        }
        reservationDao.save(reservation);
        return true;
    }

    @Override
    public boolean updateReservation(Reservation reservation) {
        if (null == reservation.getId()) {
            return false;
        }
        reservationDao.save(reservation);
        return true;
    }

    @Override
    public boolean deleteReservation(List<Long> id) {
        reservationDao.deleteInBatch(reservationDao.findAll(id));
        return true;
    }

    @Override
    public Reservation getReservation(Long id) {
        return reservationDao.findOne(id);
    }
}
