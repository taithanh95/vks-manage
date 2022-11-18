package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Law;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LawRepository extends JpaRepository<Law, String> {
    @Query(value = "SELECT a FROM Law a WHERE 1 = 1 "
            + "AND(:lawId IS NULL OR a.lawId = :lawId)"
            + "AND(:lawName IS NULL OR a.lawName LIKE :lawName)"
            + "AND(:codeId IS NULL OR a.codeId = :codeId)"
            + "AND(:status IS NULL OR a.status = :status)"
    )
    List<Law> getList(
            @Param("lawId") String lawId,
            @Param("lawName") String lawName,
            @Param("codeId") String codeId,
            @Param("status") String status
    );

    @Query("SELECT new Law(t.lawCode, t.lawId, t.item, t.point, t.lawName) FROM Law t WHERE " +
            "(:lawId IS NULL OR t.lawId = :lawId) AND " +
            "(:lawName IS NULL OR t.lawName LIKE :lawName) ")
    List<Law> getListForDropbox(@Param("lawId") String lawId,
                                @Param("lawName") String lawName);

    Page<Law> findAll(Pageable pageable);

    List<Law> findByCodeIdAndItemIsNullAndPointIsNullOrderByLawId(String codeId);

    List<Law> findByCodeIdAndLawIdAndPointIsNullAndItemIsNotNullOrderByItem(String codeId, String lawId);

    List<Law> findByCodeIdAndLawIdAndItemAndPointIsNotNullOrderByPoint(String codeId, String lawId, String item);
}
