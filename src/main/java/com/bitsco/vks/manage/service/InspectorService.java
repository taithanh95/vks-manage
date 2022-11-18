package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Inspector;

import java.util.List;

public interface InspectorService {

    List<Inspector> getList(Inspector inspector) throws Exception;

    List<Inspector> getListByUsername(Inspector inspector) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
