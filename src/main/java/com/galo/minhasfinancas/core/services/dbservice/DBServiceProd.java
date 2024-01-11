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
public class DBServiceProd {

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
  public DBServiceProd(UsuarioRepository usuarioRepository, ItemRepository ItemRepository, CompraRepository compraRepository, PagamentoRepository pagamentoRepository, ContatoRepository contatoRepository, PesquisaRepository pesquisaRepository, TelemetriaRepository telemetriaRepository, BCryptPasswordEncoder encode, Mock mock) {
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
    if (false) {
      mock.setServicos(100);
    } else {

      String senha = encode.encode("123");
      String senha2 = encode.encode("2211");

      final Img img1 = new Img(null, true, "galo.jpeg", pathUsuarios + "galo.jpeg");
      final Img img2 = new Img(null, true, "ambientecleanrp@gmail.com.jpg", pathUsuarios + "ambientecleanrp@gmail.com.jpg");

      final Usuario usuario = new Usuario(null, true, "Lucas Galo", "galo_pc@outlook.com", senha, "384.153.708-14", "20-08-1990", "(16)981037835", "(16)981037835", Genero.MASCULINO, List.of(Perfil.ADMIN, Perfil.CLIENTE), img1,  null);
      final Usuario usuario2 = new Usuario(null, true, "Cristiano vizzoto menezes", "ambientecleanrp@gmail.com", senha2, "221.187.698-65", "20-08-1990", "(16)997901023", "(16)997901023", Genero.MASCULINO, List.of(Perfil.CLIENTE), img2, null);
      usuarioRepository.saveAll(List.of(usuario, usuario2));

      final Compra compra1 = new Compra(null, true, "Rol 1", StatusCompra.CONCLUIDA,0.00, "",usuario2, null, null, null);
      compraRepository.saveAll(List.of(compra1));

      final Item item1 = new Item(null, true, "Item 1", "", "123", 3.00, 1.00, 3.00, compra1);
      ItemRepository.saveAll(List.of(item1));

      final Contato contato = new Contato(null, false, "", "email@email.com", "msg padrão", "Resposta padrão", new Date());
      contatoRepository.save(contato);

      final Pesquisa pesquisa = new Pesquisa(null, true, "Pesquisa teste", "192.168.1.100");
      pesquisaRepository.save(pesquisa);

      final Telemetria telemetria = new Telemetria(null, true, "Telemetria 1", "sessio: 1239-3i12", "192.168.1.100", -23.999, 23.000, "", usuario.getId(), null);
      telemetriaRepository.save(telemetria);
    }
  }
}