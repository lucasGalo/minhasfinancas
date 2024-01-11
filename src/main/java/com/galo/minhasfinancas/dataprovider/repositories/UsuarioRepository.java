package com.galo.minhasfinancas.dataprovider.repositories;

import com.galo.minhasfinancas.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Transactional(readOnly=true)
    Usuario findByEmail(String email);

    @Transactional(readOnly=true)
    Usuario findByNome(String nome);
    @Transactional(readOnly=true)
    @Query(value = "SELECT u.* From usuario u, perfis p where u.id = p.usuario_id " +
            "and p.perfis = :perfil"
            +" ORDER by u.id  DESC"
            ,nativeQuery = true)
    List<Usuario> findAllUsuarioByPerfil(@Param("perfil") int perfil);

    @Transactional(readOnly=true)
    @Query(value = "SELECT u.* From usuario u, endereco e where u.endereco_id = e.id" +
            " AND UPPER(e.logradouro) LIKE CONCAT('%',UPPER(:logradouro),'%')"
            ,nativeQuery = true)
    List<Usuario> findAllEndereco(@Param("logradouro") String logradouro);
}
