package com.huang.demo.entity;

/**
 * @author timkobe
 */
public class UserEntity {
    private Integer id;
    private Integer status;

    public UserEntity(Integer id, Integer status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
