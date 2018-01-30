package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Exam;
import com.xingkong.lyn.service.anjian.IExam;
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
public class ExamController {
    @Resource
    private IExam examService;

    @RequestMapping(value = "/web/manage/exam/list", method = RequestMethod.GET)
    @RequiresPermissions("exam:view")
    public Object webManageExamList(@PageableDefault(value = 15, sort = {"id"},
            direction = Sort.Direction.DESC)Pageable pageable) {
        AjaxResults ajaxResults = new AjaxResults();
        Page<Exam> exams = examService.findList(pageable);
        ajaxResults.put("exams", exams);
        return exams;
    }

    @RequestMapping(value = "/web/manage/exam/add", method = RequestMethod.POST)
    @RequiresPermissions("exam:add")
    public Object webManageExamAdd(@RequestBody Exam exam) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != exam.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            exam.setCreateTime(new Date());
            exam.setUpdateTime(new Date());
            examService.addExam(exam);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/exam/update", method = RequestMethod.PUT)
    @RequiresPermissions("exam:update")
    public Object webManageExamUpdate(@RequestBody Exam exam) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == exam.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            exam.setUpdateTime(new Date());
            examService.updateExam(exam);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/exam/detail", method = RequestMethod.GET)
    @RequiresPermissions("exam:detail")
    public Object webManageExamDetail(Long id) {
        AjaxResults ajaxResults = new AjaxResults();
        Exam exam = examService.findById(id);
        ajaxResults.put("exam", exam);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/exam/delete", method = RequestMethod.DELETE)
    @RequiresPermissions("exam:delete")
    public Object webManageExamDelete(String id) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        examService.deleteList(ids);
        return ajaxResults;
    }
}
