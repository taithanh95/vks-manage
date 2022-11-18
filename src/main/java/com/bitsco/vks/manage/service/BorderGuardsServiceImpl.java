package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.BorderGuards;
import com.bitsco.vks.manage.repository.BorderGuardsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorderGuardsServiceImpl implements BorderGuardsService {

    @Autowired
    BorderGuardsRepository borderGuardsRepository;

    @Override
    public List<BorderGuards> getList(BorderGuards borderGuards) throws Exception {
        List<BorderGuards> list = borderGuardsRepository.getList(
                borderGuards.getBorguaId(),
                StringCommon.isNullOrBlank(borderGuards.getBorguaName()) ? null : StringCommon.addLikeRightAndLeft(borderGuards.getBorguaName()),
                StringCommon.isNullOrBlank(borderGuards.getAddress()) ? null : StringCommon.addLikeRightAndLeft(borderGuards.getAddress()),
                borderGuards.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        BorderGuards request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), BorderGuards.class);
        List<BorderGuards> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

