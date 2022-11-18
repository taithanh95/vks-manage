package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Army;
import com.bitsco.vks.manage.repository.ArmyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArmyServiceImpl implements ArmyService {

    @Autowired
    ArmyRepository armyRepository;

    @Override
    public List<Army> getList(Army army) throws Exception {
        List<Army> list = armyRepository.getList(
                army.getArmyId(),
                StringCommon.isNullOrBlank(army.getArmyName()) ? null : StringCommon.addLikeRightAndLeft(army.getArmyName()),
                StringCommon.isNullOrBlank(army.getAddress()) ? null : StringCommon.addLikeRightAndLeft(army.getAddress()),
                army.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Army request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Army.class);
        List<Army> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }
}

