package com.investment.service.Impl;

import com.investment.entity.User;
import com.investment.mapper.UserMapper;
import com.investment.service.UserService;
import com.investment.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 用户业务实体类
 *
 * @author Sun
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        return userMapper.login(username, password);
    }

    @Override
    public PageBean<User> queryPage(Map<String, Object> paramMap) {
        PageBean<User> pageBean = new PageBean<>((Integer) paramMap.get("pageno"), (Integer) paramMap.get("pagesize"));

        Integer startIndex = pageBean.getStartIndex();
        paramMap.put("startIndex", startIndex);
        List<User> datas = userMapper.queryList(paramMap);
        pageBean.setDatas(datas);

        Integer totalsize = userMapper.queryCount(paramMap);
        pageBean.setTotalsize(totalsize);
        return pageBean;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public int delByUserIds(List<Integer> ids) {
        return userMapper.delByUserIds(ids);
    }

    @Override
    public int editByUser(User user) {
        return userMapper.editByUser(user);
    }

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }
}
