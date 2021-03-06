package com.xingkong.lyn.controller.anjian;

import com.xingkong.lyn.common.AjaxResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * Created by lyn on 2017/6/22.
 */
@RestController
@CrossOrigin
public class FileUploadController {
    @Value(value = "${file.upload.path}")
    private String filePath;

    private String parentPath = "uploadFile";

    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object uploadFile(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        AjaxResults ajaxResults = new AjaxResults();
        File parent = new File(filePath+File.separator+parentPath);
        if (!parent.exists()) {
            if (!parent.mkdir()) {
                ajaxResults.setCode(0);
                ajaxResults.setMsg("上传文件目录创建失败");
            }
        }
        Date date = new Date();
        Long time = date.getTime();
        String fileName = time.toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if(!file.isEmpty()){
            try{
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(parent.getAbsolutePath()+File.separator+fileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                ajaxResults.put("name", fileName);
                ajaxResults.put("path", "http://api.anjian.hanyuhuake.com/resource"+File.separator+parentPath+File.separator+fileName);
//                response.setContentType("image/png");
//                OutputStream os = response.getOutputStream();
//                os.write(file.getBytes());
//                os.flush();
//                os.close();
            }catch (FileNotFoundException e){
                logger.error("上传文件失败", e);
                ajaxResults.setCode(0);
                ajaxResults.setMsg("上传文件失败:文件未能找到");
                return ajaxResults;
            }catch (IOException e){
                logger.error("上传文件失败", e);
                ajaxResults.setCode(0);
                ajaxResults.setMsg("上传文件失败:文件传输异常");
                return ajaxResults;
            }
            return ajaxResults;
        }else{
            ajaxResults.setCode(0);
            ajaxResults.setMsg("上传文件失败:文件为空");
            return ajaxResults;
        }
    }
}
