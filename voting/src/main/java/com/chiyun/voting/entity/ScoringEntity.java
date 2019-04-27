package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ApiModel
@Table(name = "scoring", schema = "tpdf", catalog = "")
@IdClass(ScoringEntityPK.class)
public class ScoringEntity {

    @ApiModelProperty("用户 id")
    private int pid;
    @ApiModelProperty("选项id")
    private int oid;
    @ApiModelProperty("对象id")
    private int qid;
    @ApiModelProperty("得分")
    private int df;

    @Id
    @Column(name = "pid")
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Id
    @Column(name = "oid")
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Id
    @Column(name = "qid")
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    @Basic
    @Column(name = "score")
    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringEntity that = (ScoringEntity) o;
        return pid == that.pid &&
                oid == that.oid &&
                qid == that.qid &&
                df == that.df;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, oid, qid, df);
    }
}
