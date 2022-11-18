package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface DecisionRepository extends JpaRepository<Decision, String> {
    List<Decision> findByUseForAndStatus(String useFor, String status);


    @Query("SELECT new Decision(t.deciId, t.name) FROM Decision t WHERE " +
            "(:deciId IS NULL OR t.deciId = :deciId) AND " +
            "(:name IS NULL OR UPPER(t.name) LIKE UPPER(:name) ) AND " +
            "(:applyFor IS NULL OR t.applyFor = :applyFor) AND " +
            "(t.useFor like 'G1%' OR t.useFor like '%G2%' OR t.useFor like '%Tbtg%') AND " +
            "(:status IS NULL OR t.status = :status) ORDER BY t.deciId DESC")
    List<Decision> getListForDropbox(@Param("deciId") String deciId,
                                     @Param("name") String name,
                                     @Param("applyFor") String applyFor,
                                     @Param("status") String status);

    @Query(value = "SELECT t FROM Decision t WHERE 1 = 1 "
            + "AND (:status IS NULL OR t.status = :status) "
    )
    List<Decision> findByStatus(@Param("status") String status);
}
