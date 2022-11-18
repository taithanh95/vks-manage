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
import com.bitsco.vks.manage.entities.Decision;
import com.bitsco.vks.manage.repository.DecisionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DecisionServiceImpl implements DecisionService {
    @Autowired
    DecisionRepository decisionRepository;

    @Autowired
    CacheService cacheService;

    @Override
    public List<Decision> findByUseForAndStatus(Decision decision) throws Exception {
        ValidateCommon.validateNullObject(decision.getUseFor(), "useFor");
        ValidateCommon.validateNullObject(decision.getStatus(), "status");
        List<Decision> decisionList = decisionRepository.findByUseForAndStatus(decision.getUseFor(), decision.getStatus());
        if (ArrayListCommon.isNullOrEmpty(decisionList)) throw new CommonException(Response.DATA_NOT_FOUND);
        return decisionList;
    }


    @Override
    public List<Decision> getListForDropbox(Decision decision) throws Exception {
        List<Decision> decisionList = decisionRepository.getListForDropbox(
                StringCommon.isNullOrBlank(decision.getDeciId()) ? null : decision.getDeciId(),
                StringCommon.isNullOrBlank(decision.getName()) ? null : StringCommon.addLikeRightAndLeft(decision.getName()).toUpperCase(),
                StringCommon.isNullOrBlank(decision.getApplyFor()) ? null : decision.getApplyFor().trim(),
                decision.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(decisionList))
            throw new CommonException(Response.DATA_NOT_FOUND, Response.DATA_NOT_FOUND.getResponseMessage());
        return decisionList;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Decision request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Decision.class);
        List<Decision> list = getListForDropbox(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }

    @Override
    public Decision findById(Decision decision) throws Exception {
        ValidateCommon.validateNullObject(decision.getDeciId(), "deciId");
        Decision response = cacheService.getDecisionFromCache(decision.getDeciId());
        if (response != null) return response;
        response = decisionRepository.findById(decision.getDeciId()).orElse(null);
        if (response == null) throw new CommonException(Response.OBJECT_NOT_FOUND);
        else cacheService.addDecision2RedisCache(response);
        return response;
    }

    @Override
    public List<Decision> findByStatus(Decision decision) throws Exception {
        List<Decision> decisionList = decisionRepository.findByStatus(
                decision.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(decisionList))
            throw new CommonException(Response.DATA_NOT_FOUND, Response.DATA_NOT_FOUND.getResponseMessage());
        return decisionList;
    }
}
