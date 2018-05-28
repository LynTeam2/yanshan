package com.xingkong.lyn.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import groovy.json.internal.IO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by lyn on 2017/7/8.
 */
public class OtherUtil {

    private static final Logger logger = LoggerFactory.getLogger(OtherUtil.class);

    public static Long[] parseStringtoLong(String fields){
        String[] array = fields.split(",");
        Long[] ids = new Long[array.length];
        for(int i = 0; i < array.length; i++){
            try{
                ids[i] = Long.parseLong(array[i]);
            }catch (Exception e){
                logger.error(e.getMessage(), e);
                ids[i] = 0l;
            }
        }
        return ids;
    }

    public static void writeJsonToFile(String entryName, Object object, String filePath, String fileName) throws IOException {
        File dir = new File(filePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(filePath + File.separator + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(entryName, object);

        fileWriter.write(JSON.toJSONString(jsonObject, SerializerFeature.DisableCircularReferenceDetect));
        fileWriter.close();
    }

    public static String handleQuery(String query) {
        return "%" + query + "%";
    }
}
