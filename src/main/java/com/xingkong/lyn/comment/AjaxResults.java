package com.xingkong.lyn.comment;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyn on 2017/6/13.
 */
public class AjaxResults implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer code = 0;

    private String msg = "";

    private Map<String,Object> results = new HashMap<>();

    public void put(String key, Object value){
        this.results.put(key, value);
    }

    public void setCode (Integer code){
        this.code = code;
    }

    public void setMsg (String msg){
        this.msg = msg;
    }
}
