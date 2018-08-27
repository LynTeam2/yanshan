package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Unit;
import com.xingkong.lyn.service.anjian.IUnit;
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
import java.util.Date;
import java.util.List;

@RestController
public class UnitController {
    @Resource
    private IUnit unitService;

    @RequestMapping(value = "/web/manage/unit/list", method = RequestMethod.GET)
//    @RequiresPermissions("unit:view")
    public Object webManageUnitList(@PageableDefault(value = 15, sort = {"id"}, direction = Sort.Direction.DESC)Pageable pageable,
                                    String query) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Unit> units = unitService.findList(pageable, query);
        ajaxResults.put("units", units);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/unit/all", method = RequestMethod.GET)
//    @RequiresPermissions("unit:all")
    public Object webMangeUnitAll() {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("units", unitService.findAll());
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/unit/add", method = RequestMethod.POST)
//    @RequiresPermissions("unit:add")
    public Object webManageUnitAdd(@RequestBody Unit unit) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != unit.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            unit.setCreateTime(new Date());
            unitService.addUnit(unit);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/unit/update", method = RequestMethod.PUT)
//    @RequiresPermissions("unit:update")
    public Object webManageUnitUpdate(@RequestBody Unit unit) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == unit.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            unitService.updateUnit(unit);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/unit/detail", method = RequestMethod.GET)
//    @RequiresPermissions("unit:detail")
    public Object webManageUnitDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        Unit unit = unitService.findById(id);
        ajaxResults.put("unit", unit);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/unit/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("unit:delete")
    public Object webManageUnitDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        unitService.deleteList(ids);
        return ajaxResults;
    }
}
