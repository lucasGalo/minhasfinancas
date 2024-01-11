package com.galo.minhasfinancas.domain.mappers;

import com.galo.minhasfinancas.domain.dto.CategoriaDTO;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.ItemDTO;
import com.galo.minhasfinancas.domain.dto.PagamentoDTO;
import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Categoria;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.domain.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;
import java.util.List;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.DateUtil.DD_MM_YYYY;
import static com.galo.minhasfinancas.sources.DateUtil.toDate;

@Mapper(componentModel = "spring")
public interface CompraMapper extends MAPPER<CompraDTO, Compra> {
  @Mapping(source = "items", target = "items", qualifiedByName = "itemsDTO")
  @Mapping(source = "pagamentos", target = "pagamentos", qualifiedByName = "pagamentosDTO")
  @Mapping(source = "usuario", target = "usuario", qualifiedByName = "usuarioDTO")
  @Mapping(source = "categoria", target = "categoria", qualifiedByName = "categoriaDTO")
  CompraDTO toDto(Compra obj);

  @Mapping(source = "usuario.nascimento", target = "usuario.nascimento", qualifiedByName = "nascimento")
  Compra toObj(CompraDTO dto);

  @Named("nascimento")
  static Date nascimento(String nascimento) {
    if (nascimento.isEmpty()) new Date();
    return toDate(nascimento.replace("/", "-"), DD_MM_YYYY);
  }

  @Named("itemsDTO") static List<ItemDTO> itemsDTO(List<Item> itens) {return custom(itens, ItemDTO.class, Compra.class);}
  @Named("pagamentosDTO") static List<PagamentoDTO> pagamentosDTO(List<Pagamento> pagamentos) {
    return custom(pagamentos, PagamentoDTO.class, Compra.class);
  }
  @Named("usuarioDTO") static UsuarioDTO usuarioDTO(Usuario usuario) {
    return custom(usuario, UsuarioDTO.class, Compra.class);
  }
  @Named("categoriaDTO") static CategoriaDTO categoriaDTO(Categoria categoria) {
    return custom(categoria, CategoriaDTO.class, Compra.class);
  }
}