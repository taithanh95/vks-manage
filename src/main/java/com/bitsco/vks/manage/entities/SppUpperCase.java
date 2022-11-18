package com.bitsco.vks.manage.entities;

import com.bitsco.vks.common.constant.Constant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = Constant.TABLE.LST_SPP)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SppUpperCase {
    @Column(name = "SPPID")
    @Id
    @JsonProperty("SPPID")
    private String SPPID;

    @JsonProperty("NAME")
    @Column(name = "NAME")
    private String NAME;

    @JsonProperty("ADDRESS")
    @Column(name = "ADDR")
    private String ADDRESS;

    @JsonProperty("TEL")
    @Column(name = "TEL")
    private String TEL;

    @JsonProperty("FAX")
    @Column(name = "FAX")
    private String FAX;

    @JsonProperty("DIRECTOR")
    @Column(name = "DIRECTOR")
    private String DIRECTOR;

    @JsonProperty("STATUS")
    @Column(name = "STATUS")
    private String STATUS;

    @JsonProperty("SPCID")
    @Column(name = "SPCID")
    private String SPCID;

    @JsonProperty("SPCNAME")
    @Column(name = "SPCNAME")
    private String SPCNAME;

    @JsonProperty("POLID")
    @Column(name = "POLID")
    private String POLID;

    @JsonProperty("POLNAME")
    @Column(name = "POLNAME")
    private String POLNAME;

    @JsonProperty("LOCAID")
    @Column(name = "LOCAID")
    private String LOCAID;

    @JsonProperty("POSITION")
    @Column(name = "POSITION")
    private String POSITION;

    @JsonProperty("SHORTNAME")
    @Column(name = "SHORTNAME")
    private String SHORTNAME;

    @JsonProperty("LOCANAME")
    @Column(name = "LOCANAME")
    private String LOCANAME;

    @JsonProperty("SPPCODE")
    @Column(name = "SPPCODE")
    private String SPPCODE;

    @JsonProperty("SPPLEVEL")
    @Column(name = "SPPLEVEL")
    private String SPPLEVEL;

    @JsonProperty("SPPPARENT")
    @Column(name = "SPPPARENT")
    private String SPPPARENT;

    @JsonProperty("ORDERCODE")
    @Column(name = "ORDERCODE")
    private String ORDERCODE;

    @JsonProperty("ISDEPART")
    @Column(name = "ISDEPART")
    private String ISDEPART;

    @JsonProperty("SPPIDFOX")
    @Column(name = "SPPIDFOX")
    private String SPPIDFOX;

    @JsonProperty("NAMESYNC")
    @Column(name = "NAME_SYNC")
    private String NAMESYNC;
}
