package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Law;
import com.xingkong.lyn.service.anjian.ILaw;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
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

/**
 * Created by lyn on 2018/5/3.
 */
@RestController
public class LawController {
    @Resource
    private ILaw lawService;

    @RequestMapping(value = "/web/manage/law/list", method = RequestMethod.GET)
    public Object webManageLawList(@PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                                               Pageable pageable, String query) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Law> laws = lawService.findListByQuery(pageable, query);
        ajaxResults.put("laws", laws);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/law/add", method = RequestMethod.POST)
    public Object webManageLawAdd(@RequestBody Law law) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != law.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("新增失败：参数错误");
        } else {
            law.setCreateTime(new Date());
            law.setUpdateTime(new Date());
            lawService.addLaw(law);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/law/update", method = RequestMethod.PUT)
    public Object webManageLawUpdate(@RequestBody Law law) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == law.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("新增失败：参数错误");
        } else {
            law.setCreateTime(new Date());
            lawService.updateLaw(law);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/law/delete", method = RequestMethod.DELETE)
    public Object webManageLawDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        lawService.deleteList(ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/law/detail", method = RequestMethod.GET)
//    @RequiresPermissions("course:detail")
    public Object webManageLawDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        ajaxResults.put("law", lawService.findById(id));
        return ajaxResults;
    }
}
