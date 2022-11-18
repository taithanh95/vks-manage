package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.cache.CacheService;
import com.bitsco.vks.manage.entities.Inspector;
import com.bitsco.vks.manage.repository.InspectorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InspectorServiceImpl implements InspectorService {
    @Autowired
    InspectorRepository inspectorRepository;

    @Autowired
    CacheService cacheService;


    @Override
    public List<Inspector> getList(Inspector inspector) throws Exception {
        return inspectorRepository.getList(
                StringCommon.isNullOrBlank(inspector.getInspCode()) ? null : inspector.getInspCode(),
                StringCommon.isNullOrBlank(inspector.getFullName()) ? null : StringCommon.addLikeRightAndLeft(inspector.getFullName()),
                inspector.getStatus()
        );
    }

    @Override
    public List<Inspector> getListByUsername(Inspector inspector) throws Exception {
        inspector.setUsername(cacheService.getUsernameFromHeader());
        return inspectorRepository.getListByUsername(
                StringCommon.isNullOrBlank(inspector.getInspCode()) ? null : inspector.getInspCode(),
                StringCommon.isNullOrBlank(inspector.getFullName()) ? null : StringCommon.addLikeRightAndLeft(inspector.getFullName()),
                inspector.getStatus(),
                inspector.getUsername()
        );
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Inspector request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Inspector.class);
        List<Inspector> list = getListByUsername(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}
