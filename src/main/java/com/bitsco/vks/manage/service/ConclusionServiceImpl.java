package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.entities.Conclusion;
import com.bitsco.vks.manage.repository.ConclusionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConclusionServiceImpl implements ConclusionService {

    @Autowired
    ConclusionRepository conclusionRepository;

    @Override
    public List<Conclusion> getList(Conclusion conclusion) throws Exception {
        List<Conclusion> list = conclusionRepository.getList(
                conclusion.getConcId(),
                StringCommon.isNullOrBlank(conclusion.getContent()) ? null : StringCommon.addLikeRightAndLeft(conclusion.getContent()),
                conclusion.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

}

