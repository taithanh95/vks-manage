package com.bitsco.vks.manage.service;

import com.bitsco.vks.manage.entities.Register;

public interface RegisterService {

    Register findFirstByCaseCodeAndStage(Register register) throws Exception;
}
