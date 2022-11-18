package com.bitsco.vks.manage.service;

import com.bitsco.vks.manage.entities.Conclusion;

import java.util.List;

public interface ConclusionService {

    List<Conclusion> getList(Conclusion conclusion) throws Exception;
}
