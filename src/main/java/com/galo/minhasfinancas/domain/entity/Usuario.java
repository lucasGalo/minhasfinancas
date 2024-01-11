package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.galo.minhasfinancas.domain.enums.Genero;
import com.galo.minhasfinancas.domain.enums.Perfil;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.galo.minhasfinancas.sources.DateUtil.toDate;
import static com.galo.minhasfinancas.sources.StringUtil.cleanMask;

@Entity
@Table(name = "usuario")
public class Usuario extends EntityBase {
  @Enumerated(EnumType.STRING)
  @Column(name = "genero", nullable = false)
  private Genero genero;
  @Column(unique = true)
  private String email;
  private String senha;
  private String identificacao;
  private String telefone1;
  private String telefone2;
  @JsonFormat(pattern = DD_MM_YYYY)
  @Column(name = "data_nascimento")
  private Date nascimento;
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "perfis")
  private Set<Integer> perfis = new HashSet<>();
  @OneToMany(mappedBy = "usuario")
  private List<Compra> compras = new ArrayList<>();
  @OneToOne(cascade = CascadeType.ALL)
  private Img img;

  public Usuario(Integer id, Boolean isAtivo, String nome, String email, String senha, String identificacao, String nascimento, String telefone1, String telefone2, Genero genero, List<Perfil> perfis, Img img, List<Compra> compras) {
    super(id, isAtivo, nome, null);
    this.email = email;
    this.senha = senha;
    this.identificacao = identificacao;
    this.nascimento = toDate(nascimento, DD_MM_YYYY);
    this.telefone1 = telefone1;
    this.telefone2 = telefone2;
    this.img = img;
    this.genero = genero;
    this.perfis = perfis.stream().map(Perfil::getCod).collect(Collectors.toSet());
    this.compras = compras;
  }

  public Usuario() {}

  @PrePersist
  @PreUpdate
  private void prePersist() {
    identificacao = cleanMask(identificacao);
    telefone1 = cleanMask(telefone1);
    telefone2 = cleanMask(telefone2);
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
  public Set<Integer> getPerfis() {
    return perfis;
  }
  public void setPerfis(Set<Integer> perfis) {
    this.perfis = perfis;
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
  public Date getNascimento() {return nascimento;}
  public void setNascimento(Date nasimento) {this.nascimento = nasimento;}
  public Genero getGenero() {return genero;}
  public void setGenero(Genero genero) {this.genero = genero;}
  public Img getImg() {return img;}
  public void setImg(Img img) {this.img = img;}
  public List<Compra> getCompras() {return compras;}
  public void setCompras(List<Compra> compras) {this.compras = compras;}
}