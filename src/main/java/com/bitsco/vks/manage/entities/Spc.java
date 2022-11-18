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
@Table(name = Constant.TABLE.LST_SPC)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Spc extends BaseEntity {
    @Column(name = "SPCID")
    @Id
    private String spcId;

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

    @Column(name = "SPCCODE")
    private String spcCode;

    @Column(name = "SPCLEVEL")
    private String spcLevel;

    @Column(name = "SPCPARENT")
    private String spcParent;

    @Column(name = "ORDERCODE")
    private String orderCode;

    @Column(name = "LOCAID")
    private String locaId;
}
