package com.investment.entity;

/**
 * 角色实体类
 *
 * @author Sun
 */
public class Role {
    /**
     * 唯一标识ID
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
