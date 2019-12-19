package com.investment.mapper;

import com.investment.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户数据库连接类
 *
 * @author Sun
 */
public interface UserMapper {

    User login(@Param("username") String username, @Param("password") String password);

    List<User> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int insertUser(User user);

    User selectById(Integer id);

    int delByUserIds(List<Integer> ids);

    int editByUser(User user);

    User selectByName(String username);

    User selectByEmail(String email);
}
