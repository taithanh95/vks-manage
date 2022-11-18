package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Ranger;
import com.bitsco.vks.manage.repository.RangerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RangerServiceImpl implements RangerService {

    @Autowired
    RangerRepository rangerRepository;

    @Override
    public List<Ranger> getList(Ranger ranger) throws Exception {
        List<Ranger> list = rangerRepository.getList(
                ranger.getRangId(),
                StringCommon.isNullOrBlank(ranger.getRangName()) ? null : StringCommon.addLikeRightAndLeft(ranger.getRangName()),
                StringCommon.isNullOrBlank(ranger.getAddress()) ? null : StringCommon.addLikeRightAndLeft(ranger.getAddress()),
                ranger.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Ranger request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Ranger.class);
        List<Ranger> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

