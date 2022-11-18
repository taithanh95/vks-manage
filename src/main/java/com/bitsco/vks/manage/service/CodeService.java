package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Code;

import java.util.List;

public interface CodeService {
    List<Code> getList(Code code) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
