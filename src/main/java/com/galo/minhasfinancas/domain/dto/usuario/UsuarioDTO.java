package com.galo.minhasfinancas.domain.dto.usuario;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galo.minhasfinancas.domain.dto.CompraDTO;
import com.galo.minhasfinancas.domain.dto.DTO;
import com.galo.minhasfinancas.domain.dto.ImgDTO;
import com.galo.minhasfinancas.domain.entity.Compra;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.Genero;
import com.galo.minhasfinancas.domain.enums.Perfil;
import com.galo.minhasfinancas.sources.ImageUtils;
import com.galo.logstach.group.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.reflectionCuston.ReflexaoCustom.custom;
import static com.galo.minhasfinancas.sources.FilesUtil.pathUsuarios;
import static com.galo.minhasfinancas.sources.LocalDateUtil.subtrair;

public class UsuarioDTO extends DTO {

  @JsonIgnore
  @Autowired
  protected Logs logs;
  private Genero genero;
  public byte[] imgbytes;
  public String foto;

  public ImgDTO img;
  private String email;
  private String senha;
  private String identificacao;
  @JsonFormat(pattern = DD_MM_YYYY)
  @DateTimeFormat(pattern = YYYY_MM_DD, iso = DateTimeFormat.ISO.TIME)
  private Date nascimento;
  private String telefone1;
  private String telefone2;
  private Set<Integer> perfis = new HashSet<>();
  private List<CompraDTO> compras;

  public UsuarioDTO() {
  }

  public UsuarioDTO(Usuario obj, String classe) {
    if (obj != null) {
      setDefault(obj);
      this.genero = obj.getGenero();
      this.identificacao = obj.getIdentificacao();
      this.nascimento = obj.getNascimento();
      this.email = obj.getEmail();
      this.senha = obj.getSenha();
      this.perfis = obj.getPerfis();
      this.telefone1 = obj.getTelefone1();
      this.telefone2 = obj.getTelefone2();
      this.img = new ImgDTO(obj.getImg());
      if (!Objects.equals(classe, Compra.class.getSimpleName()) && null != obj.getCompras() && obj.getCompras().size() > 0)
        this.compras = custom(obj.getCompras(), CompraDTO.class,  Usuario.class);

    }
  }

  public List<CompraDTO> getCompras() {return compras;}
  public void setCompras(List<CompraDTO> compras) {this.compras = compras;}
  public ImgDTO getImg() {
    return img;
  }
  public void setImg(ImgDTO img) {
    this.img = img;
  }
  public String getFoto() {
    return foto;
  }
  public void setFoto(String foto) {
    this.foto = foto;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getSenha() {
    return senha;
  }
  public void setSenha(String senha) {
    this.senha = senha;
  }
  public String getIdentificacao() {
    return identificacao;
  }
  public void setIdentificacao(String identificacao) {
    this.identificacao = identificacao;
  }
  public String getTelefone1() {
    return telefone1;
  }
  public void setTelefone1(String telefone1) {
    this.telefone1 = telefone1;
  }
  public String getTelefone2() {
    return telefone2;
  }
  public void setTelefone2(String telefone2) {
    this.telefone2 = telefone2;
  }
  public Set<Integer> getPerfis() {
    return perfis;
  }
  public void setPerfis(Set<Integer> perfis) {
    this.perfis = perfis;
  }
  public Date getNascimento() {
    return nascimento;
  }
  public void setNascimento(Date nascimento) {
    this.nascimento = nascimento;
  }

  public long getIdade() {
    if (getNascimento() == null) return 0;
    return subtrair(getNascimento(), new Date(), ChronoUnit.YEARS);
  }

  public byte[] getImgbytes() {
    return imgbytes;
  }

  public void setImgbytes(byte[] imgbytes) {
    this.imgbytes = imgbytes;
  }

  public String base64Img() {
    if (img == null || img.getUrl().isEmpty()) return "";
    return ImageUtils.base64(pathUsuarios + img.getNome());
  }

  public Genero getGenero() {
    return genero;
  }

  public void setGenero(Genero genero) {
    this.genero = genero;
  }

  public String getPerfis(Set<Integer> perfis) {
    List<Perfil> collect = perfis.stream().map(Perfil::toEnum).collect(Collectors.toList());
    return collect.stream().map(Perfil::getValue).collect(Collectors.joining(","));
  }
}

