package com.chiyun.voting.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class VotingEntityPK implements Serializable {
    private int pid;
    private int qid;
    private int oid;

    @Column(name = "pid")
    @Id
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Column(name = "qid")
    @Id
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    @Column(name = "oid")
    @Id
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingEntityPK that = (VotingEntityPK) o;
        return pid == that.pid &&
                qid == that.qid &&
                oid == that.oid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, qid, oid);
    }
}
