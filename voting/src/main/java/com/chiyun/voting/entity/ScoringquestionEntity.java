package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ApiModel
@Table(name = "scoringquestion", schema = "tpdf", catalog = "")
public class ScoringquestionEntity {

    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("活动id")
    private int hdid;
    @ApiModelProperty("打分对象")
    private String dx;
    @ApiModelProperty("对象描述")
    private String nr;

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
    @Column(name = "themeid")
    public int getHdid() {
        return hdid;
    }

    public void setHdid(int hdid) {
        this.hdid = hdid;
    }

    @Basic
    @Column(name = "name")
    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    @Basic
    @Column(name = "content")
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringquestionEntity that = (ScoringquestionEntity) o;
        return id == that.id &&
                hdid == that.hdid &&
                Objects.equals(dx, that.dx) &&
                Objects.equals(nr, that.nr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hdid, dx, nr);
    }
}
