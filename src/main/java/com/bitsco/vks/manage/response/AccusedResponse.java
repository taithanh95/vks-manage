package com.bitsco.vks.manage.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccusedResponse {
    private String accuCode;
    private String fullName;
    private Date birthDay;
    private String sex;
    private String lawId;
    private String lawName;
    private String item;
    private String point;
    private String caseCode;
    private String caseName;
    private Date beginIndate;
    private String beginSetnum;
}
