package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Army;

import java.util.List;

public interface ArmyService {
    List<Army> getList(Army army) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
