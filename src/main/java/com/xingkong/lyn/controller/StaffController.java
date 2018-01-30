package com.xingkong.lyn.controller;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.model.web.Staff;
import com.xingkong.lyn.service.IStaff;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by lyn on 2017/7/24.
 */
@RestController
public class StaffController {
    @Resource
    private IStaff staffService;

    @RequestMapping(value = "/web/staff/list", method = RequestMethod.GET)
    public Object webStaffList(){
        AjaxResults ajaxResults = new AjaxResults();
        List<Staff> staffs = staffService.getStaffs();
        ajaxResults.put("staffs", staffs);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/staff/list", method = RequestMethod.GET)
//    @RequiresPermissions("staff:view")
    public Object webManageStaffList(){
        AjaxResults ajaxResults = new AjaxResults();
        List<Staff> staffs = staffService.getStaffs();
        ajaxResults.put("staffs", staffs);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/staff/add", method = RequestMethod.POST)
//    @RequiresPermissions("staff:add")
    public Object webManageStaffAdd(@RequestBody Staff staff){
        AjaxResults ajaxResults = new AjaxResults();
        if(null != staff.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            staff.setCreateTime(new Date());
            staffService.addStaff(staff);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/staff/update", method = RequestMethod.PUT)
//    @RequiresPermissions("staff:update")
    public Object webManageStaffUpdate(@RequestBody Staff staff){
        AjaxResults ajaxResults = new AjaxResults();
        if(null == staff.getId()){
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        }else{
            staffService.updateStaff(staff);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/staff/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("staff:delete")
    public Object webManageStaffDelete(String id){
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        staffService.deleteStaff(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/staff/detail", method = RequestMethod.GET)
//    @RequiresPermissions("staff:detail")
    public Object webManageStaffDetail(Long id){
        AjaxResults ajaxResults = new AjaxResults();
        Staff staff = staffService.getStaff(id);
        ajaxResults.put("staff", staff);
        return ajaxResults;
    }
}