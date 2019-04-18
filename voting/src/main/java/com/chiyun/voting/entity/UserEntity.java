package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ApiModel
@Table(name = "user", schema = "tpdf", catalog = "")
public class UserEntity {
    private int id;
    @ApiModelProperty("用户账号")
    private String zh;
    @ApiModelProperty("用户密码")
    private String mm;
    @ApiModelProperty("用户名称")
    private String mc;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "account")
    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    @Basic
    @Column(name = "password")
    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    @Basic
    @Column(name = "name")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(zh, that.zh) &&
                Objects.equals(mm, that.mm) &&
                Objects.equals(mc, that.mc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zh, mm, mc);
    }
}
