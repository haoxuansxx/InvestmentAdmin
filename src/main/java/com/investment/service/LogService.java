package com.investment.service;

import com.investment.entity.Log;
import com.investment.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 日志业务接口类
 *
 * @author Sun
 */
public interface LogService {
    void insertByLog(Log log);

    PageBean<Log> queryPage(Map<String, Object> paramMap);

    int delByLogIds(List<Integer> ids);
}
