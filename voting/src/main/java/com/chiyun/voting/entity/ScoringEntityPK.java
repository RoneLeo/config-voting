package com.chiyun.voting.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ScoringEntityPK implements Serializable {
    private int pid;
    private int oid;
    private int qid;

    @Column(name = "pid")
    @Id
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Column(name = "oid")
    @Id
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    @Column(name = "qid")
    @Id
    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringEntityPK that = (ScoringEntityPK) o;
        return pid == that.pid &&
                oid == that.oid &&
                qid == that.qid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid, oid, qid);
    }
}
