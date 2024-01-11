package com.galo.minhasfinancas.config.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.galo.minhasfinancas.domain.entity.Img;
import com.galo.minhasfinancas.domain.enums.Perfil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Set<Perfil> perfis;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
    private Img img;

    public UserSS() {
    }

    public UserSS(Integer id, String nome, String email, String senha, Set<Perfil> perfis, String token, Img img ) {
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getValue())).collect(Collectors.toList());
        this.token = token;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public void setId(Integer id) {this.id = id;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getSenha() {return senha;}

    public void setSenha(String senha) {this.senha = senha;}

    public Set<Perfil> getPerfis() {return perfis;}

    public void setPerfis(Set<Perfil> perfis) {this.perfis = perfis;}
    public Img getImg() {return img;}
    public void setImg(Img img) {this.img = img;}

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities == null && perfis != null )
            this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getValue())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserSS{");
        sb.append("id=").append(id);
        sb.append(", nome='").append(nome).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", senha='").append(senha).append('\'');
        sb.append(", authorities=").append(authorities);
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
