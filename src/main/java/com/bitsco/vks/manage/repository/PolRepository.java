package com.bitsco.vks.manage.repository;

import com.bitsco.vks.manage.entities.Pol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface PolRepository extends JpaRepository<Pol, String> {
    @Query(value = "SELECT a FROM Pol a WHERE 1 = 1 "
            + "AND (:name IS NULL OR a.name LIKE :name) "
    )
    List<Pol> getList(
            @Param("name") String name
    );
}
