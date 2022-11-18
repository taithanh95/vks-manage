package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.InvestigativeAgency;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Tai Thanh <taithanh95.dev@gmail.com>
 * Date: 12/10/2022
 * Time: 3:43 PM
 */
public interface InvestigativeAgencyService {

    List<InvestigativeAgency> getList(InvestigativeAgency investigativeAgency) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;
}
