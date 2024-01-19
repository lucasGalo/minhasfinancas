package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Renda;
import com.galo.minhasfinancas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Integer> {

  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From compra c where c.guid = :id", nativeQuery = true)
  List<Compra> findAllUsuario(@Param("id") int id);

  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From compra c where c.guid = :id and DATE_FORMAT(c.date ,'%Y') = :ano", nativeQuery = true)
  List<Compra> findAllUsuarioAndAno(@Param("id") int id, @Param("ano") int ano);

}
