package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.SPP_ACCUSED)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Accused extends BaseEntity {
    @Id
    @Column(name = "ACCUCODE")
    private String accuCode;

    @Column(name = "CASECODE")
    private String caseCode;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "OTHERNAME")
    private String otherName;

    @Column(name = "ALIASNAME")
    private String aliasName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "LOCAID")
    private String locaId;

    @Column(name = "BIRTHDAY")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date birthDay;

    @Column(name = "SEX")
    private String sex;

    @Column(name = "IDENTIFY")
    private String identify;

    @Column(name = "IDENTIFYD")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date identifyD;

    @Column(name = "IDENTIFYW")
    private String identifyW;

    @Column(name = "INPARTY")
    private String inParty;

    @Column(name = "NATIID")
    private String natiId;

    @Column(name = "RELIGION")
    private String religion;

    @Column(name = "HEROIN")
    private String heroin;

    @Column(name = "REPEATER")
    private String repeater;

    @Column(name = "CONVICTION")
    private Integer conviction;

    @Column(name = "OFFENCE")
    private Integer offence;

    @Column(name = "COUNID")
    private String counId;

    @Column(name = "OCCUID")
    private String occuId;

    @Column(name = "LEVELID")
    private String levelId;

    @Column(name = "OFFICEID")
    private String officeId;

    @Column(name = "PARTYID")
    private String partyId;

    @Column(name = "CRIMWHERE")
    private String crimWhere;

    @Column(name = "CRIMDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date crimDate;

    @Column(name = "CRIMTIME")
    private String crimTime;

    @Column(name = "WANDER")
    private String wander;

    @Column(name = "AGE")
    private Integer age;

    @Column(name = "LAWCODE")
    private String lawCode;

    @Column(name = "G3_PNT")
    private String g3Pnt;

    @Column(name = "G4_PNT")
    private String g4Pnt;

    @Column(name = "BEGIN_SETNUM")
    private String beginSetnum;

    @Column(name = "BEGIN_INDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE_TIME, timezone = "Asia/Ho_Chi_Minh")
    private Date beginIndate;

    @Column(name = "BEGIN_SPP")
    private String beginSpp;

    @Column(name = "BEGIN_SPC")
    private String beginSpc;

    @Column(name = "BEGIN_POL")
    private String beginPol;

    @Column(name = "UNOCCUPATION")
    private String unoccupation;

    @Column(name = "FIRSTACC")
    private String firstAcc;

    @Column(name = "OCCUTELER")
    private String occuTeler;

    @Column(name = "OCCUDISHONEST")
    private String occuDishonest;

    @Column(name = "OCCUREEDUCATE")
    private String occuReeducate;

    @Column(name = "OCCUSOLDIER")
    private String occuSoldier;

    @Column(name = "OCCUSTUDENT")
    private String occuStudent;

    @Column(name = "OCCUOFFICER")
    private String occuOfficer;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STATUSDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date statusDate;

    @Column(name = "LAWID")
    private String lawId;

    @Column(name = "ADDRNAME")
    private String addrName;

    @Column(name = "LOCANAME")
    private String locaName;

    @Column(name = "BDAY")
    private String bDay;

    @Column(name = "BMONTH")
    private String bMonth;

    @Column(name = "BYEAR")
    private String bYear;

    @Column(name = "BEGIN_OFFICE")
    private String beginOffice;

    @Column(name = "BEGIN_OFFICEID")
    private String beginOfficeId;

    @Column(name = "SYNC")
    private Integer sync;

    @Column(name = "LEGALPER")
    private String legalper;

    @Column(name = "BAOCHUA")
    private String baoChua;

    @Column(name = "BCVND")
    private String bcvnd;

    @Column(name = "TGVPL")
    private String tgvpl;

    @Column(name = "LAST_TIME")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE_TIME, timezone = "Asia/Ho_Chi_Minh")
    private Date lastTime;

    @Column(name = "THAM_NHUNG")
    private Integer corruption;

    @Column(name = "MA_TUY")
    private Integer drug;

    @Transient
    Case cCase;

    @Transient
    Law law;

    public Accused(String accuCode) {
        this.accuCode = accuCode;
    }

    public Accused(String accuCode, String fullName) {
        this.accuCode = accuCode;
        this.fullName = fullName;
    }
}
