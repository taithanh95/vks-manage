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
@Table(name = Constant.TABLE.LST_DECISION)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Decision extends BaseEntity {
    @Column(name = "DECIID")
    @Id
    private String deciId;
    @Column(name = "DECINAME")
    private String name;
    @Column(name = "APPLYFOR")
    private String applyFor;
    @Column(name = "SETTIME")
    private Long setTime;
    @Column(name = "SETUNIT")
    private String setUnit;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "APPLYFINISH")
    private String applyFinish;
    @Column(name = "USERFOR")
    private String useFor;
    @Column(name = "DECITYPEID")
    private String deciTypeId;
    @Column(name = "DECILEVEL")
    private String decLevel;
    @Column(name = "TIMELIMIT")
    private String timeLimit;
    @Column(name = "CONTENT")
    private String content;

    public Decision(String deciId, String name) {
        this.deciId = deciId;
        this.name = name;
    }
}
