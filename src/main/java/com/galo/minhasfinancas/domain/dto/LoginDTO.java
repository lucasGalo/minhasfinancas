package com.galo.minhasfinancas.domain.dto;

import com.galo.minhasfinancas.domain.dto.usuario.UsuarioDTO;
import com.galo.minhasfinancas.domain.entity.Login;
import com.galo.minhasfinancas.domain.entity.Usuario;
import com.galo.minhasfinancas.domain.enums.EstadoLogin;

import java.util.Objects;

public class LoginDTO extends DTO {
    private EstadoLogin estadoLogin;
    private String msg;
    private String token;
    private String username;
    private String password;
    private Integer usuarioId;
    private UsuarioDTO usuario;

    public LoginDTO() {
    }

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDTO(Login obj, String classe) {
        if(null != obj) {
            setId(obj.getId());
            setNome(obj.getNome());
            setDescricao(obj.getDescricao());
            setAtivo(obj.getAtivo());
            setUpdateAt(obj.getUpdateAt());
            setCreateAt(obj.getCreateAt());
            setEstadoLogin(obj.getEstadoLogin());
            setMsg(obj.getMsg());
            setToken(obj.getToken());
            if(!Objects.equals(classe, Usuario.class.getSimpleName()))
                setUsuario(new UsuarioDTO(obj.getUsuario(), Login.class.getSimpleName()));
        }
    }

    public LoginDTO(EstadoLogin estadoLogin, String msg, Integer usuarioId, String username, String token) {
        setAtivo(true);
        this.estadoLogin = estadoLogin;
        this.msg = msg;
        this.usuarioId = usuarioId;
        this.username = username;
        this.token = token;
    }

    public EstadoLogin getEstadoLogin() {return estadoLogin;}

    public void setEstadoLogin(EstadoLogin estadoLogin) {this.estadoLogin = estadoLogin;}

    public String getMsg() {return msg;}

    public void setMsg(String msg) {this.msg = msg;}

    public String getToken() {return token;}

    public void setToken(String token) {this.token = token;}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsuarioId() {return usuarioId;}

    public void setUsuarioId(Integer usuarioId) {this.usuarioId = usuarioId;}

    public UsuarioDTO getUsuario() {return usuario;}

    public void setUsuario(UsuarioDTO usuario) {this.usuario = usuario;}
}
