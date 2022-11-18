package com.bitsco.vks.manage.api;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.exception.CommonException;
import com.bitsco.vks.common.request.PageRequest;
import com.bitsco.vks.common.response.Response;
import com.bitsco.vks.common.response.ResponseBody;
import com.bitsco.vks.manage.entities.InvestigativeAgency;
import com.bitsco.vks.manage.service.InvestigativeAgencyService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Tai Thanh <taithanh95.dev@gmail.com>
 * Date: 12/10/2022
 * Time: 3:45 PM
 */
@CrossOrigin
@RestController
@RequestMapping(value = "investigativeAgency")
public class InvestigativeAgencyController {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.CONTROLLER);

    @Autowired
    InvestigativeAgencyService investigativeAgencyService;


    @Operation(description = "Truy vấn danh sách thông tin CQĐT")
    @CrossOrigin(origins = "*")
    @PostMapping("/getList/")
    public ResponseEntity<?> getList(@RequestBody InvestigativeAgency investigativeAgency) throws Exception {
        try {
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, investigativeAgencyService.getList(investigativeAgency)), HttpStatus.OK);
        } catch (CommonException e) {
            return new ResponseEntity<>(new ResponseBody(e.getResponse().getResponseCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Exception when /investigativeAgency/getList/ ", e);
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, e.getMessage()), HttpStatus.OK);
        }
    }

    @Operation(description = "Truy vấn danh sách thông tin CQĐT - Có phân trang")
    @CrossOrigin(origins = "*")
    @PostMapping("/getPage/")
    public ResponseEntity<?> getPage(@RequestBody PageRequest pageRequest) throws Exception {
        try {
            return new ResponseEntity<>(new ResponseBody(Response.SUCCESS, investigativeAgencyService.getPage(pageRequest)), HttpStatus.OK);
        } catch (CommonException e) {
            return new ResponseEntity<>(new ResponseBody(e.getResponse().getResponseCode(), e.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("Exception when /investigativeAgency/getPage/ ", e);
            return new ResponseEntity<>(new ResponseBody(Response.SYSTEM_ERROR, e.getMessage()), HttpStatus.OK);
        }
    }

}
