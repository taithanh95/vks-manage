package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.SPP_REGISTER)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Register extends BaseEntity {
    @Id
    @Column(name = "REGICODE")
    private String regiCode;

    @Column(name = "CASECODE")
    private String caseCode;

    @Column(name = "CASENAME")
    private String caseName;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "SETNUM")
    private String setNum;

    @Column(name = "INDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date inDate;

    @Column(name = "FROMDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date fromDate;

    @Column(name = "TODATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date toDate;

    @Column(name = "SPECIAL")
    private String special;

    @Column(name = "USERFOR")
    private String stage;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "FINISHDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date finishDate;

    @Column(name = "LAWCODE")
    private String lawcode;

    @Column(name = "CASETYPE")
    private String caseType;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "UNSPECIAL")
    private String unSpecial;

    @Column(name = "SPPSPC")
    private String sppSpc;

    @Column(name = "APPECODE")
    private String appeCode;

    @Column(name = "AGAINSTCODE")
    private String againstCode;

    @Column(name = "STRMDECIID")
    private String strmdeciId;

    @Column(name = "TRANSFER")
    private String transfer;

    @Column(name = "TRANSFEROUT")
    private String transferOut;

    @Column(name = "STATUSREASON")
    private String statusReason;

    @Column(name = "ACCUCODE")
    private String accuCode;

    @Column(name = "BREGICODE")
    private String bregiCode;

    @Column(name = "SPCSETNUM")
    private String spcSetnum;

    @Column(name = "SPCINDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date spcIndate;

    @Column(name = "LAWYER")
    private String lawyer;

    @Column(name = "SYNC")
    private Integer sync;

    // Luu thu ly thi hanh an gan nhat
    @Column(name = "BREGICODETHA")
    private String bregiCodeTha;

    @Column(name = "TGVPL")
    private String tgvpl;

    @Column(name = "BCVND")
    private String bcvnd;

    @Column(name = "LAST_TIME")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date lastTime;

    @Column(name = "UUID")
    private String uuid;
}
