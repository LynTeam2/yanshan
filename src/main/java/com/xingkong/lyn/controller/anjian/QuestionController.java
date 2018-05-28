package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.MultipleChoice;
import com.xingkong.lyn.entity.anjian.SimpleChoice;
import com.xingkong.lyn.entity.anjian.TrueFalse;
import com.xingkong.lyn.service.anjian.IQuestion;
import com.xingkong.lyn.util.OtherUtil;
import com.xingkong.lyn.util.PinyinUtil;
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
//    @RequiresPermissions("question:view")
    public Object webManageQuestionList(@PageableDefault(value = 15, sort = {"id"},
            direction = Sort.Direction.DESC)Pageable pageable, String questionType, String query) {
        AjaxResults ajaxResults = new AjaxResults();
        switch (questionType) {
            case "tf" :
                ajaxResults.put("questions",questionService.findtfList(pageable, query));
                break;
            case "bf" :
                ajaxResults.put("questions",questionService.findbfList(pageable));
                break;
            case "sc" :
                ajaxResults.put("questions",questionService.findscList(pageable, query));
                break;
            case "mc" :
                ajaxResults.put("questions",questionService.findmcList(pageable, query));
                break;
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/all", method = RequestMethod.GET)
//    @RequiresPermissions("question:view")
    public Object webManageQuestionList(String questionType) {
        AjaxResults ajaxResults = new AjaxResults();
        switch (questionType) {
            case "tf" :
                ajaxResults.put("questions",questionService.findNewtf());
                break;
            case "bf" :
                ajaxResults.put("questions",questionService.findNewbf());
                break;
            case "sc" :
                ajaxResults.put("questions",questionService.findNewsc());
                break;
            case "mc" :
                ajaxResults.put("questions",questionService.findNewmc());
                break;
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/detail", method = RequestMethod.GET)
//    @RequiresPermissions("question:detail")
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

    @RequestMapping(value = "/web/manage/trueFalse/add", method = RequestMethod.POST)
//    @RequiresPermissions("question:add")
    public Object webManageTrueQuestionAdd(@RequestBody TrueFalse question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("1");
            question.setCreateTime(new Date());
            question.setUpdateTime(new Date());
            questionService.addQuestion(question);
            ajaxResults.setMsg(question.getQuestion());
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/simpleChoice/add", method = RequestMethod.POST)
//    @RequiresPermissions("question:add")
    public Object webManageQuestionAdd(@RequestBody SimpleChoice question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("2");
            question.setCreateTime(new Date());
            question.setUpdateTime(new Date());
            questionService.addQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/multipleChoice/add", method = RequestMethod.POST)
//    @RequiresPermissions("question:add")
    public Object webManageQuestionAdd(@RequestBody MultipleChoice question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null != question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("3");
            StringBuilder sb = new StringBuilder("");
            String[] arr = question.getAnswer().split("\"");
            for (int i = 1; i < arr.length - 1; i += 2) {
                if (i == 1) {
                    sb.append(arr[i]);
                } else {
                    sb.append(",").append(arr[i]);
                }
            }
            question.setAnswer(sb.toString());
            question.setCreateTime(new Date());
            question.setUpdateTime(new Date());
            questionService.addQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/trueFalse/update", method = RequestMethod.PUT)
//    @RequiresPermissions("question:update")
    public Object webManageQuestionUpdate(@RequestBody TrueFalse question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("1");
            question.setUpdateTime(new Date());
            questionService.updateQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/simpleChoice/update", method = RequestMethod.PUT)
//    @RequiresPermissions("question:update")
    public Object webManageQuestionUpdate(@RequestBody SimpleChoice question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("2");
            question.setUpdateTime(new Date());
            questionService.updateQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/multipleChoice/update", method = RequestMethod.PUT)
//    @RequiresPermissions("question:update")
    public Object webManageQuestionUpdate(@RequestBody MultipleChoice question) {
        AjaxResults ajaxResults = new AjaxResults();
        if (null == question.getId()) {
            ajaxResults.setCode(0);
            ajaxResults.setMsg("参数错误");
        } else {
            question.setDifficulty("3");
            StringBuilder sb = new StringBuilder("");
            String[] arr = question.getAnswer().split("\"");
            for (int i = 1; i < arr.length - 1; i += 2) {
                if (i == 1) {
                    sb.append(arr[i]);
                } else {
                    sb.append(",").append(arr[i]);
                }
            }
            question.setAnswer(sb.toString());
            question.setUpdateTime(new Date());
            questionService.updateQuestion(question);
        }
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/delete", method = RequestMethod.DELETE)
//    @RequiresPermissions("question:delete")
    public Object webManageQuestionDelete(String id, String questionType) {
        AjaxResults ajaxResults = new AjaxResults();
        Long[] arr = StringUtils.isBlank(id)? null: OtherUtil.parseStringtoLong(id);
        List<Long> ids = Arrays.asList(arr);
        questionService.deleteList(questionType, ids);
        return ajaxResults;
    }

    @RequestMapping(value = "/web/manage/question/addUid", method = RequestMethod.GET)
    public Object addUid() {
        AjaxResults ajaxResults = new AjaxResults();
        List<TrueFalse> tfList = questionService.findNewtf();
        List<SimpleChoice> scList = questionService.findNewsc();
        List<MultipleChoice> mcList = questionService.findNewmc();
        tfList.forEach((TrueFalse entity) -> {
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            questionService.updateQuestion(entity);
        });
        scList.forEach((SimpleChoice entity) -> {
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            questionService.updateQuestion(entity);
        });
        mcList.forEach((MultipleChoice entity) -> {
            entity.setUid(PinyinUtil.converterToFirstSpell(entity.getAjType()) + entity.getQuestionType() + entity.getId());
            questionService.updateQuestion(entity);
        });
        return ajaxResults;
    }
}
