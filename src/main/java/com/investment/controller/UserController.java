package com.investment.controller;

import com.investment.entity.User;
import com.investment.service.UserService;
import com.investment.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 *
 * @author Sun
 */
@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private AjaxResult ajaxResult;
    @Autowired
    private UserService userService;

    /**
     * 跳转管理员页面
     *
     * @return
     */
    @GetMapping("/user")
    public String user() {
        return "user/userList";
    }

    /**
     * 异步加载管理员列表
     *
     * @param pageno
     * @param pagesize
     * @param username
     * @param phone
     * @param email
     * @return
     */
    @RequestMapping("/userList")
    @ResponseBody
    public Object userList(@RequestParam(value = "page", defaultValue = "1") Integer pageno,
                            @RequestParam(value = "limit", defaultValue = "5") Integer pagesize,
                            String username, String phone, String email, String rid) {
        Map<String, Object> paramMap = new HashMap(16);
        paramMap.put("pageno", pageno);
        paramMap.put("pagesize", pagesize);

        //判断是否为空
        if (!StringUtils.isEmpty(username)) {
            paramMap.put("username", username);
        }
        if (!StringUtils.isEmpty(phone)) {
            paramMap.put("phone", phone);
        }
        if (!StringUtils.isEmpty(email)) {
            paramMap.put("email", email);
        }
        if (!StringUtils.isEmpty(rid) && !rid.equals("0")) {
            paramMap.put("rid", rid);
        }

        PageBean<User> pageBean = userService.queryPage(paramMap);

        Map<String, Object> rest = new HashMap(16);
        rest.put("code", 0);
        rest.put("msg", "");
        rest.put("count", pageBean.getTotalsize());
        rest.put("data", pageBean.getDatas());
        return rest;
    }

    /**
     * 跳转添加管理员页面
     *
     * @return
     */
    @GetMapping("/addUser")
    public String addUser(String type, Integer id, Model model) {
        if (type != null && type.equals("edit")) {
            User user = userService.selectById(id);
            model.addAttribute(Const.ADMIN, user);
        }
        return "user/addUser";
    }

    /**
     * 添加管理员  修改管理员
     *
     * @param user
     * @param status
     * @return
     */
    @PostMapping("/addUser")
    @ResponseBody
    public AjaxResult submitAddUser(User user, String status) {
        User byName = userService.selectByName(user.getUsername());
        User byEmail = userService.selectByEmail(user.getEmail());
        if (user.getRid().equals(0)) {
            ajaxResult.ajaxFalse("请选择角色");
            return ajaxResult;
        }
        //on 表示通过 null 表示待审核
        user.setStatus(status != null ? 1 : 0);
        if (user.getId() != null) {
            //修改管理员
            if (byName != null && !byName.getId().equals(user.getId())) {
                //与修改用户名一样，但存在数据库中，表示后来改的用户名已存在
                ajaxResult.ajaxFalse("用户名已存在");
                return ajaxResult;
            }
            if (byEmail != null && !byEmail.getId().equals(user.getId())) {
                //与修改邮箱一样，但存在数据库中，表示后来改的邮箱已存在
                ajaxResult.ajaxFalse("邮箱已存在");
                return ajaxResult;
            }
            int count = userService.editByUser(user);
            if (count > 0) {
                ajaxResult.ajaxTrue("修改成功");
            } else {
                ajaxResult.ajaxFalse("修改失败");
            }
        } else {
            //添加管理员
            if (byName != null) {
                //与原用户名不一样，但存在数据库中，表示后来改的用户名已存在
                ajaxResult.ajaxFalse("用户名已存在");
                return ajaxResult;
            }
            if (byEmail != null) {
                //与原邮箱不一样，但存在数据库中，表示后来改的邮箱已存在
                ajaxResult.ajaxFalse("邮箱已存在");
                return ajaxResult;
            }
            String stringDate = DateUtil.getStringDate("yyyy-MM-dd");
            user.setCreatetime(stringDate);
            // 设置默认密码
            user.setPassword("123");
            int count = userService.insertUser(user);
            if (count > 0) {
                ajaxResult.ajaxTrue("添加成功");
            } else {
                ajaxResult.ajaxFalse("添加失败");
            }
        }
        return ajaxResult;
    }

    /**
     * 删除管理员
     *
     * @param data
     * @return
     */
    @PostMapping("/delUser")
    @ResponseBody
    public AjaxResult delUser(Data data) {
        int count = userService.delByUserIds(data.getIds());
        if (count >= data.getIds().size()) {
            ajaxResult.ajaxTrue("删除成功");
        } else {
            ajaxResult.ajaxFalse("删除失败");
        }
        return ajaxResult;
    }

}
