package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.SppUpperCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SppUpperCaseRepository extends JpaRepository<SppUpperCase, String> {
    @Query(value =
            "SELECT s.* " +
                    "  FROM lst_spp s " +
                    "START WITH (sppid = :SPPID)" +
                    "CONNECT BY PRIOR sppid = s.sppparent", nativeQuery = true
    )
    List<SppUpperCase> findAllByParent(@Param("SPPID") String SPPID);
}
