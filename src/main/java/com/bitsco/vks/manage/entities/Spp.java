package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.LST_SPP)
@Where(clause = "status = 'Y'")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Spp extends BaseEntity {
    @Column(name = "SPPID")
    @Id
    private String sppId;
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
    @Column(name = "POLID")
    private String polId;
    @Column(name = "POLNAME")
    private String polName;
    @Column(name = "LOCAID")
    private String locaId;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "SHORTNAME")
    private String shortName;
    @Column(name = "LOCANAME")
    private String locaName;
    @Column(name = "SPPCODE")
    private String sppCode;
    @Column(name = "SPPLEVEL")
    private String sppLevel;
    @Column(name = "SPPPARENT")
    private String sppParent;
    @Column(name = "ORDERCODE")
    private String oderCode;
    @Column(name = "ISDEPART")
    private String isDePart;
    @Column(name = "SPPIDFOX")
    private String sppIdFOX;
    @Column(name = "NAME_SYNC")
    private String nameSync;

    @Transient
    private List<Spp> children;
}
