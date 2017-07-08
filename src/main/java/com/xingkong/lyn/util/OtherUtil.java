package com.xingkong.lyn.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
