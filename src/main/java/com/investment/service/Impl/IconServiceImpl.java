package com.investment.service.Impl;

import com.investment.entity.Icon;
import com.investment.mapper.IconMapper;
import com.investment.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图标业务实体类
 *
 * @author Sun
 */
@Service
public class IconServiceImpl implements IconService {

    @Autowired
    private IconMapper iconMapper;

    @Override
    public List<Icon> selectAll() {
        return iconMapper.selectAll();
    }
}
