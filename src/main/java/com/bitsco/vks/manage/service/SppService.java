package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.PageResponse;
import com.bitsco.vks.manage.entities.Spp;
import com.bitsco.vks.manage.entities.SppUpperCase;
import com.bitsco.vks.manage.model.TreeviewItem;
import com.bitsco.vks.manage.model.User;

import java.util.List;

public interface SppService {
    List<Spp> findByStatus(Spp spp) throws Exception;

    List<Spp> findByUsername(User user) throws Exception;

    Spp findFirstByUsername(User user) throws Exception;

    Spp findByParent(Spp spp) throws Exception;

    Spp findById(String sppId) throws Exception;

    List<SppUpperCase> findAllByParent(String SPPID) throws Exception;

    PageResponse getPage(PageRequest pageRequest) throws Exception;

    List<TreeviewItem> findAllTreeView(Spp spp) throws Exception;
}
