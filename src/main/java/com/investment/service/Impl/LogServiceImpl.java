package com.investment.service.Impl;

import com.investment.entity.Log;
import com.investment.service.LogService;
import com.investment.util.PageBean;
import com.investment.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 日志业务实体类
 *
 * @author Sun
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public void insertByLog(Log log) {
        logMapper.insertByLog(log);
    }

    @Override
    public PageBean<Log> queryPage(Map<String, Object> paramMap) {
        PageBean<Log> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<Log> datas = logMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = logMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int delByLogIds(List<Integer> ids) {
        return logMapper.delByLogIds(ids);
    }
}
