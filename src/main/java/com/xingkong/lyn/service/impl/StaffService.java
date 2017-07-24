package com.xingkong.lyn.service.impl;

import com.xingkong.lyn.model.web.Staff;
import com.xingkong.lyn.repository.web.StaffRepository;
import com.xingkong.lyn.service.IStaff;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lyn on 2017/7/24.
 */
@Service
public class StaffService implements IStaff{
    @Resource
    private StaffRepository staffDao;

    @Override
    public List<Staff> getStaffs() {
        return staffDao.findAll();
    }

    @Override
    public boolean addStaff(Staff staff) {
        staffDao.save(staff);
        return true;
    }

    @Override
    public boolean updateStaff(Staff staff) {
        staffDao.save(staff);
        return false;
    }

    @Override
    public boolean deleteStaff(List<Long> ids) {
        staffDao.deleteInBatch(staffDao.findAll(ids));
        return false;
    }

    @Override
    public Staff getStaff(Long id) {
        Staff staff = staffDao.getOne(id);
        return staff;
    }
}
