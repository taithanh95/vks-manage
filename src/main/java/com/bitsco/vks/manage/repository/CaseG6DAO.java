package com.bitsco.vks.manage.repository;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.DateCommon;
import com.bitsco.vks.common.util.JsonCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.constant.ConstantManage;
import com.bitsco.vks.manage.request.CaseG6Request;
import com.bitsco.vks.manage.response.CaseG6Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CaseG6DAO {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.DB);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<CaseG6Response> getListCaseG6(CaseG6Request caseG6Request) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] CaseG6DAO.getListCaseG6 caseG6Request = " + JsonCommon.objectToJsonNotNull(caseG6Request));
        SimpleJdbcCall jdbcCall;
        RowMapper<CaseG6Response> rm = new SingleColumnRowMapper<CaseG6Response>() {
            @Override
            public CaseG6Response mapRow(ResultSet rs, int rowNum) throws SQLException {
                CaseG6Response response = new CaseG6Response();
                    response.setCaseCode(rs.getString("s_column_01"));
                response.setCaseName(rs.getString("s_column_02"));
                response.setRegiCode(rs.getString("s_column_03"));
                response.setAccuName(rs.getString("s_column_04"));
                response.setIndate(rs.getDate("s_column_05"));
                response.setSppCentence(rs.getString("s_column_06"));
                response.setSppExecutrans(rs.getString("s_column_07"));
                response.setStatus(rs.getString("s_column_08"));
                response.setReasonRefuse(rs.getString("s_column_09"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CASE)
                    .withFunctionName(ConstantManage.FUNCTION.GET_LIST_G6)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("pi_case_code", StringCommon.isNullOrBlank(caseG6Request.getCaseCode()) ? null : StringCommon.addLikeRightAndLeft(caseG6Request.getCaseCode()))
                    .addValue("pi_case_name", StringCommon.isNullOrBlank(caseG6Request.getCaseName()) ? null : StringCommon.addLikeRightAndLeft(caseG6Request.getCaseName()))
                    .addValue("pi_register_code", StringCommon.isNullOrBlank(caseG6Request.getRegiCode()) ? null : StringCommon.addLikeRightAndLeft(caseG6Request.getRegiCode()))
                    .addValue("pi_spp_id", StringCommon.isNullOrBlank(caseG6Request.getSppCentence()) ? null : caseG6Request.getSppCentence())
                    .addValue("pi_from_date", caseG6Request.getFromDate() == null ? null : DateCommon.convertDateToString(caseG6Request.getFromDate()))
                    .addValue("pi_to_date", caseG6Request.getToDate() == null ? null : DateCommon.convertDateToString(caseG6Request.getToDate()))
                    .addValue("pi_accused_name", StringCommon.isNullOrBlank(caseG6Request.getAccuName()) ? null : StringCommon.addLikeRightAndLeft(caseG6Request.getAccuName()))
                    .addValue("pi_commission_type", caseG6Request.getCommissionType() == null ? null : caseG6Request.getCommissionType())
                    .addValue("pi_accept_type", caseG6Request.getAcceptType() == null ? null : caseG6Request.getAcceptType())
                    .addValue("pi_execute_judgment_status", caseG6Request.getExecuteJudgmentStatus() == null ? null : caseG6Request.getExecuteJudgmentStatus());
            return jdbcCall.executeFunction((Class<List<CaseG6Response>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu CaseG6DAO.getListCaseG6", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] CaseG6DAO.getListCaseG6");
        }
    }
}
