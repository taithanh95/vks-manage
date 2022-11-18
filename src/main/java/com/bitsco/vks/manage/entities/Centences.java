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
@Table(name = Constant.TABLE.SPP_CENTENCE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Centences extends BaseEntity {
    // Bảng bản án của vụ án
    @Column(name = "CENTCODE")
    @Id
    private String centCode;

    @Column(name = "SETNUM")
    private String setNum;

    @Column(name = "INDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date inDate;

    @Column(name = "ACTDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date actDate;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SIGNNAME")
    private String signName;

    @Column(name = "SIGNOFFICE")
    private String signOffice;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "REGICODE")
    private String regiCode;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "CONCID")
    private String concid;

    @Column(name = "MOVEMENT")
    private String movement;

    @Column(name = "USERFOR")
    private String stage;

    @Column(name = "SPCDIFFSPP")
    private Integer spcDiffSpp;

    @Column(name = "SPPNEXT")
    private Integer sppNext;

    @Column(name = "SPPBACK")
    private Integer sppBack;

    @Column(name = "CASECODE")
    private String caseCode;

    @Column(name = "RECEIVEDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date receiveDate;

    @Column(name = "CONCIDSPP")
    private String concidSpp;

    @Column(name = "SPCID")
    private String spcId;

    @Column(name = "CENTTYPE")
    private String centType;

    @Column(name = "SYNC")
    private Integer sync;

    @Column(name = "RQTRANSFER")
    private String rqTransfer;

    @Column(name = "XETXURUTKN")
    private String xetXuRutKn;

    @Column(name = "XETXURUTGON")
    private String xetXuRutGon;

    @Column(name = "THIETHAITAISAN")
    private Integer thietHaiTaiSan;

    @Column(name = "THIETHAITHAMNHUNG")
    private Integer thietHaiThamNhung;

    @Column(name = "LAST_TIME")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date lastTime;

    @Column(name = "LAWCASE")
    private String lawCase;

    @Column(name = "XETXURUTKNVKS")
    private String xetXuRutKnVks;

    @Column(name = "SPPEQ")
    private Integer sppEq;

    @Column(name = "UUID")
    private String uuid;
}
