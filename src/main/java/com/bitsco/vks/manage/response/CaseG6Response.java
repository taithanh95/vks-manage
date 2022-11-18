package com.bitsco.vks.manage.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CaseG6Response {
    // Mã vụ án
    private String caseCode;
    // Tên vụ án
    private String caseName;
    // Mã thụ lý
    private String regiCode;
    // Người chấp hành án
    private String accuName;
    // Ngày ra bản án
    private Date indate;
    // Viện kiếm sát nhập bản án
    private String sppCentence;
    // Viện kiếm sát nhận ủy thác
    private String sppExecutrans;
    // Trạng thái
    private String status;
    // Lý do từ chối
    private String reasonRefuse;
}
