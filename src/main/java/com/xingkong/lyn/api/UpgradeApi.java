package com.xingkong.lyn.api;

import com.xingkong.lyn.common.AjaxResults;
import com.xingkong.lyn.entity.anjian.*;
import com.xingkong.lyn.service.anjian.*;
import com.xingkong.lyn.util.OtherUtil;
import com.xingkong.lyn.util.SevenZipCompressUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UpgradeApi {
    @Resource
    private IBanner bannerService;

    @Resource
    private ICategory categoryService;

    @Resource
    private ICourse courseService;

    @Resource
    private IQuestion questionService;

    @Resource
    private IExam examService;

    @RequestMapping(value = "/api/sevenZip", method = RequestMethod.PUT)
    public Object sevenZip()throws IOException{
        String filePath = "upgrade.7z";
        File file = new File(filePath);
        file.delete();

        AjaxResults ajaxResults = new AjaxResults();
        /**
         * 课程数据
         */
        List<Course> courses = courseService.findAll();

        OtherUtil.writeJsonToFile("categories", categoryService.getSubCategory(1l),
                "upgrade/course", "category.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("危险化学品"),
                "upgrade/course", "weixianhuaxuepin.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("建筑施工"),
                "upgrade/course", "jianzhushigong.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("人员密集场所"),
                "upgrade/course", "renyuanmijichangsuo.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("交通运输"),
                "upgrade/course", "jiaotongyunshu.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("工业企业"),
                "upgrade/course", "gongyeqiye.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("消防"),
                "upgrade/course", "xiaofang.json");
        OtherUtil.writeJsonToFile("courses", courseService.findList("特种设备"),
                "upgrade/course", "tezhongshebei.json");
        OtherUtil.writeJsonToFile("courses", courseService.findLatestCourseList(),
                "upgrade/course", "latest.json");

        for (Course course : courses) {
            OtherUtil.writeJsonToFile("course", course, "upgrade/course", course.getId() + ".json");
        }

        /**
         * 考试数据
         */
        OtherUtil.writeJsonToFile("exams", examService.findAll(), "upgrade/exam", "exam.json");

        /**
         * 题库数据
         */
        OtherUtil.writeJsonToFile("questions", questionService.findNewtf(), "upgrade/question", "truefalse.json");
        OtherUtil.writeJsonToFile("questions", questionService.findNewsc(), "upgrade/question", "simplechoice.json");
        OtherUtil.writeJsonToFile("questions", questionService.findNewmc(), "upgrade/question", "multiplechoice.json");

        /**
         * banner图数据
         */
        OtherUtil.writeJsonToFile("banners", bannerService.findAll(), "upgrade/banner", "banner.json");

        /**
         * 法律法规类别
         */
        List<Map<String, Object>> lawTypes = new ArrayList<>();
        String[] nameArr = new String[]{"法律法规", "规范性文件", "标准规范", "文件解读"};
        String[] iconArr = new String[]{"http://39.105.27.225/resource/lawType/flfg.png",
                "http://39.105.27.225/resource/lawType/gfxwj.png",
                "http://39.105.27.225/resource/lawType/bzgf.png",
                "http://39.105.27.225/resource/lawType/wjjd.png"};
        for (int i = 0; i < 4; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", nameArr[i]);
            map.put("icon", iconArr[i]);
            map.put("id", i);
            lawTypes.add(map);
        }
        OtherUtil.writeJsonToFile("lawType", lawTypes, "upgrade/law", "lawType.json");

//        CompressUtil.compress("upgrade", "upgrade.zip");
        File sevenZipFile = new File("upgrade");
        SevenZipCompressUtil.compress("upgrade.7z", sevenZipFile);

        return ajaxResults;
    }

    @RequestMapping(value = "/api/upgrade", method = RequestMethod.GET, produces="application/zip")
    public void upgrade(HttpServletResponse response) throws IOException {
        String filePath = "upgrade.7z";
        File file = new File(filePath);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String(file.getName().getBytes("ISO8859-1"), "UTF-8"));
        response.setContentLength((int)file.length());
        response.setContentType("application/zip");
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream buff = new BufferedInputStream(fis);
        byte[] b = new byte[1024];// 相当于我们的缓存
        long k = 0;// 该值用于计算当前实际下载了多少字节
        OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备下载
        // 开始循环下载
        while (k < file.length()) {
            int j = buff.read(b, 0, 1024);
            k += j;
            myout.write(b, 0, j);
        }
        myout.flush();
        buff.close();
    }

}
