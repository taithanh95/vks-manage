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
@Table(name = Constant.TABLE.LST_CODE)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Code extends BaseEntity {
    @Column(name = "CODEID")
    @Id
    private String codeId;

    @Column(name = "CODENAME")
    private String codeName;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CODEYEAR")
    private Long codeYear;

    @Column(name = "CODETYPE")
    private String codeType;
}
