package com.investment.service;

import com.investment.entity.TreeMenu;

import java.util.List;

/**
 * 菜单业务接口类
 *
 * @author Sun
 */
public interface TreeMenuService {

    List<TreeMenu> selectByAdminId(Integer id);


    List<TreeMenu> selectAll();

    TreeMenu selectById(Integer id);

    TreeMenu selectByName(String name);

    TreeMenu selectByUrl(String url);

    int editByPermission(TreeMenu treeMenu);

    int insertPermission(TreeMenu treeMenu);

    int delByPermissionIds(List<Integer> ids);

    List<TreeMenu> selectByPid(Integer id);

    List<TreeMenu> selectByRoleId(Integer id);

    int updateRolePermission(List<Integer> ids, Integer id);
}
