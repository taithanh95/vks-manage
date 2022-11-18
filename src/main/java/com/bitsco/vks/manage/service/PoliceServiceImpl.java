package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Police;
import com.bitsco.vks.manage.repository.PoliceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PoliceServiceImpl implements PoliceService {

    @Autowired
    PoliceRepository policeRepository;

    @Override
    public List<Police> getList(Police police) throws Exception {
        List<Police> list = policeRepository.getList(
                police.getPoliceId(),
                StringCommon.isNullOrBlank(police.getName()) ? null : StringCommon.addLikeRightAndLeft(police.getName()),
                StringCommon.isNullOrBlank(police.getAddress()) ? null : StringCommon.addLikeRightAndLeft(police.getAddress()),
                police.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Police request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Police.class);
        List<Police> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

