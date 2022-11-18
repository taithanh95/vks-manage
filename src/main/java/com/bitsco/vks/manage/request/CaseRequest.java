package com.bitsco.vks.manage.request;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseRequest extends BaseRequest {
    private String caseCode;
    private String caseName;
    private String sppId;
    private String firstAccusedCode;
    private String firstAccusedName;
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date fromBeginIndate;
    @JsonFormat(pattern = Constant.DATE.FORMAT.DATE, timezone = "Asia/Ho_Chi_Minh")
    private Date toBeginIndate;
    private String status;
    private String beginSetnum;
    private int page;
    private int size;
}
