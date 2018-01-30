package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.Question;
import com.xingkong.lyn.service.anjian.IQuestion;
import com.xingkong.lyn.util.OtherUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
public class QuestionController {
    @Resource
    private IQuestion questionService;

    @RequestMapping(value = "/web/manage/question/list", method = RequestMethod.GET)
    @RequiresPermissions("exam:view")
    public Object webManageQuestionList(@PageableDefault(value = 15, sort = {"id"},
            direction = Sort.Direction.DESC)Pageable pageable, String questionType) {
        AjaxResults ajaxResults = new AjaxResults();
        switch (questionType) {
            case "tf" :
                ajaxResults.put("questions",questionService.findtfList(pageable));
                break;
            case "bf" :
                ajaxResults.put("questions",questionService.findbfList(pageable));
                break;
            case "sc" :
                ajaxResults.put("questions",questionService.findscList(pageable));
                break;
            case "mc" :
                ajaxResults.put("questions",questionService.findmcList(pageable));
                break;
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/detail", method = RequestMethod.GET)
    @RequiresPermissions("question:detail")
    public Object webManageQuestionDetail(Long id, String questionType) {
        AjaxResults ajaxResults = new AjaxResults();
        switch (questionType) {
            case "tf" :
                ajaxResults.put("question", questionService.findtf(id));
                break;
            case "bf" :
                ajaxResults.put("question", questionService.findbf(id));
                break;
            case "sc" :
                ajaxResults.put("question", questionService.findsc(id));
                break;
            case "mc" :
                ajaxResults.put("question", questionService.findmc(id));
                break;
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/add", method = RequestMethod.POST)
    @RequiresPermissions("question:add")
    public Object webManageQuestionAdd(@RequestBody Question question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setCreateTime(new Date());
            question.setUpdateTime(new Date());
            questionService.addQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/update", method = RequestMethod.PUT)
    @RequiresPermissions("question:update")
    public Object webManageQuestionUpdate(@RequestBody Question question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setUpdateTime(new Date());
            questionService.updateQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/delete", method = RequestMethod.DELETE)
    @RequiresPermissions("question:delete")
    public Object webManageQuestionDelete(String id, String questionType) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        questionService.deleteList(questionType, ids);
        return ajaxResults;
    }
}
