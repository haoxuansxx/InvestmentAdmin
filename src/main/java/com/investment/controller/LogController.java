package com.investment.controller;

import com.investment.service.LogService;
import com.investment.util.AjaxResult;
import com.investment.util.Data;
import com.investment.util.PageBean;
import com.investment.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志控制器
 *
 * @author Sun
 */
@Controller
@RequestMapping("/")
public class LogController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private LogService logService;

    /**
     * 日志页面
     *
     * @return
     */
    @GetMapping("/log")
    public String log() {
        return "log/logList";
    }


    /**
     * 异步加载日志列表
     *
     * @param pageno
     * @param pagesize
     * @param username
     * @param logTime
     * @return
     */
    @RequestMapping("/logList")
    @ResponseBody
    public Object adminList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                            @RequestParam(value = "limit", defaultValue = "20") Integer pagesize,
                            String username, String logTime) {
        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageno", pageno);
        paramMap.put("pagesize", pagesize);

        //判断是否为空
        if (!StringUtils.isEmpty(username)) {
            paramMap.put("username", username);
        }
        if (!StringUtils.isEmpty(logTime)) {
            String[] split = logTime.split(" - ");
            paramMap.put("stime", split[0]);
            paramMap.put("ftime", split[1]);
        }

        PageBean<Log> pageBean = logService.queryPage(paramMap);

        Map<String, Object> rest = new HashMap(16);
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count", pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }


    @PostMapping("/delLog")
    @ResponseBody
    public AjaxResult delLog(Data data) {
        int count = logService.delByLogIds(data.getIds());
        if (count >= data.getIds().size()) {
            ajaxResult.ajaxTrue("删除成功");
        } else {
            ajaxResult.ajaxFalse("删除失败");
        }
        return ajaxResult;
    }


}
