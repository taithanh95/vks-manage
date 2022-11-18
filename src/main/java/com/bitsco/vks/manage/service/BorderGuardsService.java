package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.BorderGuards;

import java.util.List;

public interface BorderGuardsService {
    List<BorderGuards> getList(BorderGuards borderGuards) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
