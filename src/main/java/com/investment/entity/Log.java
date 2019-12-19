package com.investment.entity;

import org.springframework.stereotype.Component;

/**
 * 日志实体类
 *
 * @author Sun
 */
@Component
public class Log {
    /**
     * 唯一标识ID
     */
    private Integer id;
    /**
     * 操作时间
     */
    private String logTime;
    /**
     * 操作人
     */
    private String username;
    /**
     * 访问Url
     */
    private String uri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
