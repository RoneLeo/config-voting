package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "votingquestion", schema = "tpdf", catalog = "")
@ApiModel
public class VotingquestionEntity {

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("投票类型")
    private int tplx;
    @ApiModelProperty("标题")
    private String bt;
    @ApiModelProperty("活动id")
    private int hdid;
    @ApiModelProperty("最小数")
    private int zxs;
    @ApiModelProperty("最大数")
    private int zds;

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
    @Column(name = "vtype")
    public int getTplx() {
        return tplx;
    }

    public void setTplx(int tplx) {
        this.tplx = tplx;
    }

    @Basic
    @Column(name = "title")
    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    @Basic
    @Column(name = "themeid")
    public int getHdid() {
        return hdid;
    }

    public void setHdid(int hdid) {
        this.hdid = hdid;
    }

    @Basic
    @Column(name = "numless")
    public int getZxs() {
        return zxs;
    }

    public void setZxs(int zxs) {
        this.zxs = zxs;
    }

    @Basic
    @Column(name = "numbig")
    public int getZds() {
        return zds;
    }

    public void setZds(int zds) {
        this.zds = zds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingquestionEntity that = (VotingquestionEntity) o;
        return id == that.id &&
                tplx == that.tplx &&
                hdid == that.hdid &&
                zxs == that.zxs &&
                zds == that.zds &&
                Objects.equals(bt, that.bt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tplx, bt, hdid, zxs, zds);
    }
}
