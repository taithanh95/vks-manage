package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Centences;
import com.bitsco.vks.manage.repository.CentencesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CentencesServiceImpl implements CentencesService {
    @Autowired
    CentencesRepository centencesRepository;

    @Override
    public List<Centences> getList(Centences centences) throws Exception {
        List<Centences> list = centencesRepository.getList(
                StringCommon.isNullOrBlank(centences.getStatus()) ? null : centences.getStatus(),
                StringCommon.isNullOrBlank(centences.getCaseCode()) ? null : centences.getCaseCode().trim()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND, Response.DATA_NOT_FOUND.getResponseMessage());
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Centences request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Centences.class);
        List<Centences> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}
