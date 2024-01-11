package com.galo.minhasfinancas.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dashboard")
public class Dashboard extends EntityBase {
    @OneToMany
    @Transient
    private List<Usuario> usuarios = new ArrayList<>();
    @OneToMany
    @Transient
    private List<Compra> compras = new ArrayList<>();

    public Dashboard() {}
    public Dashboard(Integer id, Boolean isAtivo, String nome, List<Compra> compras) {
        super(id, isAtivo, nome, null);
        this.compras = compras;
    }
    public List<Compra> getRols() {return compras;}
    public void setRols(List<Compra> compras) {this.compras = compras;}
    public List<Usuario> getUsuarios() {return usuarios;}
    public void setUsuarios(List<Usuario> usuarios) {this.usuarios = usuarios;}

    @Override
    public String toString() {
        return "Dashboard{" + "rols=" + compras + '}';
    }
}
