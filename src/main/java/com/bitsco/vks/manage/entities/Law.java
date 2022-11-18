package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.StringCommon;
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
@Table(name = Constant.TABLE.LST_LAW)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Law extends BaseEntity {
    @Column(name = "LAWCODE")
    @Id
    private String lawCode;

    @Column(name = "LAWID")
    private String lawId;

    @Column(name = "ITEM")
    private String item;

    @Column(name = "POINT")
    private String point;

    @Column(name = "LAWNAME")
    private String lawName;

    @Column(name = "LAWDATE")
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date lawDate;

    @Column(name = "PRIORITY")
    private Long priority;

    @Column(name = "SETORDER")
    private Long setorder;

    @Column(name = "GROUPID")
    private String groupId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CODEID")
    private String codeId;

    @Column(name = "LAWTYPE")
    private String lawType;

    @Column(name = "LAWCODEPARRENT")
    private String lawCodeParrent;

    @Column(name = "SYNC")
    private Integer sync;

    @Transient
    private String fullName;

    public Law coppyFrom(Law law) {
        if (!StringCommon.isNullOrBlank(law.getLawCode())) this.setLawCode(law.getLawCode().trim());
        if (!StringCommon.isNullOrBlank(law.getLawName())) this.setLawName(law.getLawName());
        if (law.getStatus() != null) this.setStatus(law.getStatus());
        return this;
    }

    public Law(String lawCode, String fullName) {
        this.lawCode = lawCode;
        this.fullName = fullName;
    }

    public Law(String lawId, String lawCode, String lawName) {
        this.lawId = lawId;
        this.lawCode = lawCode;
        this.lawName = lawName;
    }

    public Law(String lawCode, String lawId, String item, String point, String lawName) {
        this.lawCode = lawCode;
        this.lawId = lawId;
        this.item = item;
        this.point = point;
        this.lawName = lawName;
    }
}
