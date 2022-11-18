package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Conclusion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface ConclusionRepository extends JpaRepository<Conclusion, String> {
    @Query(value = "SELECT a FROM Conclusion a WHERE 1 = 1 "
            + "AND a.stage = 'G'"
            + "AND(:concId IS NULL OR a.concId = :concId)"
            + "AND(:content IS NULL OR a.content LIKE :content)"
            + "AND(:status IS NULL OR a.status = :status)"
    )
    List<Conclusion> getList(
            @Param("concId") String concId,
            @Param("content") String content,
            @Param("status") String status
    );
}
