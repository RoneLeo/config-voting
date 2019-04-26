package com.chiyun.voting.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@ApiModel
@Table(name = "theme", schema = "tpdf", catalog = "")
public class ThemeEntity {
    private int id;
    @ApiModelProperty(value = "活动标题", required = true)
    private String bt;
    @ApiModelProperty("活动内容")
    private String nr;
    @ApiModelProperty(value = "活动类型：0 投票， 1 打分", required = true)
    private int hdlx;
    @ApiModelProperty(value = "是否发布（0：未发布，1：已发布，2：投票结束）", required = true)
    private int sffb;
    @ApiModelProperty("开始时间")
    private Date kssj;
    @ApiModelProperty("结束时间")
    private Date jssj;
    @ApiModelProperty("是否匿名：0 匿名，1 实名")
    private int sfnm;

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
    @Column(name = "title")
    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    @Basic
    @Column(name = "content")
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    @Basic
    @Column(name = "vtype")
    public int getHdlx() {
        return hdlx;
    }

    public void setHdlx(int hdlx) {
        this.hdlx = hdlx;
    }

    @Basic
    @Column(name = "publish")
    public int getSffb() {
        return sffb;
    }

    public void setSffb(int sffb) {
        this.sffb = sffb;
    }

    @Basic
    @Column(name = "btime")
    public Date getKssj() {
        return kssj;
    }

    public void setKssj(Date kssj) {
        this.kssj = kssj;
    }

    @Basic
    @Column(name = "etime")
    public Date getJssj() {
        return jssj;
    }

    public void setJssj(Date jssj) {
        this.jssj = jssj;
    }

    @Basic
    @Column(name = "anonymous")
    public int getSfnm() {
        return sfnm;
    }

    public void setSfnm(int sfnm) {
        this.sfnm = sfnm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThemeEntity that = (ThemeEntity) o;
        return id == that.id &&
                hdlx == that.hdlx &&
                sffb == that.sffb &&
                sfnm == that.sfnm &&
                Objects.equals(bt, that.bt) &&
                Objects.equals(nr, that.nr) &&
                Objects.equals(kssj, that.kssj) &&
                Objects.equals(jssj, that.jssj);
    }

    List<VotingquestionEntity> votelist;
    List<ScoringquestionEntity> scorelist;
    List<ScoringquestionoptionsEntity> scoreoblist;

    @Transient
    public List<VotingquestionEntity> getVotelist() {
        return votelist;
    }

    public void setVotelist(List<VotingquestionEntity> votelist) {
        this.votelist = votelist;
    }

    @Transient
    public List<ScoringquestionEntity> getScorelist() {
        return scorelist;
    }

    public void setScorelist(List<ScoringquestionEntity> scorelist) {
        this.scorelist = scorelist;
    }

    @Transient
    public List<ScoringquestionoptionsEntity> getScoreoblist() {
        return scoreoblist;
    }

    public void setScoreoblist(List<ScoringquestionoptionsEntity> scoreoblist) {
        this.scoreoblist = scoreoblist;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bt, nr, hdlx, sffb, kssj, jssj, sfnm);
    }
}
