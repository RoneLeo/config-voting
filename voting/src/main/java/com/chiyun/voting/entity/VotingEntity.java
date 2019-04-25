package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "voting", schema = "tpdf", catalog = "")
@IdClass(VotingEntityPK.class)
@ApiModel
public class VotingEntity {
    @ApiModelProperty("用户id")
    private int pid;
    @ApiModelProperty("投票对象id")
    private int qid;
    @ApiModelProperty("选项id")
    private int oid;
    @ApiModelProperty("备注")
    private String bz;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Id
    @Column(name = "qid")
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    @Id
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Basic
    @Column(name = "remark")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingEntity that = (VotingEntity) o;
        return pid == that.pid &&
                qid == that.qid &&
                oid == that.oid &&
                Objects.equals(bz, that.bz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, qid, oid, bz);
    }
}
