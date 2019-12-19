package com.investment.mapper;

import com.investment.entity.Icon;

import java.util.List;

/**
 * 图标数据库连接类
 * @author Sun
 */
public interface IconMapper {
    List<Icon> selectAll();

}
