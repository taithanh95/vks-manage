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
@Table(name = Constant.TABLE.LST_POL)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pol extends BaseEntity {
    @Column(name = "POLID")
    @Id
    private String polId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDR")
    private String addr;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "FAX")
    private String fax;

    @Column(name = "DIRECTOR")
    private String director;

}
