package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Police;

import java.util.List;

public interface PoliceService {
    List<Police> getList(Police police) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
