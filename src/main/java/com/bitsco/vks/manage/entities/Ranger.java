package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.LST_RANGER)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ranger extends BaseEntity {
    @Column(name = "RANGID")
    @Id
    private String rangId;

    @Column(name = "NAME")
    private String rangName;

    @Column(name = "ADDR")
    private String address;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "SPCID")
    private String spcId;

    @Column(name = "SPCNAME")
    private String spcName;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "SPPNAME")
    private String sppName;

    @Column(name = "LOCAID")
    private String locaId;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "SHORTNAME")
    private String shortName;

    @Column(name = "LOCANAME")
    private String locaName;

    @Column(name = "RANGCODE")
    private String rangCode;

    @Column(name = "RANGLEVEL")
    private String rangLevel;

    @Column(name = "RANGPARENT")
    private String rangParent;

    @Column(name = "ORDERCODE")
    private String orderCode;
}
