package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Case;
import com.bitsco.vks.manage.request.CaseG6Request;
import com.bitsco.vks.manage.request.CaseRequest;
import com.bitsco.vks.manage.response.CaseG6Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CaseService {

    Page<Case> getList(CaseRequest caseRequest, Pageable pageable) throws Exception;

    List<Case> getList1(CaseRequest caseRequest) throws Exception;

    List<Case> getListVuAnChuaKetThucThuLy(CaseRequest caseRequest) throws Exception;

    List<CaseG6Response> getListG6(CaseG6Request caseG6Request) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;

    PageResponse getPageG6(PageRequest pageRequest) throws Exception;

    Case findById(Case c) throws Exception;

    Case detail(Case c) throws Exception;
}
