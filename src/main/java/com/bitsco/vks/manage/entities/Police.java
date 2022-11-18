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
@Table(name = Constant.TABLE.LST_POLICE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Police extends BaseEntity {
    @Column(name = "POLICEID")
    @Id
    private String policeId;

    @Column(name = "NAME")
    private String name;

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

    @Column(name = "POLICECODE")
    private String policeCode;

    @Column(name = "POLICELEVEL")
    private String policeLevel;

    @Column(name = "POLICEPARENT")
    private String policeParent;

    @Column(name = "ORDERCODE")
    private String oderCode;
}
