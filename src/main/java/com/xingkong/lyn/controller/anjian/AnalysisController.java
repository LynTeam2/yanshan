package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.*;
import com.xingkong.lyn.service.anjian.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by lyn on 2018/10/22.
 */
@RestController
@RequestMapping(value = "/web/manage/analysis")
public class AnalysisController {
    @Resource
    private IExamHistory examHistoryService;
    @Resource
    private IUser userService;
    @Resource
    private ICourse courseService;
    @Resource
    private IUnit unitService;
    @Resource
    private IExam examService;

    @RequestMapping(value = "/period", method = RequestMethod.GET)
    public Object period(Long unitId, Long examId,
                         @PageableDefault(value = 15, sort = { "id" }, direction = Sort.Direction.DESC)
                         Pageable pageable, Date startDate, Date endDate) {
        AjaxResults ajaxResults = new AjaxResults();
        List<User> users;
        if (null == unitId) {
            users = userService.findList(pageable, null).getContent();
        } else {
            users = unitService.findById(unitId).getUserList();
        }
        List<Map> courseProcessList = new LinkedList<>();
        users.forEach(user -> {
            Map<String, Object> map = new HashMap<>();
            map.put("userName", user.getUserName());
            map.put("realName", user.getRealName());
            map.put("unitName", user.getUnit().getUnitName());
            map.put("duration", user.getCourseProcessList().stream().mapToInt(CourseProcess::getDuration).sum());
            courseProcessList.add(map);
        });
        ajaxResults.put("period", courseProcessList);
        return ajaxResults;
    }

    @RequestMapping(value = "/passNumber", method = RequestMethod.GET)
    public Object passNumber(Long unitId, Long examId) {
        AjaxResults ajaxResults = new AjaxResults();
        List<ExamHistory> historyList = examHistoryService.findListByExam(examId);
        if (null == unitId) {
            historyList.forEach(examHistory -> {

            });
        } else {

        }
        return ajaxResults;
    }

    @RequestMapping(value = "/superiority", method = RequestMethod.GET)
    public Object superiority(Long unitId, Long examId) {
        AjaxResults ajaxResults = new AjaxResults();
        List<ExamHistory> historyList = examHistoryService.findListByUnitAndExam(unitId, examId);
        int type1 = 0;
        int type2 = 0;
        int type3 = 0;
        int type4 = 0;
        int type5 = 0;
        int type6 = 0;
        int type7 = 0;
        for (ExamHistory examHistory : historyList) {
            for (ExamDetail examDetail : examHistory.getExamDetailList()) {
                if (examDetail.getResult() != 1) {
                    switch (examDetail.getAjType()) {
                        case "危险化学品":
                            type1 += 1;
                            break;
                        case "建筑施工":
                            type2 += 1;
                            break;
                        case "人员密集场所":
                            type3 += 1;
                            break;
                        case "交通运输":
                            type4 += 1;
                            break;
                        case "工业企业":
                            type5 += 1;
                            break;
                        case "消防":
                            type6 += 1;
                            break;
                        case "特种设备":
                            type7 += 1;
                            break;
                        default: break;
                    }
                }
            }
        }
        List<Map> superiorityList = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "危险化学品");
        map1.put("value", type1);
        superiorityList.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "建筑施工");
        map2.put("value", type2);
        superiorityList.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "人员密集场所");
        map3.put("value", type3);
        superiorityList.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "交通运输");
        map4.put("value", type4);
        superiorityList.add(map4);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("name", "工业企业");
        map5.put("value", type5);
        superiorityList.add(map5);
        Map<String, Object> map6 = new HashMap<>();
        map6.put("name", "消防");
        map6.put("value", type6);
        superiorityList.add(map6);
        Map<String, Object> map7 = new HashMap<>();
        map7.put("name", "特种设备");
        map7.put("value", type7);
        superiorityList.add(map7);
        ajaxResults.put("superiority", superiorityList);
        return ajaxResults;
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    public Object score(Long unitId, Long examId) {
        AjaxResults ajaxResults = new AjaxResults();
        List<ExamHistory> examHistoryList = examHistoryService.findListByUnitAndExam(unitId, examId);
        Map<String, Object> map = new HashMap<>();
        List<String> userList = new LinkedList<>();
        List<Integer> scoreList = new LinkedList<>();
        examHistoryList.forEach(examHistory -> {
            User user = userService.findById(examHistory.getUserId());
            if (map.keySet().contains(user.getRealName())) {
                if ((Integer)map.get(user.getRealName()) < examHistory.getExamScore()) {
                    map.put(user.getRealName(), examHistory.getExamScore());
                }
            } else {
                map.put(user.getRealName(), examHistory.getExamScore());
            }
        });
        for (String key : map.keySet()) {
            userList.add(key);
            scoreList.add((Integer)map.get(key));
        }
        ajaxResults.put("users", userList);
        ajaxResults.put("scores", scoreList);
        return ajaxResults;
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public Object rate(Long unitId) {
        AjaxResults ajaxResults = new AjaxResults();
        List<Exam> exams = examService.findAll();
        List<String> examList = new LinkedList<>();
        List<Double> passList = new LinkedList<>();
        List<Double> goodList = new LinkedList<>();
        for (Exam exam : exams) {
            int passCount = 0;
            int goodCount = 0;
            Map<Long, Integer> scoreMap = new HashMap<>();
            List<ExamHistory> examHistories = examHistoryService.findListByUnitAndExam(unitId, exam.getId());
            if (null == examHistories || examHistories.size() == 0) {
                continue;
            }
            for (ExamHistory examHistory : examHistories) {
                Long userId = examHistory.getUserId();
                int score = examHistory.getExamScore();
                if (!scoreMap.keySet().contains(userId) || scoreMap.get(userId) < score) {
                    scoreMap.put(userId, examHistory.getExamScore());
                }
            }
            int size = scoreMap.keySet().size();
            for (Long key : scoreMap.keySet()) {
                if (scoreMap.get(key) >= 80) {
                    goodCount += 1;
                    passCount += 1;
                } else if (scoreMap.get(key) >= 60) {
                    passCount += 1;
                }
            }
            examList.add(exam.getExamName());
            passList.add(Math.floor(passCount * 100 / size));
            goodList.add(Math.floor(goodCount * 100 / size));
        }
        ajaxResults.put("exams", examList);
        ajaxResults.put("pass", passList);
        ajaxResults.put("good", goodList);
        return ajaxResults;
    }

    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rank(Long examId) {
        AjaxResults ajaxResults = new AjaxResults();
        List<Unit> units = unitService.findAll();
        List<String> unitList = new LinkedList<>();
        List<Double> passList = new LinkedList<>();
        List<Double> goodList = new LinkedList<>();
        for (Unit unit : units) {
            int passCount = 0;
            int goodCount = 0;
            Map<Long, Integer> scoreMap = new HashMap<>();
            List<ExamHistory> examHistories = examHistoryService.findListByUnitAndExam(unit.getId(), examId);
            if (null == examHistories || examHistories.size() == 0) {
                continue;
            }
            for (ExamHistory examHistory : examHistories) {
                Long userId = examHistory.getUserId();
                int score = examHistory.getExamScore();
                if (!scoreMap.keySet().contains(userId) || scoreMap.get(userId) < score) {
                    scoreMap.put(userId, examHistory.getExamScore());
                }
            }
            int size = scoreMap.keySet().size();
            for (Long key : scoreMap.keySet()) {
                if (scoreMap.get(key) >= 80) {
                    goodCount += 1;
                    passCount += 1;
                } else if (scoreMap.get(key) >= 60) {
                    passCount += 1;
                }
            }
            unitList.add(unit.getUnitName());
            passList.add(Math.floor(passCount * 100 / size));
            goodList.add(Math.floor(goodCount * 100 / size));
        }
        ajaxResults.put("units", unitList);
        ajaxResults.put("pass", passList);
        ajaxResults.put("good", goodList);
        return ajaxResults;
    }
}
