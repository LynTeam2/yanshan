package com.xingkong.lyn.service;

import java.util.Map;

/**
 * Created by lyn on 2017/5/18.
 */
public interface IShiro {
    Map<String, String> loadFilterChainDefinitions();
    void updatePermission();
}
