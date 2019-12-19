package com.investment.controller;

import com.investment.entity.User;
import com.investment.entity.Role;
import com.investment.service.UserService;
import com.investment.service.RoleService;
import com.investment.util.AjaxResult;
import com.investment.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * DispatherController 控制器
 *
 * @author Sun
 */
@Controller
@RequestMapping("/")
public class DispatherController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 跳转登录界面
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 表单提交登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult doLogin(User user, HttpSession session) {
        if (StringUtils.isEmpty(user.getUsername())) {
            ajaxResult.ajaxFalse("用户名不能为空");
            return ajaxResult;
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            ajaxResult.ajaxFalse("密码不能为空");
            return ajaxResult;
        }
        // 查询用户是否存在 并返回用户数据
        try {
            User ad = userService.login(user.getUsername(), user.getPassword());
            if (StringUtils.isEmpty(ad)) {
                ajaxResult.ajaxFalse("用户名或密码错误");
            } else {
                session.setAttribute(Const.ADMIN, ad);
                ajaxResult.ajaxTrue("登录成功");

                //获取角色列表，存入session
                if (session.getAttribute(Const.ROLE) == null) {
                    List<Role> roleList = roleService.selectAll();
                    session.setAttribute(Const.ROLE, roleList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ajaxResult.ajaxFalse("系统错误,请刷新");
        }
        return ajaxResult;
    }

    /**
     * 登出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }

    /**
     * 跳转修改密码页面
     *
     * @return
     */
    @GetMapping("/password")
    public String password() {
        return "/common/password";
    }

    /**
     * 修改密码
     *
     * @param session
     * @param password
     * @param newpassword
     * @param repassword
     * @return
     */
    @PostMapping("/editPassword")
    @ResponseBody
    public AjaxResult editPassword(HttpSession session, String password, String newpassword, String repassword) {
        User user = (User) session.getAttribute(Const.ADMIN);
        if (!password.equals(user.getPassword())) {
            ajaxResult.ajaxFalse("原密码错误");
            return ajaxResult;
        }
        if (!newpassword.equals(repassword)) {
            ajaxResult.ajaxFalse("密码不一致");
            return ajaxResult;
        }
        user.setPassword(newpassword);
        int count = userService.editByUser(user);
        if (count >= 1) {
            ajaxResult.ajaxTrue("修改密码成功");
        } else {
            ajaxResult.ajaxFalse("系统错误");
        }
        return ajaxResult;
    }

    @GetMapping("/info")
    public String info(HttpSession session, Model model) {
        User user = (User) session.getAttribute(Const.ADMIN);
        Role role = roleService.selectById(user.getRid());
        model.addAttribute(Const.ROLE, role);
        return "/common/info";
    }

    @PostMapping("/editInfo")
    @ResponseBody
    public AjaxResult editInfo(User user, HttpSession session) {
        User ad = (User) session.getAttribute(Const.ADMIN);
        ad.setUsername(user.getUsername());
        ad.setEmail(user.getEmail());
        ad.setPhone(user.getPhone());
        int count = userService.editByUser(ad);
        if (count >= 1) {
            ajaxResult.ajaxTrue("修改成功");
        } else {
            ajaxResult.ajaxFalse("修改失败");
        }
        return ajaxResult;
    }

}
