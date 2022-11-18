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
import com.bitsco.vks.manage.entities.Spp;
import com.bitsco.vks.manage.entities.SppUpperCase;
import com.bitsco.vks.manage.model.TreeviewItem;
import com.bitsco.vks.manage.model.User;
import com.bitsco.vks.manage.repository.SppRepository;
import com.bitsco.vks.manage.repository.SppUpperCaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SppServiceImpl implements SppService {

    @Autowired
    SppRepository sppRepository;

    @Autowired
    SppUpperCaseRepository sppUpperCaseRepository;

    @Autowired
    CacheService cacheService;

    @Override
    public List<Spp> findByStatus(Spp spp) throws Exception {
        ValidateCommon.validateNullObject(spp.getStatus(), "status");
        List<Spp> sppList = sppRepository.findByStatus(Constant.STATUS_OBJECT.ACTIVE_Y);
        if (ArrayListCommon.isNullOrEmpty(sppList)) throw new CommonException(Response.DATA_NOT_FOUND);
        return sppList;
    }

    @Override
    public List<Spp> findByUsername(User user) throws Exception {
        ValidateCommon.validateNullObject(user.getUsername(), "username");
        List<Spp> sppList = sppRepository.findByUsername(user.getUsername());
        if (ArrayListCommon.isNullOrEmpty(sppList)) throw new CommonException(Response.DATA_NOT_FOUND);
        return sppList;
    }

    @Override
    public Spp findFirstByUsername(User user) throws Exception {
        ValidateCommon.validateNullObject(user.getUsername(), "username");
        return sppRepository.findFirstByUsername(user.getUsername().trim());
    }

    @Override
    public Spp findByParent(Spp spp) throws Exception {
        ValidateCommon.validateNullObject(spp.getSppId(), "sppId");
        Spp vks = findById(spp.getSppId());
        if (vks == null) throw new CommonException(Response.DATA_NOT_FOUND);
        if (StringCommon.isNullOrBlank(vks.getSppParent())) return vks;
        else return findById(vks.getSppParent());
    }

    @Override
    public Spp findById(String sppId) throws Exception {
        Spp vks = cacheService.getSppFromCache(sppId);
        if (vks != null) return vks;
        vks = sppRepository.findById(sppId).orElse(null);
        if (vks == null) throw new CommonException(Response.DATA_NOT_FOUND);
        else cacheService.addSpp2RedisCache(vks);
        return vks;
    }

    @Override
    public PageResponse getPage(PageRequest pageRequest) throws Exception {
        Spp request = (new ObjectMapper()).convertValue(pageRequest.getDataRequest(), Spp.class);
        List<Spp> list = sppRepository.getList(
                request.getSppId(),
                StringCommon.isNullOrBlank(request.getName()) ? null : StringCommon.addLikeRightAndLeft(request.getName()),
                StringCommon.isNullOrBlank(request.getAddress()) ? null : StringCommon.addLikeRightAndLeft(request.getAddress()),
                request.getStatus()
        );
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return PageCommon.toPageResponse(
                list,
                pageRequest.getPageNumber(),
                pageRequest.getPageSize());
    }

    @Override
    public List<SppUpperCase> findAllByParent(String SPPID) throws Exception {
        ValidateCommon.validateNullObject(SPPID, "SPPID");
        List<SppUpperCase> list = sppUpperCaseRepository.findAllByParent(SPPID);
        if (ArrayListCommon.isNullOrEmpty(list)) throw new CommonException(Response.DATA_NOT_FOUND);
        return list;
    }

    @Override
    public List<TreeviewItem> findAllTreeView(Spp spp) throws Exception {
        ValidateCommon.validateNullObject(spp.getSppId(), "sppId");
        List<TreeviewItem> sppList = new ArrayList<>();
        Spp current = sppRepository.findById(spp.getSppId()).orElse(null);
        if (current == null)
            throw new CommonException(Response.OBJECT_NOT_FOUND, "Mã đơn vị không tồn tại");
        TreeviewItem treeviewItem = new TreeviewItem(current.getName(), current.getSppId());
        List<TreeviewItem> children = new ArrayList<>();
        List<Spp> childrenSpp = sppRepository.findBySppParent(current.getSppId());
        if (!ArrayListCommon.isNullOrEmpty(childrenSpp)) {
            childrenSpp.forEach(x -> {
                TreeviewItem item = new TreeviewItem(x.getName(), x.getSppId());
                List<TreeviewItem> childrenItem = new ArrayList<>();
                List<Spp> children2 = sppRepository.findBySppParent(x.getSppId());
                if (!ArrayListCommon.isNullOrEmpty(children2))
                    children2.forEach(y -> childrenItem.add(new TreeviewItem(y.getName(), y.getSppId())));
                item.setChildren(childrenItem);
                children.add(item);
            });
        }
        treeviewItem.setChildren(children);
        sppList.add(treeviewItem);
        return sppList;
    }
}
