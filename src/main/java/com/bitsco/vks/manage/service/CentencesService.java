package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Centences;

import java.util.List;

public interface CentencesService {

    List<Centences> getList(Centences centences) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
