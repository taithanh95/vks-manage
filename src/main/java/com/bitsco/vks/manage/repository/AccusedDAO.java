package com.bitsco.vks.manage.repository;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.JsonCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.constant.ConstantManage;
import com.bitsco.vks.manage.request.AccusedRequest;
import com.bitsco.vks.manage.response.AccusedResponse;
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
public class AccusedDAO {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.DB);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<AccusedResponse> getListAccused(AccusedRequest accusedRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] AccusedDAO.getPage accusedRequest = " + JsonCommon.objectToJsonNotNull(accusedRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<AccusedResponse> rm = new SingleColumnRowMapper<AccusedResponse>() {
            @Override
            public AccusedResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                AccusedResponse response = new AccusedResponse();
                response.setAccuCode(rs.getString("s_accused_code"));
                response.setFullName(rs.getString("s_accused_name"));
                response.setBirthDay(rs.getDate("d_birthday"));
                response.setSex(rs.getString("s_sex"));
                response.setLawId(rs.getString("s_law_id"));
                response.setItem(rs.getString("s_item"));
                response.setPoint(rs.getString("s_point"));
                response.setLawName(rs.getString("s_law_name"));
                response.setCaseCode(rs.getString("s_case_code"));
                response.setCaseName(rs.getString("s_case_name"));
                response.setBeginIndate(rs.getDate("d_begin_indate"));
                response.setBeginSetnum(rs.getString("s_begin_setnum"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.GET_LIST_ACCUCODE)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("pi_username", accusedRequest.getUsername())
                    .addValue("pi_sppid", StringCommon.isNullOrBlank(accusedRequest.getSppId()) ? null : accusedRequest.getSppId())
                    .addValue("pi_case_code", StringCommon.isNullOrBlank(accusedRequest.getCaseCode()) ? null : StringCommon.addLikeRightAndLeft(accusedRequest.getCaseCode()))
                    .addValue("pi_case_name", StringCommon.isNullOrBlank(accusedRequest.getCaseName()) ? null : StringCommon.addLikeRightAndLeft(accusedRequest.getCaseName()))
                    .addValue("pi_accused_code", StringCommon.isNullOrBlank(accusedRequest.getAccuCode()) ? null : StringCommon.addLikeRightAndLeft(accusedRequest.getAccuCode()))
                    .addValue("pi_accused_name", StringCommon.isNullOrBlank(accusedRequest.getFullName()) ? null : StringCommon.addLikeRightAndLeft(accusedRequest.getFullName()))
                    .addValue("pi_begin_setnum", StringCommon.isNullOrBlank(accusedRequest.getBeginSetnum()) ? null : accusedRequest.getBeginSetnum());
            return jdbcCall.executeFunction((Class<List<AccusedResponse>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu AccusedDAO.getList", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] AccusedDAO.getList");
        }
    }
}
