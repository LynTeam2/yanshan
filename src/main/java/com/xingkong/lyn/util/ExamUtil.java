package com.xingkong.lyn.util;

import com.xingkong.lyn.entity.anjian.MultipleChoice;
import com.xingkong.lyn.entity.anjian.SimpleChoice;
import com.xingkong.lyn.entity.anjian.TrueFalse;

import java.util.*;

/**
 * Created by lyn on 2018/1/28.
 */
public class ExamUtil {
    public static void main(String[] args) {

    }

    public static void createExam2() {
        // 考试题库，从exam对应的json中获得 tfList scList mcList三个字段
        List<TrueFalse> tfList = new ArrayList<>();
        List<SimpleChoice> scList = new ArrayList<>();
        List<MultipleChoice> mcList = new ArrayList<>();
        // 记录三类题目数量和总数
        int tfCount = tfList.size();
        int scCount = scList.size();
        int mcCount = mcList.size();
        int sumCount = tfCount + scCount + mcCount;
        // 考试算法生成的题目列表
        List<TrueFalse> examTfList = new ArrayList<>();
        List<SimpleChoice> examScList = new ArrayList<>();
        List<MultipleChoice> examMcList = new ArrayList<>();
        // 该考试需要从题库中抓去的三类题目数量和总数;
        int examTfCount;
        int examScCount;
        int examMcCount;
        int examSumCount = 0;
        boolean flag = false;

        // 取半策略 考试题目控制在20-50之间,题库只有20题的时候，直接取题库所有题目
        if (20 == sumCount) {
            examTfList = tfList;
            examScList = scList;
            examMcList = mcList;
        } else {
            if (20 < sumCount && sumCount <= 40) {
                examSumCount = 20;
            } else if (40 < sumCount && sumCount < 100) {
                // 向上取整
                examSumCount = (int)Math.ceil((sumCount) / 2);
            } else if (100 < sumCount) {
                examSumCount = 50;
            }

            // 通过该类型题目在题库中的占比，获取出该类型考试需要抽取多少题目
            if (0 != tfCount && 0 == sumCount%tfCount) {
                examTfCount = tfCount/sumCount * examSumCount;
            } else {
                // 向下取整
                examTfCount = (int)Math.floor(tfCount/(float)sumCount * examSumCount);
                flag = !flag;
            }

            if (0 != scCount && 0 == sumCount%scCount) {
                examScCount = scCount/sumCount * examSumCount;
            } else {
                // 向上取整
                examScCount = flag?(int)Math.ceil(scCount/(float)sumCount * examSumCount):(int)Math.floor(scCount/(float)sumCount * examSumCount);
                flag = !flag;
            }

            if (0 != mcCount && 0 == sumCount%mcCount) {
                examMcCount = mcCount/sumCount * examSumCount;
            } else {
                // 向下取整
                examMcCount = flag?(int)Math.ceil(mcCount/(float)sumCount * examSumCount):(int)Math.floor(mcCount/(float)sumCount * examSumCount);
            }

            // 随机乱序
            tfList = random(tfList);
            scList = random(scList);
            mcList = random(mcList);

            /**
             * 从题库中抽取题目 下面的代码就是从tfList中抽取前 examTfCount数量的题目，
             * 因为已经是乱序的，所以可以取前examTfCount数量的题目
              */

            examTfList = tfList.subList(0, examTfCount);
            examScList = scList.subList(0, examScCount);
            examMcList = mcList.subList(0, examMcCount);
        }
    }

    public static void createExam() {
        Map<String, Integer> ajType = new HashMap<>();
        Map<String, Integer> difficulty = new HashMap<>();
        Map<String, Integer> questionType = new HashMap<>();
        //试题规则 ajType, difficulty, questionType
        ajType.put("危险化学品", 20);
        difficulty.put("1", 10);
        difficulty.put("2", 6);
        difficulty.put("3", 4);
        questionType.put("1", 12);
        questionType.put("2", 3);
        questionType.put("3", 5);

        //list 题库
        List<Map<String, Object>> list = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "试题1");
        map1.put("ajType", "危险化学品");
        map1.put("difficulty", 1);
        map1.put("questionType", 1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "试题2");
        map2.put("ajType", "危险化学品");
        map2.put("difficulty", 1);
        map2.put("questionType", 1);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 3);
        map3.put("name", "试题3");
        map3.put("ajType", "危险化学品");
        map3.put("difficulty", 1);
        map3.put("questionType", 1);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", 4);
        map4.put("name", "试题4");
        map4.put("ajType", "危险化学品");
        map4.put("difficulty", 1);
        map4.put("questionType", 1);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("id", 5);
        map5.put("name", "试题5");
        map5.put("ajType", "危险化学品");
        map5.put("difficulty", 1);
        map5.put("questionType", 1);
        Map<String, Object> map6 = new HashMap<>();
        map6.put("id", 6);
        map6.put("name", "试题6");
        map6.put("ajType", "危险化学品");
        map6.put("difficulty", 1);
        map6.put("questionType", 1);
        Map<String, Object> map7 = new HashMap<>();
        map7.put("id", 7);
        map7.put("name", "试题7");
        map7.put("ajType", "危险化学品");
        map7.put("difficulty", 1);
        map7.put("questionType", 1);
        Map<String, Object> map8 = new HashMap<>();
        map8.put("id", 8);
        map8.put("name", "试题8");
        map8.put("ajType", "危险化学品");
        map8.put("difficulty", 1);
        map8.put("questionType", 1);
        Map<String, Object> map9 = new HashMap<>();
        map9.put("id", 9);
        map9.put("name", "试题9");
        map9.put("ajType", "危险化学品");
        map9.put("difficulty", 2);
        map9.put("questionType", 1);
        Map<String, Object> map10 = new HashMap<>();
        map10.put("id", 10);
        map10.put("name", "试题10");
        map10.put("ajType", "危险化学品");
        map10.put("difficulty", 2);
        map10.put("questionType", 1);
        Map<String, Object> map11 = new HashMap<>();
        map11.put("id", 11);
        map11.put("name", "试题10");
        map11.put("ajType", "危险化学品");
        map11.put("difficulty", 2);
        map11.put("questionType", 1);
        Map<String, Object> map12 = new HashMap<>();
        map12.put("id", 12);
        map12.put("name", "试题10");
        map12.put("ajType", "危险化学品");
        map12.put("difficulty", 2);
        map12.put("questionType", 1);
        Map<String, Object> map13 = new HashMap<>();
        map13.put("id", 13);
        map13.put("name", "试题10");
        map13.put("ajType", "危险化学品");
        map13.put("difficulty", 2);
        map13.put("questionType", 1);
        Map<String, Object> map14 = new HashMap<>();
        map14.put("id", 14);
        map14.put("name", "试题10");
        map14.put("ajType", "危险化学品");
        map14.put("difficulty", 2);
        map14.put("questionType", 1);
        Map<String, Object> map15 = new HashMap<>();
        map15.put("id", 15);
        map15.put("name", "试题10");
        map15.put("ajType", "危险化学品");
        map15.put("difficulty", 2);
        map15.put("questionType", 1);
        Map<String, Object> map16 = new HashMap<>();
        map16.put("id", 16);
        map16.put("name", "试题10");
        map16.put("ajType", "危险化学品");
        map16.put("difficulty", 3);
        map16.put("questionType", 1);
        Map<String, Object> map17 = new HashMap<>();
        map17.put("id", 17);
        map17.put("name", "试题10");
        map17.put("ajType", "危险化学品");
        map17.put("difficulty", 3);
        map17.put("questionType", 1);
        Map<String, Object> map18 = new HashMap<>();
        map18.put("id", 18);
        map18.put("name", "试题10");
        map18.put("ajType", "危险化学品");
        map18.put("difficulty", 3);
        map18.put("questionType", 1);
        Map<String, Object> map19 = new HashMap<>();
        map19.put("id", 19);
        map19.put("name", "试题10");
        map19.put("ajType", "危险化学品");
        map19.put("difficulty", 3);
        map19.put("questionType", 1);
        Map<String, Object> map20 = new HashMap<>();
        map20.put("id", 20);
        map20.put("name", "试题10");
        map20.put("ajType", "危险化学品");
        map20.put("difficulty", 3);
        map20.put("questionType", 1);
        Map<String, Object> map21 = new HashMap<>();
        map21.put("id", 21);
        map21.put("name", "试题10");
        map21.put("ajType", "危险化学品");
        map21.put("difficulty", 3);
        map21.put("questionType", 1);
        Map<String, Object> map22 = new HashMap<>();
        map22.put("id", 22);
        map22.put("name", "试题10");
        map22.put("ajType", "危险化学品");
        map22.put("difficulty", 3);
        map22.put("questionType", 1);
        Map<String, Object> map23 = new HashMap<>();
        map23.put("id", 23);
        map23.put("name", "试题10");
        map23.put("ajType", "危险化学品");
        map23.put("difficulty", 3);
        map23.put("questionType", 1);
        Map<String, Object> map24 = new HashMap<>();
        map24.put("id", 24);
        map24.put("name", "试题10");
        map24.put("ajType", "危险化学品");
        map24.put("difficulty", 1);
        map24.put("questionType", 2);
        Map<String, Object> map25 = new HashMap<>();
        map25.put("id", 25);
        map25.put("name", "试题10");
        map25.put("ajType", "危险化学品");
        map25.put("difficulty", 2);
        map25.put("questionType", 2);
        Map<String, Object> map26 = new HashMap<>();
        map26.put("id", 26);
        map26.put("name", "试题10");
        map26.put("ajType", "危险化学品");
        map26.put("difficulty", 3);
        map26.put("questionType", 2);
        Map<String, Object> map27 = new HashMap<>();
        map27.put("id", 27);
        map27.put("name", "试题10");
        map27.put("ajType", "危险化学品");
        map27.put("difficulty", 1);
        map27.put("questionType", 3);
        Map<String, Object> map28 = new HashMap<>();
        map28.put("id", 28);
        map28.put("name", "试题10");
        map28.put("ajType", "危险化学品");
        map28.put("difficulty", 2);
        map28.put("questionType", 3);
        Map<String, Object> map29 = new HashMap<>();
        map29.put("id", 29);
        map29.put("name", "试题10");
        map29.put("ajType", "危险化学品");
        map29.put("difficulty", 2);
        map29.put("questionType", 3);
        Map<String, Object> map30 = new HashMap<>();
        map30.put("id", 30);
        map30.put("name", "试题10");
        map30.put("ajType", "危险化学品");
        map30.put("difficulty", 3);
        map30.put("questionType", 3);
        Map<String, Object> map31 = new HashMap<>();
        map31.put("id", 31);
        map31.put("name", "试题10");
        map31.put("ajType", "危险化学品");
        map31.put("difficulty", 3);
        map31.put("questionType", 3);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        list.add(map7);
        list.add(map8);
        list.add(map9);
        list.add(map10);
        list.add(map11);
        list.add(map12);
        list.add(map13);
        list.add(map14);
        list.add(map15);
        list.add(map16);
        list.add(map17);
        list.add(map18);
        list.add(map19);
        list.add(map20);
        list.add(map21);
        list.add(map22);
        list.add(map23);
        list.add(map24);
        list.add(map25);
        list.add(map26);
        list.add(map27);
        list.add(map28);
        list.add(map29);
        list.add(map30);
        list.add(map31);

        // 对题库做随机排序，保证后续遍历取的时候题库顺序打乱，伪随机
        list = random(list);

        //exam 考试需要的试题
        List<Map<String, Object>> exam = new LinkedList<>();
        for (Map<String, Object> map : list) {
            // 试题的三种类别
            String aj = (String) map.get("ajType");
            String di = map.get("difficulty").toString();
            String qu = map.get("questionType").toString();

            // 当三种条件为空的时候，不再取题
            if (ajType.isEmpty() || difficulty.isEmpty() || questionType.isEmpty()) {
                break;
            }

            // 满足取题条件
            if (null != ajType.get(aj)
                    && null != difficulty.get(di)
                    && null != questionType.get(qu)) {
                // 加入到考试题目中
                exam.add(map);

                // 对试题规则做改动，需求数量-1，当试题规则需求数量为0时，删除此规则不做匹配
                if (ajType.get(aj) == 1) {
                    ajType.remove(aj);
                } else {
                    ajType.put(aj, ajType.get(aj) - 1);
                }
                if (difficulty.get(di) == 1) {
                    difficulty.remove(di);
                } else {
                    difficulty.put(di, difficulty.get(di) - 1);
                }
                if (questionType.get(qu) == 1) {
                    questionType.remove(qu);
                } else {
                    questionType.put(qu, questionType.get(qu) - 1);
                }
            }
        }

        System.out.println(exam.toString());
        System.out.println(exam.size());

        Set<String> set = new LinkedHashSet<>();
        set.add("123");
        set.add("342");
        set.add("123");
        System.out.println(set.toString());
    }

    public static List random(List list) {
        int count = list.size();
        List resultList = new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int num = rand.nextInt(count - i);
            resultList.add(list.get(num));
            list.remove(num);
        }
        return resultList;
    }
}
