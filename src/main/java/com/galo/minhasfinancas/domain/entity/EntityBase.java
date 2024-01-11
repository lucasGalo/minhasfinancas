package com.galo.minhasfinancas.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.galo.minhasfinancas.domain.statics.DataLocal;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EntityBase implements Serializable, DataLocal {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer guid;
    @Column(name = "nome")
    private String nome;
    @Column(name= "is_ativo", columnDefinition = "boolean default true", nullable = false)
    private Boolean isAtivo;
    @Transient
    private boolean isPersistido;
    @Transient
    private String urlPage;

    @JsonFormat(pattern = DD_MM_YYYY_HH_MM_SS)
    @CreationTimestamp
    @Column(name = "create_at")
    private Date createAt;

    @JsonFormat(pattern = DD_MM_YYYY_HH_MM_SS)
    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updateAt;

    private String descricao;
    public EntityBase() {}
    public EntityBase(Integer id, Boolean isAtivo, String nome, String descricao) {
        this.id = id;
        this.isAtivo = isAtivo;
        setNome(nome);
        setDescricao(descricao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityBase identity = (EntityBase) o;
        return Objects.equals(id, identity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {this.nome = nome;}
    public Boolean getAtivo() { return isAtivo; }
    public void setAtivo(Boolean ativo) { isAtivo = ativo == null || ativo; }
    public boolean isPersistido() {
        return isPersistido;
    }
    public void setPersistido(boolean persistido) {
        isPersistido = persistido;
    }
    public Date getCreateAt() { return createAt; }
    public void setCreateAt(Date createAt) { this.createAt = createAt; }
    public Date getUpdateAt() {
        return updateAt;
    }
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
    public String getUrlPage() {return urlPage;}
    public void setUrlPage(String urlPage) {this.urlPage = urlPage;}
    public Integer getGuid() {return guid;}
    public void setGuid(Integer guid) {this.guid = guid;}
    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    @Override
    public String toString() {
        return "EntityBase{" + "id=" + id +
                ", guid=" + guid +
                ", nome='" + nome + '\'' +
                ", isAtivo=" + isAtivo +
                ", isPersistido=" + isPersistido +
                ", urlPage='" + urlPage + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", decsricao='" + descricao + '\'' +
                '}';
    }
}
