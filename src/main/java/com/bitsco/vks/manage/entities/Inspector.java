package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = Constant.TABLE.LST_INSPECTOR)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inspector extends BaseEntity {
    @Id
    @Column(name = "INSPCODE")
    private String inspCode;

    @Column(name = "SPPID")
    private String sppId;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "JOBTITLE")
    private String jobTitle;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "POSITION")
    private String position;

    @Column(name = "SYNC")
    private Integer sync;

    @Transient
    private String username;
}
