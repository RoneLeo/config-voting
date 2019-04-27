package com.chiyun.voting.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "votingtype", schema = "tpdf", catalog = "")
public class VotingtypeEntity {
    private int id;
    private int tplx;
    private String lxmc;

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
    @Column(name = "vname")
    public String getLxmc() {
        return lxmc;
    }

    public void setLxmc(String lxmc) {
        this.lxmc = lxmc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VotingtypeEntity that = (VotingtypeEntity) o;
        return id == that.id &&
                tplx == that.tplx &&
                Objects.equals(lxmc, that.lxmc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tplx, lxmc);
    }
}
