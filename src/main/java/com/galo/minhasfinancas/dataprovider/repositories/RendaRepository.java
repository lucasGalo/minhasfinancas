package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Renda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RendaRepository extends JpaRepository<Renda, Integer> {

  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From renda c where c.guid = :id", nativeQuery = true)
  List<Renda> findAllUsuario(@Param("id") int id);


  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From renda c where c.guid = :id and DATE_FORMAT(c.date ,'%Y') = :ano", nativeQuery = true)
  List<Renda> findAllUsuarioAndAno(@Param("id") int id, @Param("ano") int ano);
}
