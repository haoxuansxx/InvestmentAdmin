package com.investment.interceptor;

import com.investment.entity.User;
import com.investment.entity.Log;
import com.investment.service.LogService;
import com.investment.util.Const;
import com.investment.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 日志过滤器
 *
 * @author Sun
 */
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    private Log log;
    @Autowired
    private LogService logService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String stringDate = DateUtil.getStringDate("yyyy-MM-dd HH:mm:ss");
        String username = null;
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        if (request.getSession().getAttribute(Const.ADMIN) != null) {
            User user = (User) request.getSession().getAttribute(Const.ADMIN);
            username = user.getUsername();
        }
        String uri = request.getRequestURI();
        if (uri.equals("/logList")) {
            return;
        }

        log.setLogTime(stringDate);
        log.setUsername(username);
        log.setUri(uri);

        logService.insertByLog(log);
    }


}
