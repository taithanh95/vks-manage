package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.GroupLaw;

import java.util.List;

public interface GroupLawService {
    List<GroupLaw> getList(GroupLaw code) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
