package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.util.ArrayListCommon;
import com.bitsco.vks.common.util.DateCommon;
import com.bitsco.vks.common.util.PageCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.common.validate.ValidateCommon;
import com.bitsco.vks.manage.cache.CacheService;
import com.bitsco.vks.manage.entities.Accused;
import com.bitsco.vks.manage.entities.Case;
import com.bitsco.vks.manage.repository.AccusedRepository;
import com.bitsco.vks.manage.repository.CaseDAO;
import com.bitsco.vks.manage.repository.CaseG6DAO;
import com.bitsco.vks.manage.repository.CaseRepository;
import com.bitsco.vks.manage.request.CaseG6Request;
import com.bitsco.vks.manage.request.CaseRequest;
import com.bitsco.vks.manage.response.CaseG6Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CaseServiceImpl implements CaseService {
    @Autowired
    CaseRepository caseRepository;

    @Autowired
    CaseDAO caseDAO;

    @Autowired
    CaseG6DAO caseG6DAO;

    @Autowired
    CacheService cacheService;

    @Autowired
    AccusedRepository accusedRepository;

    @Autowired
    AccusedService accusedService;

    @Override
    public Page<Case> getList(CaseRequest caseRequest, Pageable pageable) throws Exception {
        Page<Case> caseList = caseRepository.getList(
                StringCommon.isNullOrBlank(caseRequest.getCaseCode()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseCode().trim()),
                StringCommon.isNullOrBlank(caseRequest.getCaseName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseName().trim()),
                StringCommon.isNullOrBlank(caseRequest.getSppId()) ? null : caseRequest.getSppId(),
                StringCommon.isNullOrBlank(caseRequest.getFirstAccusedName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getFirstAccusedName().trim()),
                caseRequest.getFromBeginIndate() == null ? null : DateCommon.convertDateToString(caseRequest.getFromBeginIndate()),
                caseRequest.getToBeginIndate() == null ? null : DateCommon.convertDateToString(caseRequest.getToBeginIndate()),
                StringCommon.isNullOrBlank(caseRequest.getStatus()) ? null : caseRequest.getStatus(),
                pageable
        );
        if (!caseList.hasContent())
            throw new CommonException(Response.DATA_NOT_FOUND);
//        if (ArrayListCommon.isNullOrEmpty(caseList))
        caseList.getContent().stream().forEach(x -> {
            try {
                if (!StringCommon.isNullOrBlank(x.getFirstAcc())) {
                    Accused accused = accusedService.findById(new Accused(x.getFirstAcc()));
                    if (accused != null) {
                        x.setFirstAccused(accused);
                        x.setFirstAccusedName(accused.getFullName());
                        x.setFirstAccusedNameNoSign(StringCommon.toNoSign(accused.getFullName()));
                    }
                }
                if (!StringCommon.isNullOrBlank(x.getCaseName())) {
                    x.setCaseNameNoSign(StringCommon.toNoSign(x.getCaseName()));
                }
                if (!StringCommon.isNullOrBlank(x.getLawCode()))
                    x.setLaw(cacheService.getLawFromCache(x.getLawCode()));
            } catch (Exception e) {
            }
        });
        return caseList;
    }

    public List<Case> getList1(CaseRequest caseRequest) throws Exception {
        List<Case> caseList = caseRepository.getList1(
                StringCommon.isNullOrBlank(caseRequest.getCaseCode()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseCode().trim()),
                StringCommon.isNullOrBlank(caseRequest.getCaseName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseName().trim()),
                StringCommon.isNullOrBlank(caseRequest.getSppId()) ? null : caseRequest.getSppId(),
                StringCommon.isNullOrBlank(caseRequest.getFirstAccusedName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getFirstAccusedName().trim()),
                caseRequest.getFromBeginIndate() == null ? null : DateCommon.convertDateToString(caseRequest.getFromBeginIndate()),
                caseRequest.getToBeginIndate() == null ? null : DateCommon.convertDateToString(caseRequest.getToBeginIndate()),
                StringCommon.isNullOrBlank(caseRequest.getStatus()) ? null : caseRequest.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(caseList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        caseList.stream().forEach(x -> {
            try {
                if (!StringCommon.isNullOrBlank(x.getFirstAcc())) {
                    Accused accused = accusedService.findById(new Accused(x.getFirstAcc()));
                    if (accused != null) {
                        x.setFirstAccused(accused);
                        x.setFirstAccusedName(accused.getFullName());
                        x.setFirstAccusedNameNoSign(StringCommon.toNoSign(accused.getFullName()));
                    }
                }
                if (!StringCommon.isNullOrBlank(x.getCaseName())) {
                    x.setCaseNameNoSign(StringCommon.toNoSign(x.getCaseName()));
                }
                if (!StringCommon.isNullOrBlank(x.getLawCode()))
                    x.setLaw(cacheService.getLawFromCache(x.getLawCode()));
            } catch (Exception e) {
            }
        });
        return caseList;
    }

    @Override
    public List<Case> getListVuAnChuaKetThucThuLy(CaseRequest caseRequest) throws Exception {
        caseRequest.setUsername(cacheService.getUsernameFromHeader());
        List<Case> caseList = caseDAO.getListVuAnChuaKetThucThuLy(caseRequest);
        if (ArrayListCommon.isNullOrEmpty(caseList))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return caseList;
    }

    @Override
    public List<CaseG6Response> getListG6(CaseG6Request caseG6Request) throws Exception {
        List<CaseG6Response> caseG6List = caseG6DAO.getListCaseG6(caseG6Request);
        if (ArrayListCommon.isNullOrEmpty(caseG6List))
            throw new CommonException(Response.DATA_NOT_FOUND);
        return caseG6List;
    }

    @Override
    public PageResponse getPageG6(PageRequest pageRequest) throws Exception {
        CaseG6Request request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), CaseG6Request.class);
        List<CaseG6Response> list = getListG6(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        CaseRequest request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), CaseRequest.class);
        List<Case> list = getList1(request);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        if (!StringUtils.isBlank(pageRequest.getSortField()) && !StringUtils.isBlank(pageRequest.getSortOrder())) {
            sortCase(list, pageRequest.getSortField(), pageRequest.getSortOrder());
        } else {
            list.sort(Comparator.comparing(Case::getCreatedAt, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
        }
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }

    private void sortCase(List<Case> list, String sortField, String sortOrder) {
        if (sortField.equals("caseCode")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getCaseCode, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getCaseCode, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
        if (sortField.equals("caseName")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getCaseNameNoSign, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getCaseNameNoSign, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
        if (sortField.equals("firstAccusedName")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getFirstAccusedNameNoSign, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getFirstAccusedNameNoSign, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
        if (sortField.equals("beginSetnum")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getBeginSetnum, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getBeginSetnum, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
        if (sortField.equals("beginIndate")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getBeginIndate, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getBeginIndate, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
        if (sortField.equals("createdAt")) {
            if (sortOrder.equals("descend")) {
                list.sort(Comparator.comparing(Case::getCreatedAt, Comparator.nullsFirst(Comparator.naturalOrder())).reversed());
            } else {
                list.sort(Comparator.comparing(Case::getCreatedAt, Comparator.nullsFirst(Comparator.naturalOrder())));
            }
        }
    }


    @Override
    public Case findById(Case c) throws Exception {
        ValidateCommon.validateNullObject(c.getCaseCode(), "caseCode");
        Case response = cacheService.getCaseFromCache(c.getCaseCode());
        if (response != null) return response;
        response = caseRepository.findFirstByCaseCode(c.getCaseCode());
        if (response == null) throw new CommonException(Response.OBJECT_NOT_FOUND);
        else cacheService.addCase2RedisCache(response);
        return response;
    }

    @Override
    public Case detail(Case c) throws Exception {
        ValidateCommon.validateNullObject(c.getCaseCode(), "caseCode");
        Case response = findById(c);
        response.setAccusedList(accusedRepository.findByCaseCode(response.getCaseCode()));
        if (!StringCommon.isNullOrBlank(response.getFirstAcc()))
            response.setFirstAccused(accusedService.findById(new Accused(response.getFirstAcc())));
        if (!StringCommon.isNullOrBlank(response.getLawCode()))
            response.setLaw(cacheService.getLawFromCache(response.getLawCode()));
        return response;
    }
}
