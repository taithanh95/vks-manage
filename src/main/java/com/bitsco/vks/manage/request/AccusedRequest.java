package com.bitsco.vks.manage.request;

import com.bitsco.vks.common.request.BaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccusedRequest extends BaseRequest {
    private String caseCode;
    private String caseName;
    private String accuCode;
    private String fullName;
    private String beginSetnum;
    private String sppId;
}
