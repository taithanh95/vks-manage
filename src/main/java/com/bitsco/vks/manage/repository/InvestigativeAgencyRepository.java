package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.InvestigativeAgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Tai Thanh <taithanh95.dev@gmail.com>
 * Date: 12/10/2022
 * Time: 3:41 PM
 */
@RepositoryRestResource
public interface InvestigativeAgencyRepository extends JpaRepository<InvestigativeAgency, String> {
    @Query(value = "SELECT a FROM InvestigativeAgency a WHERE 1 = 1 "
            + "AND (:invesCode IS NULL OR a.invesCode = :invesCode) "
            + "AND (:name IS NULL OR upper(a.name) LIKE upper(:name)) "
    )
    List<InvestigativeAgency> getList(
            @Param("invesCode") String invesCode,
            @Param("name") String name
    );
}
