package com.galo.minhasfinancas.domain.entity;

import com.galo.minhasfinancas.domain.enums.EstadoLogin;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "logins")
final public class Login extends EntityBase {

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_login")
    private EstadoLogin estadoLogin;
    private String msg;
    @Column(name = "token", length = 350)
    private String token;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Login() {
    }

    public Login(Integer id, Boolean isAtivo, String nome, EstadoLogin estadoLogin, String msg, String token, Usuario usuario) {
        super(id, isAtivo, nome, null);
        this.estadoLogin = estadoLogin;
        this.msg = msg;
        this.token = token;
        this.usuario = usuario;
    }

    public EstadoLogin getEstadoLogin() {
        return estadoLogin;
    }

    public void setEstadoLogin(EstadoLogin estadoLogin) {
        this.estadoLogin = estadoLogin;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Login{");
        sb.append("estadoLogin=").append(estadoLogin);
        sb.append(", msg='").append(msg).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }
}
