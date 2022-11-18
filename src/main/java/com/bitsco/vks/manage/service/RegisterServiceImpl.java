package com.bitsco.vks.manage.service;

import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.validate.ValidateCommon;
import com.bitsco.vks.manage.entities.Register;
import com.bitsco.vks.manage.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    RegisterRepository registerRepository;

    @Override
    public Register findFirstByCaseCodeAndStage(Register register) throws Exception {
        ValidateCommon.validateNullObject(register.getCaseCode(), "caseCode");
        ValidateCommon.validateNullObject(register.getStage(), "stage");
        Optional<Register> registerOptional = registerRepository.findFirstByCaseCodeAndStage(register.getCaseCode(), register.getStage());
        if (!registerOptional.isPresent()) {
            throw new CommonException(Response.DATA_NOT_FOUND, Response.DATA_NOT_FOUND.getResponseMessage());
        }
        return registerOptional.get();
    }
}
