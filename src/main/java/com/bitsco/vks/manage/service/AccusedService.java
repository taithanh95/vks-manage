package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Accused;
import com.bitsco.vks.manage.request.AccusedRequest;
import com.bitsco.vks.manage.response.AccusedResponse;

import java.util.List;

public interface AccusedService {

    List<AccusedResponse> getListComboBox(AccusedRequest accusedRequest) throws Exception;

    PageResponse getPageComboBox(PageRequest pageRequest) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;

    Accused findById(Accused accused) throws Exception;

    Accused detail(Accused accused) throws Exception;

    List<Accused> findByCaseCode(Accused accused) throws Exception;
}
