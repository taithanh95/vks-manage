package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Inspector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface InspectorRepository extends JpaRepository<Inspector, String> {
    @Query(value = "SELECT a FROM Inspector a WHERE 1 = 1 "
            + "AND (:inspCode IS NULL OR a.inspCode = :inspCode) "
            + "AND (:fullName IS NULL OR upper(a.fullName) like upper(:fullName)) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Inspector> getList(
            @Param("inspCode") String inspCode,
            @Param("fullName") String fullName,
            @Param("status") String status
    );

    @Query(value = "SELECT a.* FROM lst_inspector a WHERE 1 = 1 "
            + "AND (:inspCode IS NULL OR a.inspCode = :inspCode) "
            + "AND (:fullName IS NULL OR upper(a.fullName) like upper(:fullName)) "
            + "AND (:status IS NULL OR a.status = :status)"
            + "AND (:username IS NULL OR a.sppid = (SELECT nvl(au.departid, au.sppid) FROM adm_users au WHERE au.userid = :username))"
            , nativeQuery = true
    )
    List<Inspector> getListByUsername(
            @Param("inspCode") String inspCode,
            @Param("fullName") String fullName,
            @Param("status") String status,
            @Param("username") String username
    );
}
