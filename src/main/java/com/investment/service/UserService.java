package com.investment.service;

import com.investment.entity.User;
import com.investment.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 用户业务接口类
 *
 * @author Sun
 */
public interface UserService {

    User login(String username, String password);

    PageBean<User> queryPage(Map<String, Object> paramMap);

    int insertUser(User user);

    User selectById(Integer id);

    int delByUserIds(List<Integer> ids);

    int editByUser(User user);

    User selectByName(String username);

    User selectByEmail(String email);
}
