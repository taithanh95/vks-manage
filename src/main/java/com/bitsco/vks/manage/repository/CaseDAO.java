package com.bitsco.vks.manage.repository;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.DateCommon;
import com.bitsco.vks.common.util.JsonCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.constant.ConstantManage;
import com.bitsco.vks.manage.entities.Accused;
import com.bitsco.vks.manage.entities.Case;
import com.bitsco.vks.manage.entities.Law;
import com.bitsco.vks.manage.request.CaseRequest;
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
public class CaseDAO {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.DB);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Case> getListVuAnChuaKetThucThuLy(CaseRequest caseRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] CaseDAO.getListVuAnChuaKetThucThuLy accusedRequest = " + JsonCommon.objectToJsonNotNull(caseRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<Case> rm = new SingleColumnRowMapper<Case>() {
            @Override
            public Case mapRow(ResultSet rs, int rowNum) throws SQLException {
                Case response = new Case();
                response.setCaseCode(rs.getString("s_case_code"));
                response.setCaseName(rs.getString("s_case_name"));
                response.setBeginSetnum(rs.getString("s_begin_setnum"));
                response.setBeginIndate(rs.getDate("d_begin_indate"));
                response.setFirstAcc(rs.getString("s_first_accused_code"));
                response.setLawId(rs.getString("s_law_id"));
                response.setLawCode(rs.getString("s_law_code"));
                response.setFirstAccused(new Accused(response.getFirstAcc(), rs.getString("s_first_accused_name")));
                response.setLaw(new Law(response.getLawCode(), response.getLawId(), rs.getString("s_item"), rs.getString("s_point"), rs.getString("s_law_name")));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.GET_LIST_CASE_CHUA_KET_THUC_THU_LY)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("pi_username", StringCommon.isNullOrBlank(caseRequest.getUsername()) ? null : caseRequest.getUsername().trim())
                    .addValue("pi_sppid", StringCommon.isNullOrBlank(caseRequest.getSppId()) ? null : caseRequest.getSppId())
                    .addValue("pi_from_date", caseRequest.getFromDate() == null ? null : DateCommon.convertDateToString(caseRequest.getFromDate()))
                    .addValue("pi_to_date", caseRequest.getToDate() == null ? null : DateCommon.convertDateToString(caseRequest.getToDate()))
                    .addValue("pi_case_code", StringCommon.isNullOrBlank(caseRequest.getCaseCode()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseCode()))
                    .addValue("pi_case_name", StringCommon.isNullOrBlank(caseRequest.getCaseName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getCaseName()))
                    .addValue("pi_first_accused_code", StringCommon.isNullOrBlank(caseRequest.getFirstAccusedCode()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getFirstAccusedCode()))
                    .addValue("pi_first_accused_name", StringCommon.isNullOrBlank(caseRequest.getFirstAccusedName()) ? null : StringCommon.addLikeRightAndLeft(caseRequest.getFirstAccusedName()))
                    .addValue("pi_begin_setnum", StringCommon.isNullOrBlank(caseRequest.getBeginSetnum()) ? null : caseRequest.getBeginSetnum());
            return jdbcCall.executeFunction((Class<List<Case>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu AccusedDAO.getList", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] CaseDAO.getListVuAnChuaKetThucThuLy");
        }
    }
}
