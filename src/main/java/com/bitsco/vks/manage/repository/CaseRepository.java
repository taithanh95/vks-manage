package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface CaseRepository extends JpaRepository<Case, String> {
    @Query(value = " SELECT sc.* "
            + FROM_SELECT
            , countQuery = " SELECT COUNT(1) "
            + FROM_SELECT
            , nativeQuery = true
    )
    Page<Case> getList(
            @Param("caseCode") String caseCode,
            @Param("caseName") String caseName,
            @Param("sppId") String sppId,
            @Param("firstAcc") String firstAcc,
            @Param("fromBeginIndate") String fromBeginIndate,
            @Param("toBeginInDate") String toBeginInDate,
            @Param("status") String status,
            Pageable pageable
    );

    String FROM_SELECT =  " FROM                                                         "
            + "     spp_case sc                                          "
            + "     JOIN spp_accused sa ON sa.accucode = sc.firstacc     "
            + " WHERE                                                        "
            + "         1 = 1                                                "
             + "     AND (sc.sppid = '01' or sc.sppid IN (select sppid from lst_spp where isdepart = 'Y' and sppparent = '01'))                                                    "
            + "     AND (                                                    "
            + "             :caseCode IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sc.casecode) LIKE lower(:caseCode)              "
            + "     ) AND (                                                  "
            + "             :caseName IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sc.casename) LIKE lower(:caseName)              "
            + "     ) AND (                                                  "
            + "             :sppId IS NULL                                        "
            + "         OR                                                   "
            + "             sc.sppid = :sppId                                   "
            + "     ) AND (                                                  "
            + "             :firstAcc IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sa.fullname) LIKE lower(:firstAcc)              "
            + "     ) AND (                                                  "
            + "             :fromBeginIndate IS NULL                                        "
            + "         OR                                                   "
            + "             sc.begin_indate >= TO_DATE(:fromBeginIndate, 'dd/MM/yyyy')                       "
            + "     ) AND (                                                  "
            + "             :toBeginInDate IS NULL                                        "
            + "         OR                                                   "
            + "             sc.begin_indate < TO_DATE(:toBeginInDate, 'dd/MM/yyyy') + 1                 "
            + "     )  AND (                                                  "
            + "             :status IS NULL                                        "
            + "         OR                                                   "
            + "             sc.status < :status                              "
            + "     )                                                        ";

    @Query(value = " SELECT                                                       "
            + "     sc.*                                                     "
            + " FROM                                                         "
            + "     spp_case sc                                          "
            + "     JOIN spp_accused sa ON sa.accucode = sc.firstacc     "
            + " WHERE                                                        "
            + "         1 = 1                                                "
//             + "     AND (sc.sppid = '01' or sc.sppid IN (select sppid from lst_spp where isdepart = 'Y' and sppparent = '01'))                                                    "
             + "     AND (sc.sppid = '01' or sc.sppid IN (select sppid from lst_spp))                                                    "
            + "     AND (                                                    "
            + "             :caseCode IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sc.casecode) LIKE lower(:caseCode)              "
            + "     ) AND (                                                  "
            + "             :caseName IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sc.casename) LIKE lower(:caseName)              "
            + "     ) AND (                                                  "
            + "             :sppId IS NULL                                        "
            + "         OR                                                   "
            + "             sc.sppid = :sppId                                   "
            + "     ) AND (                                                  "
            + "             :firstAcc IS NULL                                        "
            + "         OR                                                   "
            + "             lower(sa.fullname) LIKE lower(:firstAcc)              "
            + "     ) AND (                                                  "
            + "             :fromBeginIndate IS NULL                                        "
            + "         OR                                                   "
            + "             sc.begin_indate >= TO_DATE(:fromBeginIndate, 'dd/MM/yyyy')                       "
            + "     ) AND (                                                  "
            + "             :toBeginInDate IS NULL                                        "
            + "         OR                                                   "
            + "             sc.begin_indate < TO_DATE(:toBeginInDate, 'dd/MM/yyyy') + 1                 "
            + "     )  AND (                                                  "
            + "             :status IS NULL                                        "
            + "         OR                                                   "
            + "             sc.status < :status                              "
            + "     )                                                        "
            + " ORDER BY sc.crtdate DESC                                     "
            , nativeQuery = true
    )
    List<Case> getList1(
            @Param("caseCode") String caseCode,
            @Param("caseName") String caseName,
            @Param("sppId") String sppId,
            @Param("firstAcc") String firstAcc,
            @Param("fromBeginIndate") String fromBeginIndate,
            @Param("toBeginInDate") String toBeginInDate,
            @Param("status") String status
    );

    Case findFirstByCaseCode(String caseCode);
}
