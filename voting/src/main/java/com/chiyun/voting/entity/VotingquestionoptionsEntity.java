package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "votingquestionoptions", schema = "tpdf", catalog = "")
@ApiModel
public class VotingquestionoptionsEntity {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("对象id")
    private int dxid;
    @ApiModelProperty("选项名称")
    private String xxmc;
    @ApiModelProperty("选项描述")
    private String xxnr;
    @ApiModelProperty("选项类型：1 文字，2 多媒体，3 数字，4 其他")
    private int xxlx;

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
    @Column(name = "qid")
    public int getDxid() {
        return dxid;
    }

    public void setDxid(int dxid) {
        this.dxid = dxid;
    }

    @Basic
    @Column(name = "name")
    public String getXxmc() {
        return xxmc;
    }

    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }

    @Basic
    @Column(name = "content")
    public String getXxnr() {
        return xxnr;
    }

    public void setXxnr(String xxnr) {
        this.xxnr = xxnr;
    }

    @Basic
    @Column(name = "otype")
    public int getXxlx() {
        return xxlx;
    }

    public void setXxlx(int xxlx) {
        this.xxlx = xxlx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingquestionoptionsEntity that = (VotingquestionoptionsEntity) o;
        return id == that.id &&
                dxid == that.dxid &&
                Objects.equals(xxmc, that.xxmc) &&
                Objects.equals(xxnr, that.xxnr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dxid, xxmc, xxnr);
    }
}
