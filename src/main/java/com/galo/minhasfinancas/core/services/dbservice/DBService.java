package com.galo.minhasfinancas.core.services.dbservice;

import com.galo.minhasfinancas.dataprovider.repositories.CompraRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ContatoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ItemRepository;
import com.galo.minhasfinancas.dataprovider.repositories.PagamentoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.PesquisaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.TelemetriaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.UsuarioRepository;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Contato;
import com.galo.minhasfinancas.domain.entity.Img;
import com.galo.minhasfinancas.domain.entity.Item;
import com.galo.minhasfinancas.domain.entity.Pesquisa;
import com.galo.minhasfinancas.domain.entity.Telemetria;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.Genero;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.domain.enums.StatusCompra;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.galo.minhasfinancas.sources.FilesUtil.pathUsuarios;

@Service
public class DBService {

  private final UsuarioRepository usuarioRepository;
  private final ItemRepository ItemRepository;
  private final CompraRepository compraRepository;
  private final PagamentoRepository pagamentoRepository;
  private final ContatoRepository contatoRepository;
  private final PesquisaRepository pesquisaRepository;
  private final TelemetriaRepository telemetriaRepository;
  private final BCryptPasswordEncoder encode;
  private final Mock mock;

  @Autowired
  public DBService(UsuarioRepository usuarioRepository, ItemRepository ItemRepository, CompraRepository compraRepository, PagamentoRepository pagamentoRepository, ContatoRepository contatoRepository, PesquisaRepository pesquisaRepository, TelemetriaRepository telemetriaRepository, BCryptPasswordEncoder encode, Mock mock) {
    this.usuarioRepository = usuarioRepository;
    this.ItemRepository = ItemRepository;
    this.compraRepository = compraRepository;
    this.pagamentoRepository = pagamentoRepository;
    this.contatoRepository = contatoRepository;
    this.pesquisaRepository = pesquisaRepository;
    this.telemetriaRepository = telemetriaRepository;
    this.encode = encode;
    this.mock = mock;
  }

  public void instantiateTestDatabase() {

    if (true) {
      mock.setServicos(1);
    } else {

      String senha = encode.encode("123");

      final Img img1 = new Img(null, true, "galo.jpeg", pathUsuarios + "galo.jpeg");
      final Img img2 = new Img(null, true, "aranha.jpg", pathUsuarios + "aranha.jpg");
      final Img img3 = new Img(null, true, "batman.jpg", pathUsuarios + "batman.jpg");
      final Img img4 = new Img(null, true, "ferro.jpg", pathUsuarios + "ferro.jpg");
      final Img img5 = new Img(null, true, "america.jpg", pathUsuarios + "america.jpg");

      final Usuario usuario = new Usuario(null, true, "Lucas Galo",  "galo_pc@outlook.com", senha, "384.153.708-14", "20-08-1990", "(16)981037835", "(16)981037835", Genero.MASCULINO, List.of(Perfil.ADMIN), img1, null);
      final Usuario usuario2 = new Usuario(null, true, "Fulano de tal",  "fulano@teste.com", senha, "384.153.708-14", "05-05-1965", "(16)912345678", "(16)912345678", Genero.MASCULINO, List.of(Perfil.CLIENTE), img2, null);
      final Usuario usuario3 = new Usuario(null, true, "Beltrano de tal",  "beltrano@teste.com", senha, "28.848.858/0001-44", "05-05-1965", "(16)998745878", "(16)998745878", Genero.MASCULINO, List.of(Perfil.CLIENTE), img3, null);
      final Usuario usuario4 = new Usuario(null, true, "Joao da Silva",  "joao@teste.com", senha, "384.153.708-14", "05-05-1965", "(16)968578421", "(16)968578421", Genero.MASCULINO, List.of(Perfil.CLIENTE), img4, null);
      final Usuario usuario5 = new Usuario(null, true, "Ciclano de tal",  "ciclano@teste.com", senha, "384.153.708-14", "05-05-1965", "(16)987487521", "(16)987487521", Genero.MASCULINO, List.of(Perfil.OPERADOR), img5, null);
      usuarioRepository.saveAll(List.of(usuario, usuario2, usuario3, usuario4, usuario5));

      final Compra compra1 = new Compra(null, true, "Rol 1", StatusCompra.CONCLUIDA, 0.00, "", usuario3, null, null, null);
      final Compra compra2 = new Compra(null, true, "Rol 2", StatusCompra.CONCLUIDA, 0.00,"", usuario2, null, null,null);
      final Compra compra3 = new Compra(null, true, "Rol 3", StatusCompra.CONCLUIDA, 0.00,"", usuario2, null, null, null);
      final Compra compra4 = new Compra(null, true, "Rol 4", StatusCompra.CONCLUIDA, 0.00,"", usuario3, null, null, null);
      final Compra compra5 = new Compra(null, true, "Rol 5", StatusCompra.CONCLUIDA, 0.00,"", usuario2, null, null, null);
      compraRepository.saveAll(List.of(compra1, compra2, compra3, compra4, compra5));


      final Item item1 = new Item(null, true, "Item 1", "", "123", 3.00, 1.00, 3.00, compra1);
      ItemRepository.saveAll(List.of(item1));

    }

    final Contato contato = new Contato(null, false, "", "email@email.com", "msg padrão", "Resposta padrão", new Date());
    contatoRepository.save(contato);

    final Pesquisa pesquisa = new Pesquisa(null, true, "Pesquisa teste", "192.168.1.100");
    pesquisaRepository.save(pesquisa);

    final Telemetria telemetria = new Telemetria(null, true, "Telemetria 1", "sessio: 1239-3i12", "192.168.1.100", -23.999, 23.000, "", 1, null);
    telemetriaRepository.save(telemetria);

  }

}

