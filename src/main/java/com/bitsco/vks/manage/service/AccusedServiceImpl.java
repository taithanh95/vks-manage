package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.common.validate.ValidateCommon;
import com.bitsco.vks.manage.cache.CacheService;
import com.bitsco.vks.manage.entities.Accused;
import com.bitsco.vks.manage.entities.Case;
import com.bitsco.vks.manage.repository.AccusedDAO;
import com.bitsco.vks.manage.repository.AccusedRepository;
import com.bitsco.vks.manage.request.AccusedRequest;
import com.bitsco.vks.manage.response.AccusedResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccusedServiceImpl implements AccusedService {
    @Autowired
    AccusedRepository accusedRepository;

    @Autowired
    AccusedDAO accusedDAO;

    @Autowired
    CaseService caseService;

    @Autowired
    CacheService cacheService;

    @Override
    public List<AccusedResponse> getListComboBox(AccusedRequest accusedRequest) throws Exception {
        accusedRequest.setUsername(cacheService.getUsernameFromHeader());
        List<AccusedResponse> accusedList = accusedDAO.getListAccused(accusedRequest);
        if (ArrayListCommon.isNullOrEmpty(accusedList))
            throw new CommonException(Response.DATA_NOT_FOUND, Response.DATA_NOT_FOUND.getResponseMessage());
        return accusedList;
    }

    @Override
    public PageResponse getPageComboBox(PageRequest pageRequest) throws Exception {
        AccusedRequest request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), AccusedRequest.class);
        request.setUsername(cacheService.getUsernameFromHeader());
        //Truy vấn dữ liệu vào pkg
        List<AccusedResponse> list = accusedDAO.getListAccused(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(list, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Accused request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Accused.class);
        List<Accused> list = accusedRepository.getList(
                StringCommon.isNullOrBlank(request.getSppId()) ? null : request.getSppId(),
                StringCommon.isNullOrBlank(request.getFullName()) ? null : StringCommon.addLikeRightAndLeft(request.getFullName()),
                StringCommon.isNullOrBlank(request.getCaseCode()) ? null : request.getCaseCode().trim(),
                request.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(list, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Override
    public Accused findById(Accused accused) throws Exception {
        ValidateCommon.validateNullObject(accused.getAccuCode(), "accuCode");
        Accused response = accusedRepository.findById(accused.getAccuCode()).orElse(null);
        if (response == null) throw new CommonException(Response.OBJECT_NOT_FOUND);
        else cacheService.addAccused2RedisCache(response);
        return response;
    }

    @Override
    public Accused detail(Accused accused) throws Exception {
        ValidateCommon.validateNullObject(accused.getAccuCode(), "accuCode");
        Accused response = findById(accused);
        if (!StringCommon.isNullOrBlank(response.getCaseCode()))
            response.setCCase(caseService.findById(new Case(response.getCaseCode())));
        if (!StringCommon.isNullOrBlank(response.getLawCode()))
            response.setLaw(cacheService.getLawFromCache(response.getLawCode()));
        return response;
    }

    @Override
    public List<Accused> findByCaseCode(Accused accused) throws Exception {
        ValidateCommon.validateNullObject(accused.getCaseCode(), "caseCode");
        List<Accused> list = accusedRepository.findByCaseCode(accused.getCaseCode());
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }
}
