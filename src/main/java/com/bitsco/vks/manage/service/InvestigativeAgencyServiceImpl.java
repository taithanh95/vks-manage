package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Accused;
import com.bitsco.vks.manage.entities.InvestigativeAgency;
import com.bitsco.vks.manage.repository.InvestigativeAgencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Tai Thanh <taithanh95.dev@gmail.com>
 * Date: 12/10/2022
 * Time: 3:43 PM
 */

@Service
@Transactional
public class InvestigativeAgencyServiceImpl implements InvestigativeAgencyService {
    @Autowired
    InvestigativeAgencyRepository investigativeAgencyRepository;

    @Override
    public List<InvestigativeAgency> getList(InvestigativeAgency investigativeAgency) throws Exception {
        return investigativeAgencyRepository.getList(investigativeAgency.getInvesCode(), investigativeAgency.getName());
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        InvestigativeAgency request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), InvestigativeAgency.class);
        List<InvestigativeAgency> list = investigativeAgencyRepository.getList(
                StringCommon.isNullOrBlank(request.getInvesCode()) ? null : request.getInvesCode(),
                StringCommon.isNullOrBlank(request.getName()) ? null : StringCommon.addLikeRightAndLeft(request.getName())
        );
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(list, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}
