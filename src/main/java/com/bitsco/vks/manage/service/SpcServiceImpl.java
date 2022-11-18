package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Spc;
import com.bitsco.vks.manage.repository.SpcRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SpcServiceImpl implements SpcService {

    @Autowired
    SpcRepository spcRepository;

    @Override
    public List<Spc> getList(Spc spc) throws Exception {
        List<Spc> list = spcRepository.getList(
                spc.getSpcId(),
                StringCommon.isNullOrBlank(spc.getName()) ? null : StringCommon.addLikeRightAndLeft(spc.getName()),
                StringCommon.isNullOrBlank(spc.getAddress()) ? null : StringCommon.addLikeRightAndLeft(spc.getAddress())
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Spc request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Spc.class);
        List<Spc> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

