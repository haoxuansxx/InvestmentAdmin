package com.investment.mapper;

import com.investment.entity.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志数据库连接类
 * @author Sun
 */
public interface LogMapper {
    void insertByLog(Log log);

    List<Log> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int delByLogIds(List<Integer> ids);
}
