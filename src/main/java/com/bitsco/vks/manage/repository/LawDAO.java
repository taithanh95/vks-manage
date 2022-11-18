package com.bitsco.vks.manage.repository;

import com.bitsco.vks.common.constant.Constant;
import com.bitsco.vks.common.util.JsonCommon;
import com.bitsco.vks.common.util.StringCommon;
import com.bitsco.vks.manage.constant.ConstantManage;
import com.bitsco.vks.manage.entities.Law;
import com.bitsco.vks.manage.request.LawRequest;
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
public class LawDAO {
    private static final Logger LOGGER = LogManager.getLogger(Constant.LOG_APPENDER.DB);

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Law> search(LawRequest lawRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] LawDAO.search = " + JsonCommon.objectToJsonNotNull(lawRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<Law> rm = new SingleColumnRowMapper<Law>() {
            @Override
            public Law mapRow(ResultSet rs, int rowNum) throws SQLException {
                Law response = new Law();
                response.setLawCode(rs.getString("LAWCODE"));
                response.setLawId(rs.getString("LAWID"));
                response.setItem(rs.getString("ITEM"));
                response.setPoint(rs.getString("POINT"));
                response.setLawName(rs.getString("LAWNAME"));
                response.setLawDate(rs.getDate("LAWDATE"));
                response.setPriority(rs.getLong("PRIORITY"));
                response.setSetorder(rs.getLong("SETORDER"));
                response.setGroupId(rs.getString("GROUPID"));
                response.setStatus(rs.getString("STATUS"));
                response.setCodeId(rs.getString("CODEID"));
                response.setLawType(rs.getString("LAWTYPE"));
                response.setLawCodeParrent(rs.getString("LAWCODEPARRENT"));
                response.setSync(rs.getInt("SYNC"));
                response.setCreatedAt(rs.getDate("CRTDATE"));
                response.setUpdatedAt(rs.getDate("MDFDATE"));
                response.setCreatedBy(rs.getString("CRTUSER"));
                response.setUpdatedBy(rs.getString("MDFUSER"));
                response.setFullName("Điều " + rs.getString("LAWID") + " - " + rs.getString("LAWNAME"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.FN_SEARCH)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("p_rowindex", lawRequest.getRowIndex())
                    .addValue("p_pagesize", lawRequest.getPageSize())
                    .addValue("p_sortfield", StringCommon.isNullOrBlank(lawRequest.getSortField()) ? null : lawRequest.getSortField().trim())
                    .addValue("p_sortorder", StringCommon.isNullOrBlank(lawRequest.getSortOrder()) ? null : lawRequest.getSortOrder().trim())
                    .addValue("p_lawid", StringCommon.isNullOrBlank(lawRequest.getLawId()) ? null : lawRequest.getLawId().trim())
                    .addValue("p_item", StringCommon.isNullOrBlank(lawRequest.getItem()) ? null : lawRequest.getItem().trim())
                    .addValue("p_point", StringCommon.isNullOrBlank(lawRequest.getPoint()) ? null : lawRequest.getPoint().trim())
                    .addValue("p_lawname", StringCommon.isNullOrBlank(lawRequest.getLawName()) ? null : lawRequest.getLawName().trim())
                    .addValue("p_groupid", StringCommon.isNullOrBlank(lawRequest.getGroupId()) ? null : lawRequest.getGroupId().trim())
                    .addValue("p_codeid", StringCommon.isNullOrBlank(lawRequest.getCodeId()) ? null : lawRequest.getCodeId().trim());
            return jdbcCall.executeFunction((Class<List<Law>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu LawDAO.search", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] LawDAO.search");
        }
    }

    public List<Law> searchLawId(LawRequest lawRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] LawDAO.searchLawId = " + JsonCommon.objectToJsonNotNull(lawRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<Law> rm = new SingleColumnRowMapper<Law>() {
            @Override
            public Law mapRow(ResultSet rs, int rowNum) throws SQLException {
                Law response = new Law();
                response.setLawCode(rs.getString("LAWCODE"));
                response.setLawId(rs.getString("LAWID"));
                response.setItem(rs.getString("ITEM"));
                response.setPoint(rs.getString("POINT"));
                response.setLawName(rs.getString("LAWNAME"));
                response.setLawDate(rs.getDate("LAWDATE"));
                response.setPriority(rs.getLong("PRIORITY"));
                response.setSetorder(rs.getLong("SETORDER"));
                response.setGroupId(rs.getString("GROUPID"));
                response.setStatus(rs.getString("STATUS"));
                response.setCodeId(rs.getString("CODEID"));
                response.setLawType(rs.getString("LAWTYPE"));
                response.setLawCodeParrent(rs.getString("LAWCODEPARRENT"));
                response.setSync(rs.getInt("SYNC"));
                response.setCreatedAt(rs.getDate("CRTDATE"));
                response.setUpdatedAt(rs.getDate("MDFDATE"));
                response.setCreatedBy(rs.getString("CRTUSER"));
                response.setUpdatedBy(rs.getString("MDFUSER"));
                response.setFullName("Điều " + rs.getString("LAWID") + " - " + rs.getString("LAWNAME"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.FN_SEARCH_LAWID)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("p_rowindex", lawRequest.getRowIndex())
                    .addValue("p_pagesize", lawRequest.getPageSize())
                    .addValue("p_lawname", StringCommon.isNullOrBlank(lawRequest.getLawName()) ? null : lawRequest.getLawName().trim())
                    .addValue("p_codeid", StringCommon.isNullOrBlank(lawRequest.getCodeId()) ? null : lawRequest.getCodeId().trim());
            return jdbcCall.executeFunction((Class<List<Law>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu LawDAO.searchLawId", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] LawDAO.searchLawId");
        }
    }

    public List<Law> searchLawItem(LawRequest lawRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] LawDAO.searchLawItem = " + JsonCommon.objectToJsonNotNull(lawRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<Law> rm = new SingleColumnRowMapper<Law>() {
            @Override
            public Law mapRow(ResultSet rs, int rowNum) throws SQLException {
                Law response = new Law();
                response.setLawCode(rs.getString("LAWCODE"));
                response.setLawId(rs.getString("LAWID"));
                response.setItem(rs.getString("ITEM"));
                response.setPoint(rs.getString("POINT"));
                response.setLawName(rs.getString("LAWNAME"));
                response.setLawDate(rs.getDate("LAWDATE"));
                response.setPriority(rs.getLong("PRIORITY"));
                response.setSetorder(rs.getLong("SETORDER"));
                response.setGroupId(rs.getString("GROUPID"));
                response.setStatus(rs.getString("STATUS"));
                response.setCodeId(rs.getString("CODEID"));
                response.setLawType(rs.getString("LAWTYPE"));
                response.setLawCodeParrent(rs.getString("LAWCODEPARRENT"));
                response.setSync(rs.getInt("SYNC"));
                response.setCreatedAt(rs.getDate("CRTDATE"));
                response.setUpdatedAt(rs.getDate("MDFDATE"));
                response.setCreatedBy(rs.getString("CRTUSER"));
                response.setUpdatedBy(rs.getString("MDFUSER"));
                response.setFullName("Điều " + rs.getString("LAWID") + " - " + rs.getString("LAWNAME"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.FN_SEARCH_ITEM)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("p_lawid", StringCommon.isNullOrBlank(lawRequest.getLawId()) ? null : lawRequest.getLawId().trim())
                    .addValue("p_item", StringCommon.isNullOrBlank(lawRequest.getItem()) ? null : lawRequest.getItem().trim())
                    .addValue("p_codeid", StringCommon.isNullOrBlank(lawRequest.getCodeId()) ? null : lawRequest.getCodeId().trim());
            return jdbcCall.executeFunction((Class<List<Law>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu LawDAO.searchLawItem", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] LawDAO.searchLawItem");
        }
    }

    public List<Law> searchLawPoint(LawRequest lawRequest) throws Exception {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[B][" + startTime + "] LawDAO.searchLawPoint = " + JsonCommon.objectToJsonNotNull(lawRequest));
        SimpleJdbcCall jdbcCall;
        RowMapper<Law> rm = new SingleColumnRowMapper<Law>() {
            @Override
            public Law mapRow(ResultSet rs, int rowNum) throws SQLException {
                Law response = new Law();
                response.setLawCode(rs.getString("LAWCODE"));
                response.setLawId(rs.getString("LAWID"));
                response.setItem(rs.getString("ITEM"));
                response.setPoint(rs.getString("POINT"));
                response.setLawName(rs.getString("LAWNAME"));
                response.setLawDate(rs.getDate("LAWDATE"));
                response.setPriority(rs.getLong("PRIORITY"));
                response.setSetorder(rs.getLong("SETORDER"));
                response.setGroupId(rs.getString("GROUPID"));
                response.setStatus(rs.getString("STATUS"));
                response.setCodeId(rs.getString("CODEID"));
                response.setLawType(rs.getString("LAWTYPE"));
                response.setLawCodeParrent(rs.getString("LAWCODEPARRENT"));
                response.setSync(rs.getInt("SYNC"));
                response.setCreatedAt(rs.getDate("CRTDATE"));
                response.setUpdatedAt(rs.getDate("MDFDATE"));
                response.setCreatedBy(rs.getString("CRTUSER"));
                response.setUpdatedBy(rs.getString("MDFUSER"));
                response.setFullName("Điều " + rs.getString("LAWID") + " - " + rs.getString("LAWNAME"));
                return response;
            }
        };
        try {
            jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(ConstantManage.PACKAGE.PKG_CATEGORY)
                    .withFunctionName(ConstantManage.FUNCTION.FN_SEARCH_POINT)
                    .returningResultSet("return", rm);
            SqlParameterSource paramMap = new MapSqlParameterSource()
                    .addValue("p_lawid", StringCommon.isNullOrBlank(lawRequest.getLawId()) ? null : lawRequest.getLawId().trim())
                    .addValue("p_item", StringCommon.isNullOrBlank(lawRequest.getItem()) ? null : lawRequest.getItem().trim())
                    .addValue("p_point", StringCommon.isNullOrBlank(lawRequest.getPoint()) ? null : lawRequest.getPoint().trim())
                    .addValue("p_codeid", StringCommon.isNullOrBlank(lawRequest.getCodeId()) ? null : lawRequest.getCodeId().trim());
            return jdbcCall.executeFunction((Class<List<Law>>) (Class) List.class, paramMap);
        } catch (Exception e) {
            LOGGER.error("[Exception][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] khi truy vấn dữ liệu LawDAO.searchLawPoint", e);
            throw e;
        } finally {
            LOGGER.info("[E][" + startTime + "][Duration = " + (System.currentTimeMillis() - startTime) + "] LawDAO.searchLawPoint");
        }
    }
}

