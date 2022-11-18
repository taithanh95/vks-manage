package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Ranger;

import java.util.List;

public interface RangerService {
    List<Ranger> getList(Ranger ranger) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
