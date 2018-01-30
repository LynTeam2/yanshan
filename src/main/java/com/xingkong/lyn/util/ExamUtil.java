package com.xingkong.lyn.util;

import java.util.*;

/**
 * Created by lyn on 2018/1/28.
 */
public class ExamUtil {
    public static void main(String[] args) {
        Map<String, Integer> ajType = new HashMap<>();
        Map<String, Integer> difficulty = new HashMap<>();
        Map<String, Integer> questionType = new HashMap<>();
        //试题规则 ajType, difficulty, questionType
        ajType.put("危险化学品", 3);
        ajType.put("消防", 2);
        difficulty.put("1", 1);
        difficulty.put("2", 4);
        questionType.put("1", 1);
        questionType.put("2", 4);

        //list 题库
        List<Map<String, Object>> list = new LinkedList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 1);
        map1.put("name", "试题1");
        map1.put("ajType", "危险化学品");
        map1.put("difficulty", 1);
        map1.put("questionType", 2);
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 2);
        map2.put("name", "试题2");
        map2.put("ajType", "消防");
        map2.put("difficulty", 1);
        map2.put("questionType", 1);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 3);
        map3.put("name", "试题3");
        map3.put("ajType", "消防");
        map3.put("difficulty", 2);
        map3.put("questionType", 2);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", 4);
        map4.put("name", "试题4");
        map4.put("ajType", "消防");
        map4.put("difficulty", 1);
        map4.put("questionType", 2);
        Map<String, Object> map5 = new HashMap<>();
        map5.put("id", 5);
        map5.put("name", "试题5");
        map5.put("ajType", "危险化学品");
        map5.put("difficulty", 2);
        map5.put("questionType", 2);
        Map<String, Object> map6 = new HashMap<>();
        map6.put("id", 6);
        map6.put("name", "试题6");
        map6.put("ajType", "危险化学品");
        map6.put("difficulty", 2);
        map6.put("questionType", 2);
        Map<String, Object> map7 = new HashMap<>();
        map7.put("id", 7);
        map7.put("name", "试题7");
        map7.put("ajType", "危险化学品");
        map7.put("difficulty", 2);
        map7.put("questionType", 1);
        Map<String, Object> map8 = new HashMap<>();
        map8.put("id", 8);
        map8.put("name", "试题8");
        map8.put("ajType", "消防");
        map8.put("difficulty", 2);
        map8.put("questionType", 2);
        Map<String, Object> map9 = new HashMap<>();
        map9.put("id", 9);
        map9.put("name", "试题9");
        map9.put("ajType", "危险化学品");
        map9.put("difficulty", 2);
        map9.put("questionType", 2);
        Map<String, Object> map10 = new HashMap<>();
        map10.put("id", 10);
        map10.put("name", "试题10");
        map10.put("ajType", "消防");
        map10.put("difficulty", 2);
        map10.put("questionType", 2);
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

        // 对题库做随机排序，保证后续遍历取的时候题库顺序打乱，伪随机
        list = random(list);

        //exam 考试需要的试题
        List<Map<String, Object>> exam = new LinkedList<>();
        for (Map<String, Object> map : list) {
            // 试题的三种类别
            String aj = (String)map.get("ajType");
            String di = map.get("difficulty").toString();
            String qu = map.get("questionType").toString();

            // 当三种条件为空的时候，不再取题
            if (ajType.isEmpty() || difficulty.isEmpty() || questionType.isEmpty()){
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
    }

    public static List<Map<String, Object>> random(List<Map<String, Object>> list) {
        int count = list.size();
        List<Map<String, Object>> resultList= new LinkedList<>();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int num = rand.nextInt(count - i);
            resultList.add(list.get(num));
            list.remove(num);
        }
        return resultList;
    }
}
