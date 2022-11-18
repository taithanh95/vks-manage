package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Decision;

import java.util.List;

public interface DecisionService {
    List<Decision> findByUseForAndStatus(Decision decision) throws Exception;

    List<Decision> getListForDropbox(Decision decision) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;

    Decision findById(Decision decision) throws Exception;

    List<Decision> findByStatus(Decision decision) throws Exception;
}
