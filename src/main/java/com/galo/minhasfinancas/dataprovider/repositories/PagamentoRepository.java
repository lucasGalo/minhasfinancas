package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

  @Transactional(readOnly=true)
  @Query(value = "SELECT c.* From pagamento c where c.guid = :id", nativeQuery = true)
  List<Pagamento> findAllUsuario(@Param("id") int id);
}
