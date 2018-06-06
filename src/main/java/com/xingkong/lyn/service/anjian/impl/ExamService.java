package com.xingkong.lyn.service.anjian.impl;

import com.xingkong.lyn.entity.anjian.*;
import com.xingkong.lyn.repository.anjian.ExamRepository;
import com.xingkong.lyn.service.anjian.IExam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ExamService implements IExam {
    @Resource
    private ExamRepository examDao;

    @Override
    public Page<Exam> findList(Pageable pageable, String query) {
        return StringUtils.isBlank(query)?examDao.findAll(pageable):examDao.findByExamNameLike("%" + query + "%", pageable);
    }

    @Override
    public Exam findById(long id) {
        return examDao.findOne(id);
    }

    @Override
    public boolean addExam(Exam exam) {
        examDao.saveAndFlush(exam);
        return true;
    }

    @Override
    public boolean deleteList(List<Long> ids) {
        examDao.deleteInBatch(examDao.findAll(ids));
        return true;
    }

    @Override
    public boolean updateExam(Exam exam) {
        examDao.saveAndFlush(exam);
        return true;
    }

    @Override
    public List<Exam> findAll() {
        List<Exam> exams = examDao.findAll();
        if (null != exams) {
            for (Exam exam : exams) {
                List<TrueFalse> tfSet = new ArrayList<>();
                List<SimpleChoice> scSet = new ArrayList<>();
                List<MultipleChoice> mcSet = new ArrayList<>();
                List<Course> courses = exam.getCourseList();
                List<Long> tfList = new ArrayList<>();
                List<Long> scList = new ArrayList<>();
                List<Long> mcList = new ArrayList<>();
                for (Course course : courses) {
                    course.getTfList().forEach((TrueFalse tf) -> {
                        if (!tfList.contains(tf.getId())) {
                            tfSet.add(tf);
                            tfList.add(tf.getId());
                        }
                    });
                    course.getScList().forEach((SimpleChoice sc) -> {
                        if (!scList.contains(sc.getId())) {
                            scSet.add(sc);
                            scList.add(sc.getId());
                        }
                    });
                    course.getMcList().forEach((MultipleChoice mc) -> {
                        if (!mcList.contains(mc.getId())) {
                            mcSet.add(mc);
                            mcList.add(mc.getId());
                        }
                    });
                }
                exam.setTfList(tfSet);
                exam.setScList(scSet);
                exam.setMcList(mcSet);
                createStandard(exam);
            }
        }
        return exams;
    }

    private Exam createStandard(Exam exam) {
        // 考试题库，从exam对应的json中获得 tfList scList mcList三个字段
        List<TrueFalse> tfList = exam.getTfList();
        List<SimpleChoice> scList = exam.getScList();
        List<MultipleChoice> mcList = exam.getMcList();
        // 记录三类题目数量和总数
        int tfCount = null == tfList?0:tfList.size();
        int scCount = null == scList?0:scList.size();
        int mcCount = null == mcList?0:mcList.size();
        int sumCount = tfCount + scCount + mcCount;
        // 该考试需要从题库中抓去的三类题目数量和总数;
        int examTfCount;
        int examScCount;
        int examMcCount;
        int examSumCount = 0;
        boolean flag = false;

        // 取半策略 考试题目控制在20-50之间,题库只有20题的时候，直接取题库所有题目
        if (20 == sumCount) {
            examTfCount = tfCount;
            examScCount = scCount;
            examMcCount = mcCount;
        } else {
            if (20 < sumCount && sumCount <= 40) {
                examSumCount = 20;
            } else if (40 < sumCount && sumCount < 100) {
                // 向上取整
                examSumCount = (int) Math.ceil((sumCount) / 2);
            } else if (100 < sumCount) {
                examSumCount = 50;
            }

            // 通过该类型题目在题库中的占比，获取出该类型考试需要抽取多少题目
            if (0 != tfCount && 0 == sumCount % tfCount) {
                examTfCount = tfCount / sumCount * examSumCount;
            } else {
                // 向下取整
                examTfCount = (int) Math.floor(tfCount / (float) sumCount * examSumCount);
                flag = !flag;
            }

            if (0 != scCount && 0 == sumCount % scCount) {
                examScCount = scCount / sumCount * examSumCount;
            } else {
                // 向上取整
                examScCount = flag ? (int) Math.ceil(scCount / (float) sumCount * examSumCount) : (int) Math.floor(scCount / (float) sumCount * examSumCount);
                flag = !flag;
            }

            if (0 != mcCount && 0 == sumCount % mcCount) {
                examMcCount = mcCount / sumCount * examSumCount;
            } else {
                // 向下取整
                examMcCount = flag ? (int) Math.ceil(mcCount / (float) sumCount * examSumCount) : (int) Math.floor(mcCount / (float) sumCount * examSumCount);
            }
        }
        exam.setExamTfCount(examTfCount);
        exam.setExamScCount(examScCount);
        exam.setExamMcCount(examMcCount);
        exam.setStandard((int)Math.ceil(0.6 * examSumCount));
        return exam;
    }
}
