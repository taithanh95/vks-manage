package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Spc;

import java.util.List;

public interface SpcService {
    List<Spc> getList(Spc spc) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
