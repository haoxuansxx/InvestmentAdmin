package com.investment.service;

import com.investment.util.PageBean;
import com.investment.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色业务接口类
 *
 * @author Sun
 */
public interface RoleService {
    List<Role> selectAll();

    PageBean<Role> queryPage(Map<String, Object> paramMap);

    int delByRoleIds(List<Integer> ids);

    Role selectById(Integer id);

    Role selectByName(String name);

    int editByRole(Role role);

    int insertRole(Role role);
}
