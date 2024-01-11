package com.galo.minhasfinancas.framework.api.operation;

import com.galo.minhasfinancas.config.beans.HttpRequest;
import com.galo.minhasfinancas.dataprovider.other.ipinfo.Ipinfo;
import com.galo.minhasfinancas.dataprovider.repositories.CarteiraRepository;
import com.galo.minhasfinancas.dataprovider.repositories.CategoriaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ClientinfoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.CompraRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ContatoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.DashboardRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ImgRepository;
import com.galo.minhasfinancas.dataprovider.repositories.ItemRepository;
import com.galo.minhasfinancas.dataprovider.repositories.PagamentoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.PesquisaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.RendaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.TelemetriaRepository;
import com.galo.minhasfinancas.dataprovider.repositories.TipoRepository;
import com.galo.minhasfinancas.dataprovider.repositories.UsuarioRepository;
import com.galo.logstach.group.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class IBaseRepository<J extends JpaRepository> extends OperationCommuns {

    public J repo;

    @Autowired public UsuarioRepository usuarioRepository;
    @Autowired public CompraRepository compraRepository;
    @Autowired public ItemRepository itemRepository;
    @Autowired public PagamentoRepository pagamentoRepository;
    @Autowired public DashboardRepository dashboardRepository;
    @Autowired public ImgRepository imgRepository;
    @Autowired public ContatoRepository contatoRepository;
    @Autowired public PesquisaRepository pesquisaRepository;
    @Autowired public TelemetriaRepository telemetriaRepository;
    @Autowired public ClientinfoRepository clientinfoRepository;
    @Autowired public CarteiraRepository carteiraRepository;
    @Autowired public CategoriaRepository categoriaRepository;
    @Autowired public TipoRepository tipoRepository;
    @Autowired public RendaRepository rendaRepository;

    @Autowired public HttpRequest request;
    @Autowired public Logs logs;
    @Autowired public Ipinfo ipinfo;

    public IBaseRepository(J repo) {
        this.repo = repo;
    }
    public IBaseRepository() {}
}
