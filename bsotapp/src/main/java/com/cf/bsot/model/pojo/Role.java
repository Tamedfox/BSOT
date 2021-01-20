package com.cf.bsot.model.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cf
 * @version 1.0
 * @date 2020/9/15 23:17
 */
public class Role implements Serializable {

    private Long id;

    private String name;

    private String description;

    private Date createTime;

    private Date updateTime;

    private Integer status;

    private Integer order_seq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderSeq() {
        return order_seq;
    }

    public void setOrderSeq(Integer order) {
        this.order_seq = order;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", orderSeq=" + order_seq +
                '}';
    }
}
