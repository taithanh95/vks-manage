package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Law;
import com.bitsco.vks.manage.request.LawRequest;

import java.util.List;

public interface LawService {

    Law create(Law law) throws Exception;

    Law update(Law law) throws Exception;

    List<Law> getList(Law law) throws Exception;

    List<Law> search(LawRequest lawRequest) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;

    PageResponse getPageAll(PageRequest pageRequest) throws Exception;

    List<Law> getListForDropbox(Law law) throws Exception;

    List<Law> searchLawId(LawRequest lawRequest) throws Exception;

    List<Law> searchLawItem(LawRequest lawRequest) throws Exception;

    List<Law> searchLawPoint(LawRequest lawRequest) throws Exception;

    List<Law> findLawByCodeId(LawRequest lawRequest) throws Exception;

    List<Law> findItemByLawId(LawRequest lawRequest) throws Exception;

    List<Law> findPointByItemId(LawRequest lawRequest) throws Exception;
}
