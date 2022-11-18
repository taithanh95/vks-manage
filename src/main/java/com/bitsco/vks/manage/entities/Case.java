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
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.SPP_CASE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Case extends BaseEntity {
    @Id
    @Column(name = "CASECODE")
    private String caseCode;

    @Column(name = "CASENAME")
    private String caseName;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CASETYPE")
    private String caseType;

    @Column(name = "REMARK")
    private String remark;

    @Column(name = "SPCCASECODE")
    private String spcCaseCode;

    @Column(name = "CRIMWHERE")
    private String crimWhere;

    @Column(name = "CRIMDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date crimDate;

    @Column(name = "CRIMTIME")
    private String crimTime;

    @Column(name = "ORISPPID")
    private String oriSppId;

    @Column(name = "BEGIN_SETNUM")
    private String beginSetnum;

    @Column(name = "BEGIN_INDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date beginIndate;

    @Column(name = "BEGIN_SPP")
    private String beginSpp;

    @Column(name = "BEGIN_SPC")
    private String beginSpc;

    @Column(name = "BEGIN_POL")
    private String beginPol;

    @Column(name = "LAWCODE")
    private String lawCode;

    @Column(name = "FIRSTACC")
    private String firstAcc;

    @Column(name = "ALIAS")
    private String alias;

    @Column(name = "LAWID")
    private String lawId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STATUSDATE")
    private String statusDate;

    @Column(name = "CRIMDATE1")
    private String crimDate1;

    @Column(name = "CRIMWHERE1")
    private String crimWhere1;

    @Column(name = "AUTOLAW")
    private String autoLaw;

    @Column(name = "BEGIN_OFFICE")
    private String beginOffice;

    @Column(name = "BEGIN_OFFICEID")
    private String beginOfficeId;

    @Column(name = "SYNC")
    private Integer sync;

    @Column(name = "CASEISNEW")
    private String caseIsnew;

    @Column(name = "GHIHINH")
    private String ghiHinh;

    @Column(name = "DIENTHOAI")
    private String dienThoai;

    @Column(name = "KHAM_NGHIEM_HIEN_TRUONG")
    private Integer knht;

    @Column(name = "NHAN_DANG")
    private Integer nhanDang;

    @Column(name = "KHAM_XET")
    private Integer khamXet;

    @Column(name = "KHAM_NGHIEM_TU_THI")
    private Integer kntt;

    @Column(name = "NHAN_BIET_GIONG_NOI")
    private Integer nbgn;

    @Column(name = "THUC_NGHIEM_DIEU_TRA")
    private Integer tndt;

    @Column(name = "DOI_CHAT")
    private Integer doiChat;

    @Column(name = "VKS_Y_C_KHOI_TO")
    private String ycKhoiTo;

    @Column(name = "TT_HOI_CUNG")
    private Integer ttHoiCung;

    @Column(name = "TT_LK_NBD_DS")
    private Integer ttLkNbdDs;

    @Column(name = "TG_HOI_CUNG")
    private Integer tgHoiCung;

    @Column(name = "TT_LK_BB_TG")
    private Integer ttLkBbTg;

    @Column(name = "LAST_TIME")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date lastTime;

    @Column(name = "THAM_NHUNG")
    private Integer corruption;

    @Column(name = "TT_LK_NLC")
    private Integer ttLkNlc;

    @Column(name = "TT_LK_NBH")
    private Integer ttLkNbh;

    @Column(name = "TG_LK")
    private Integer tgLk;

    @Column(name = "TT_LK_NBB_NTG_NLC_NBH")
    private Integer ttLkNbbNtgNlcNbh;

    @Column(name = "KHAM_NGHIEM_HIEN_TRUONG_KO")
    private Integer khamNghiemHienTruongKo;

    @Column(name = "KHAM_NGHIEM_TU_THI_KO")
    private Integer khamNghiemTuThiKo;

    @Column(name = "DOI_CHAT_KO")
    private Integer doiChatKo;

    @Column(name = "NHAN_DANG_KO")
    private Integer nhanDangKo;

    @Column(name = "NHAN_BIET_GIONG_NOI_KO")
    private Integer nhanBietGiongNoiKo;

    @Column(name = "THUC_NGHIEM_DIEU_TRA_KO")
    private Integer thucNghiemDieuTraKo;

    @Column(name = "KHAM_XET_KO")
    private Integer khamXetKo;

    @Column(name = "MA_TUY")
    private Integer drug;

    @Column(name = "FROMDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE_TIME, timezone = "Asia/Ho_Chi_Minh")
    private Date fromDate;

    @Column(name = "SETTIME")
    private Integer setTime;

    @Column(name = "ESETTIME")
    private Integer eSetTime;

    @Column(name = "SIGNNAME")
    private String signName;

    @Column(name = "SIGNOFFICE")
    private String signOffice;

    @Column(name = "FINISHDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date finishDate;

    @Transient
    List<Accused> accusedList;

    @Transient
    Accused firstAccused;

    @Transient
    String firstAccusedName;

    @Transient
    String firstAccusedNameNoSign;

    @Transient
    String caseNameNoSign;

    @Transient
    Law law;

    public Case(String caseCode) {
        this.caseCode = caseCode;
    }

    public Case(String caseCode, String caseName, String beginSetnum, Date beginIndate, String firstAcc, String lawId, String lawCode) {
        this.caseCode = caseCode;
        this.caseName = caseName;
        this.beginSetnum = beginSetnum;
        this.beginIndate = beginIndate;
        this.firstAcc = firstAcc;
        this.lawId = lawId;
        this.lawCode = lawCode;
    }
}
