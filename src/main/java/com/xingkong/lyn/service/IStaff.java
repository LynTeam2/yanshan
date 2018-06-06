package com.xingkong.lyn.service;

import com.xingkong.lyn.model.web.Staff;

import java.util.List;

/**
 * Created by lyn on 2017/7/24.
 */
public interface IStaff {
    List<Staff> getStaffs();
    boolean addStaff(Staff staff);
    boolean updateStaff(Staff staff);
    boolean deleteStaff(List<Long> id);
    Staff getStaff(Long id);
}
