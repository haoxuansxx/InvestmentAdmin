package com.investment.controller;

import com.investment.entity.User;
import com.investment.entity.TreeMenu;
import com.investment.service.TreeMenuService;
import com.investment.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 后台控制器
 *
 * @author Sun
 */
@Controller
@RequestMapping("/")
public class ManagerController {

    @Autowired
    private TreeMenuService treeMenuService;

    /**
     * 跳转后台页面
     *
     * @return
     */
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 异步加载权限树
     *
     * @param session
     * @return
     */
    @PostMapping("/treeMenu")
    @ResponseBody
    public Object treeMenu(HttpSession session) {
        if (!StringUtils.isEmpty(session.getAttribute(Const.TREEMENU))) {
            return session.getAttribute(Const.TREEMENU);
        }
        User user = (User) session.getAttribute(Const.ADMIN);
        List<TreeMenu> treeMenuList = treeMenuService.selectByAdminId(user.getId());
        session.setAttribute(Const.TREEMENU, treeMenuList);
        return treeMenuList;
    }

    /**
     * 异步加载后台主页
     *
     * @return
     */
    @GetMapping("/console")
    public String console() {
        return "console";
    }

}
