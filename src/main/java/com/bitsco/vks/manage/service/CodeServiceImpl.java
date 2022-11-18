package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.manage.entities.Code;
import com.bitsco.vks.manage.repository.CodeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodeServiceImpl implements CodeService {

    @Autowired
    CodeRepository codeRepository;

    @Override
    public List<Code> getList(Code code) throws Exception {
        List<Code> codeList = codeRepository.getList(code.getStatus());
        if (ArrayListCommon.isNullOrEmpty(codeList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return codeList;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Code request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Code.class);
        List<Code> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}
