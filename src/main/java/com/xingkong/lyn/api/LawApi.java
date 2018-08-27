package com.xingkong.lyn.api;

import com.xingkong.lyn.annotation.AdminLog;
import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Law;
import com.xingkong.lyn.service.anjian.ILaw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by lyn on 2018/5/4.
 */
@RestController
@RequestMapping(value = "/api/law")
public class LawApi {
    @Resource
    private ILaw lawService;

    @RequestMapping(method = RequestMethod.GET)
//    @RequiresPermissions("news:view")
    @AdminLog(value = "法律法规:查看列表")
    public Object get(@PageableDefault(value = 15, sort = { "createTime" }, direction = Sort.Direction.DESC)
                                  Pageable pageable, String type) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Law> laws = lawService.findList(pageable, type);
        ajaxResults.put("laws", laws);
        return ajaxResults;
    }
}
