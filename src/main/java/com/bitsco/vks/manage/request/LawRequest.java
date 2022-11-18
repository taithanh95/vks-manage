package com.bitsco.vks.manage.request;

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
public class LawRequest {
    private Integer rowIndex;
    private Integer pageSize;
    private String sortField;
    private String sortOrder;
    private String lawId;
    private String item;
    private String point;
    private String lawName;
    private String groupId;
    private String codeId;
}
