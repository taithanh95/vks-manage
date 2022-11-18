package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Pol;
import com.bitsco.vks.manage.repository.PolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PolServiceImpl implements PolService {
    @Autowired
    PolRepository polRepository;
    
    @Override
    public List<Pol> getList(Pol Pol) throws Exception {
        List<Pol> list = polRepository.getList(
                StringCommon.isNullOrBlank(Pol.getName()) ? null : StringCommon.addLikeRightAndLeft(Pol.getName())
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }
}
