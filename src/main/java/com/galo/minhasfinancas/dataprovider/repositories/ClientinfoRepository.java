package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Clientinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientinfoRepository extends JpaRepository<Clientinfo, Integer> {
  @Query(value = "SELECT c.* FROM telemetria t, clientinfo c WHERE c.telemetria_id = :id ORDER by t.id DESC LIMIT 1", nativeQuery = true)
  Clientinfo findClientinfo(@Param("id") int id);
}
