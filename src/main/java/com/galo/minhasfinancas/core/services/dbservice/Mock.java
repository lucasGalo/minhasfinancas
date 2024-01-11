package com.galo.minhasfinancas.core.services.dbservice;

import com.galo.minhasfinancas.dataprovider.repositories.ItemRepository;
import com.galo.minhasfinancas.dataprovider.repositories.PagamentoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.CompraRepository;
import com.galo.minhasfinancas.dataprovider.repositories.UsuarioRepository;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Img;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.entity.Pagamento;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.BaseEnum;
import com.galo.minhasfinancas.domain.enums.Genero;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.enums.StatusItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.galo.minhasfinancas.sources.FilesUtil.pathUsuarios;

@Service
public class Mock {
  private final UsuarioRepository usuarioRepository;
  private final ItemRepository ItemRepository;
  private final CompraRepository compraRepository;
  private final PagamentoRepository pagamentoRepository;
  private final BCryptPasswordEncoder encode;

  @Autowired
  public Mock(UsuarioRepository usuarioRepository, ItemRepository ItemRepository, CompraRepository compraRepository, PagamentoRepository pagamentoRepository, BCryptPasswordEncoder encode) {
    this.usuarioRepository = usuarioRepository;
    this.ItemRepository = ItemRepository;
    this.compraRepository = compraRepository;
    this.pagamentoRepository = pagamentoRepository;
    this.encode = encode;
  }

  public void setServicos(int qtd) {
    final Img img = new Img(null, true, "galo.jpeg", pathUsuarios + "galo.jpeg");
    final Usuario lucas = new Usuario(null, true, "Lucas Galo", "galo_pc@outlook.com", encode.encode("123"), "384.153.708-14",  "20-08-1990", "(16)981037835", "(16)981037835", Genero.MASCULINO, List.of(Perfil.ADMIN), img,  null);

    final Img imgJoao = new Img(null, true, "ferro.jpg", pathUsuarios + "ferro.jpg");
    final Usuario joao = new Usuario(null, true, "Joao da Silva", "joao@teste.com", encode.encode("123"), "384.153.708-14",  "05-05-1965", "(16)968578421", "(16)968578421", Genero.MASCULINO, List.of(Perfil.OPERADOR), imgJoao,  null);
    usuarioRepository.saveAll(List.of(lucas, joao));


    Random gerador = new Random();
    double valor;
    double desc;

    Usuario usuario;
    String senha = encode.encode("123");

    Compra compra;
    Item item;
    Pagamento pgto;

    StatusItem statusItem;
    StatusCompra statusCompra;

    int repeticao;
    int acumulado;
    int base;
    CompraDTO compraDTO;
    int lavadorid;
    int lavadorid2;
    int prat;


    for (int i = 0; i < 5; i++) {
      usuario = new Usuario(null, true, "Usuario " + i, "lavador" + i + "@outlook.com", senha, "384.153.708-14","20-08-1990", "(16)981037835", "(16)981037835", Genero.MASCULINO, List.of(Perfil.LAVADOR), null, null);
      usuarioRepository.save(usuario);
    }

    for (int i = 0; i < qtd; i++) {

      repeticao = getIndex(gerador);
      if(repeticao % 2 == 0) {
        repeticao = getIndex(gerador);
      }

      valor = gerador.nextInt(200);
      usuario = new Usuario(null, true, "Usuario " + i, "usuario" + i + "@outlook.com", senha, "384.153.708-14","20-08-1990", "(16)981037835", "(16)981037835", Genero.MASCULINO, List.of(Perfil.CLIENTE), null, null);
      usuarioRepository.save(usuario);


      repeticao = getIndex(gerador);
      if (valor % 2 == 0 && repeticao % 2 == 0)
        for (int j = 0; j < repeticao; j++) {

          base = getIndex(gerador);
          statusCompra = (StatusCompra) BaseEnum.getEnumInstance(StatusCompra.values(), base);

          if (statusCompra != StatusCompra.CONCLUIDA) {
            compra = new Compra(null, true, "Rol " + i + "-" + j, statusCompra,0.00, "", usuario, null, null, null);
            compra.setGuid(usuario.getId());
            compra.setItems(new ArrayList<>());
            compra.setPagamentos(new ArrayList<>());
            compraRepository.save(compra);

            if (statusCompra == StatusCompra.PENDENTE) {
              statusItem = StatusItem.PRONTO_RETIRADA;
            } else {
              statusItem = (StatusItem) BaseEnum.getEnumInstance(StatusItem.values(), statusCompra.getValue());
            }

            acumulado = gerador.nextInt(6) + 1;

            for (int k = 0; k < acumulado; k++) {
              valor = gerador.nextInt(10) + 1;
              desc = gerador.nextInt(10);
              lavadorid = getIndex(gerador);
              prat =  gerador.nextInt(5) ;
              do {
                lavadorid2 = getIndex(gerador);
              }while(lavadorid!= lavadorid2);

              item = new Item(null, true, "Item " + i + "-" + j + "-" + k, "","",1, valor, valor, compra);
              ItemRepository.save(item);
              compra.getItems().add(item);
            }
            compraDTO = new CompraDTO(compra, CompraDTO.class.getSimpleName());
            compraRepository.save(compra);
          }
        }
    }
  }

  private static int getIndex(Random gerador) {
    return gerador.nextInt(2);
  }
}

