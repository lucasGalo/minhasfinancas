package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Telemetria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetriaRepository extends JpaRepository<Telemetria, Integer> {
  @Query(value = "SELECT * FROM telemetria t WHERE t.session = :session ORDER by t.id DESC LIMIT 1", nativeQuery = true)
  Telemetria findTelemetria(@Param("session") String session);

  @Query(value = "SELECT t.* FROM telemetria t WHERE DATE_FORMAT(t.create_at, \"%Y\") = :ano AND t.latitude != 0 AND t.longitude != 0" ,nativeQuery = true)
  List<Telemetria> findAllAno(@Param("ano") String ano);
}
