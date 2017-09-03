package com.xingkong.lyn.controller;

import com.xingkong.lyn.comment.AjaxResults;
import com.xingkong.lyn.model.web.Reservation;
import com.xingkong.lyn.service.IReservation;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lyn on 2017/8/30.
 */
@RestController
public class ReservationController {
    @Resource
    private IReservation reservationService;

    @RequestMapping(value = "/web/manage/reservation/list", method = RequestMethod.GET)
//    @RequiresPermissions("reservation:view")
    public Object webManageReservationList(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Reservation> reservations = reservationService.getReservationList(pageable);
        for(Reservation reservation : reservations) {
            reservation.getCourse().setReservations(null);
            reservation.getCourse().setTeachers(null);
        }
        ajaxResults.put("reservations", reservations);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/reservation/add", method = RequestMethod.POST)
//    @RequiresPermissions("reservation:add")
    public Object webManageReservationAdd(@RequestBody Reservation reservation) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != reservation.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            reservationService.addReservation(reservation);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/reservation/update", method = RequestMethod.PUT)
//    @RequiresPermissions("reservation:update")
    public Object webManageReservationUpdate(@RequestBody Reservation reservation) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == reservation.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            reservationService.updateReservation(reservation);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/reservation/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("reservation:delete")
    public Object webManageReservationDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        reservationService.deleteReservation(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/reservation/detail", method = RequestMethod.GET)
//    @RequiresPermissions("reservation:detail")
    public Object webManageReservationDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("reservation", reservationService.getReservation(id));
        return ajaxResults;
    }
}
