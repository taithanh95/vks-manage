package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.common.validate.ValidateCommon;
import com.bitsco.vks.manage.cache.CacheService;
import com.bitsco.vks.manage.entities.Law;
import com.bitsco.vks.manage.repository.LawDAO;
import com.bitsco.vks.manage.repository.LawRepository;
import com.bitsco.vks.manage.request.LawRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LawServiceImpl implements LawService {
    @Autowired
    LawRepository lawRepository;

    @Autowired
    CacheService cacheService;

    @Autowired
    LawDAO lawDAO;

    @Override
    public Law create(Law law) throws Exception {
        validateFields(law);
        law.setStatus(Constant.STATUS_OBJECT.ACTIVE_Y);
        return lawRepository.save(law);
    }

    @Override
    public Law update(Law law) throws Exception {
        Law old = lawRepository.findById(law.getLawId()).orElse(null);
        if (old == null)
            throw new CommonException(Response.OBJECT_NOT_FOUND);
        return lawRepository.save(old.coppyFrom(law));
    }

    @Override
    public List<Law> getList(Law law) throws Exception {
        List<Law> list = lawRepository.getList(
                StringCommon.isNullOrBlank(law.getLawName()) ? null : law.getLawId(),
                StringCommon.isNullOrBlank(law.getLawName()) ? null : StringCommon.addLikeRightAndLeft(law.getLawName()),
                StringCommon.isNullOrBlank(law.getCodeId()) ? null : law.getCodeId(),
                law.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public List<Law> search(LawRequest lawRequest) throws Exception {
        ValidateCommon.validateNullObject(lawRequest.getPageSize(), "pageSize");
        ValidateCommon.validateNullObject(lawRequest.getRowIndex(), "rowIndex");
        List<Law> lawList = lawDAO.search(lawRequest);
        if (ArrayListCommon.isNullOrEmpty(lawList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return lawList;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Law request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Law.class);
        List<Law> list = getList(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }

    @Override
    public PageResponse getPageAll(PageRequest pageRequest) throws Exception {
        return new PageResponse(
                lawRepository.findAll(
                        PageCommon.createPageable(
                                pageRequest.getPageNumber(),
                                pageRequest.getPageSize()
                        )
                )
        );
    }

    @Override
    public List<Law> getListForDropbox(Law law) throws Exception {
        List<Law> lawList = lawRepository.getListForDropbox(law.getLawId(), law.getLawName());
        if (ArrayListCommon.isNullOrEmpty(lawList)) throw new CommonException(Response.DATA_NOT_FOUND);
        List<Law> lawListFinal = new ArrayList<>();
        lawList.forEach(x -> lawListFinal.add(new Law(x.getLawCode(), "Điều " + x.getLawId() + " - " + x.getLawName())));
        return lawListFinal;
    }

    @Override
    public List<Law> searchLawId(LawRequest lawRequest) throws Exception {
        ValidateCommon.validateNullObject(lawRequest.getPageSize(), "pageSize");
        ValidateCommon.validateNullObject(lawRequest.getRowIndex(), "rowIndex");
        List<Law> lawList = lawDAO.searchLawId(lawRequest);
        if (ArrayListCommon.isNullOrEmpty(lawList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return lawList;
    }

    @Override
    public List<Law> searchLawItem(LawRequest lawRequest) throws Exception {
        List<Law> lawList = lawDAO.searchLawItem(lawRequest);
        if (ArrayListCommon.isNullOrEmpty(lawList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return lawList;
    }

    @Override
    public List<Law> searchLawPoint(LawRequest lawRequest) throws Exception {
        List<Law> lawList = lawDAO.searchLawPoint(lawRequest);
        if (ArrayListCommon.isNullOrEmpty(lawList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return lawList;
    }

    @Override
    public List<Law> findLawByCodeId(LawRequest lawRequest) throws Exception {
        ValidateCommon.validateNullObject(lawRequest.getCodeId(), "codeId");
        List<Law> list = lawRepository.findByCodeIdAndItemIsNullAndPointIsNullOrderByLawId(lawRequest.getCodeId());
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public List<Law> findItemByLawId(LawRequest lawRequest) throws Exception {
        ValidateCommon.validateNullObject(lawRequest.getCodeId(), "codeId");
        ValidateCommon.validateNullObject(lawRequest.getLawId(), "lawId");
        List<Law> list = lawRepository.findByCodeIdAndLawIdAndPointIsNullAndItemIsNotNullOrderByItem(lawRequest.getCodeId(), lawRequest.getLawId());
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public List<Law> findPointByItemId(LawRequest lawRequest) throws Exception {
        ValidateCommon.validateNullObject(lawRequest.getCodeId(), "codeId");
        ValidateCommon.validateNullObject(lawRequest.getLawId(), "lawId");
        ValidateCommon.validateNullObject(lawRequest.getItem(), "item");
        List<Law> list = lawRepository.findByCodeIdAndLawIdAndItemAndPointIsNotNullOrderByPoint(lawRequest.getCodeId(), lawRequest.getLawId(), lawRequest.getItem());
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    public void validateFields(Law law) throws Exception {
        ValidateCommon.validateNullObject(law.getLawCode(), "lawCode");
        ValidateCommon.validateNullObject(law.getItem(), "item");
        ValidateCommon.validateNullObject(law.getPoint(), "point");
        ValidateCommon.validateNullObject(law.getLawName(), "lawName");
        ValidateCommon.validateNullObject(law.getLawDate(), "lawDate");
        ValidateCommon.validateNullObject(law.getPriority(), "priority");
    }
}
