package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Customs;
import com.bitsco.vks.manage.repository.CustomsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomsServiceImpl implements CustomsService {

    @Autowired
    CustomsRepository customsRepository;

    @Override
    public List<Customs> getList(Customs customs) throws Exception {
        List<Customs> list = customsRepository.getList(
                customs.getCustomId(),
                StringCommon.isNullOrBlank(customs.getCustomName()) ? null : StringCommon.addLikeRightAndLeft(customs.getCustomName()),
                StringCommon.isNullOrBlank(customs.getAddress()) ? null : StringCommon.addLikeRightAndLeft(customs.getAddress()),
                customs.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Customs request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Customs.class);
        List<Customs> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

