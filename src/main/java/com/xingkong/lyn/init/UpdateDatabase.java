package com.xingkong.lyn.init;

import com.xingkong.lyn.service.IShiro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by lyn on 2017/5/9.
 */
@Component
@Order(value = 1)
public class UpdateDatabase implements CommandLineRunner{
    @Resource
    private IShiro shiroService;
    @Override
    public void run(String... args) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作<<<<<<<<<<<<<");
        shiroService.updatePermission();
    }
}
