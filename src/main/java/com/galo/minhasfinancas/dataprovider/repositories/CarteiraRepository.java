package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Carteira;
import com.galo.minhasfinancas.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarteiraRepository extends JpaRepository<Carteira, Integer> {

  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From carteira c where c.guid = :id", nativeQuery = true)
  List<Carteira> findAllUsuario(@Param("id") int id);
}
