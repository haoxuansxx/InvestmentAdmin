package com.investment.mapper;

import com.investment.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色数据库连接类
 * @author Sun
 */
public interface RoleMapper {
    List<Role> selectAll();

    List<Role> queryList(Map<String, Object> paramMap);

    Integer queryCount(Map<String, Object> paramMap);

    int delByRoleIds(List<Integer> ids);

    Role selectById(Integer id);

    Role selectByName(String name);

    int editByRole(Role role);

    int insertRole(Role role);
}
