package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Spp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SppRepository extends JpaRepository<Spp, String> {
    List<Spp> findByStatus(String status);

    @Query(value =
            "SELECT s.* " +
                    "  FROM lst_spp s WHERE s.status = 'Y' " +
                    "START WITH sppid = (SELECT NVL (departid, sppid) " +
                    "                      FROM adm_users " +
                    "                     WHERE userid = :username ) " +
                    "CONNECT BY PRIOR sppid = s.sppparent ", nativeQuery = true
//            "CONNECT BY PRIOR sppid = s.sppparent ORDER BY s.isdepart DESC, s.NAME ASC", nativeQuery = true
    )
    List<Spp> findByUsername(@Param("username") String username);

    List<Spp> findBySppParentIsNull();

    List<Spp> findBySppParent(String sppParent);

    @Query(value =
            "SELECT s.* " +
                    "  FROM lst_spp s " +
                    " WHERE s.status = 'Y' AND sppid = (SELECT NVL (departid, sppid) " +
                    "                      FROM adm_users " +
                    "                     WHERE userid = :username ) ", nativeQuery = true
    )
    Spp findFirstByUsername(@Param("username") String username);

    @Query(value = "SELECT a FROM Spp a WHERE 1 = 1 "
            + "AND (:sppId IS NULL OR a.sppId = :sppId) "
            + "AND (:name IS NULL OR upper(a.name) LIKE upper(:name) ) "
            + "AND (:address IS NULL OR a.address LIKE :address) "
            + "AND (:status IS NULL OR a.status = :status) "
    )
    List<Spp> getList(
            @Param("sppId") String sppId,
            @Param("name") String name,
            @Param("address") String address,
            @Param("status") String status
    );
}
