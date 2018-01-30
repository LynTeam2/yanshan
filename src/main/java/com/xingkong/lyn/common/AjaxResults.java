package com.xingkong.lyn.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyn on 2017/6/13.
 */
@Data
public class AjaxResults implements Serializable{
    private static final long serialVersionUID = 1L;

    private Integer code = 1;

    private String msg = "";

    private Map<String,Object> results = new HashMap<>();

    public void put(String key, Object value){
        this.results.put(key, value);
    }

    public void putAll(Map<String,Object> map){
        this.results.putAll(map);
    }
}
