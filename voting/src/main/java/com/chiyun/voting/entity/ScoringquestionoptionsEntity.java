package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ApiModel
@Table(name = "scoringquestionoptions", schema = "tpdf", catalog = "")
public class ScoringquestionoptionsEntity {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("活动id")
    private int hdid;
    @ApiModelProperty("最低分")
    private int zdf;
    @ApiModelProperty("最高分")
    private int zgf;
    @ApiModelProperty(value = "打分项", required = true)
    private String mc;
    @ApiModelProperty("打分项描述")
    private String mcms;

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
    @Column(name = "scorelow")
    public int getZdf() {
        return zdf;
    }

    public void setZdf(int zdf) {
        this.zdf = zdf;
    }

    @Basic
    @Column(name = "scorehign")
    public int getZgf() {
        return zgf;
    }

    public void setZgf(int zgf) {
        this.zgf = zgf;
    }

    @Basic
    @Column(name = "scorename")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "scoredesc")
    public String getMcms() {
        return mcms;
    }

    public void setMcms(String mcms) {
        this.mcms = mcms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoringquestionoptionsEntity that = (ScoringquestionoptionsEntity) o;
        return id == that.id &&
                hdid == that.hdid &&
                zdf == that.zdf &&
                zgf == that.zgf;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hdid, zdf, zgf);
    }
}
