package com.xingkong.lyn.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lyn on 2017/4/30.
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceSettings {
    private String type;
    private String driver;
    private String url;
    private String username;
    private String password;
    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;
    private int timeBetweenEvictionRunsMillis ;
    private int minEvictableIdleTimeMillis ;
    private String validationQuery ;
    private boolean testWhileIdle ;
    private boolean testOnBorrow ;
    private boolean testOnReturn ;
    private boolean poolPreparedStatements ;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters ;
}
