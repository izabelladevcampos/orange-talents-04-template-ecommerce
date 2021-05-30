package br.com.zupacademy.izabella.ecommerce.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Email
    @Column(unique = true)
    private String login;

    @NotBlank
    @Length(min = 6)
    private String senha;

    @PastOrPresent
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    @Deprecated
    public Usuario() {

    }

    public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) SenhaLimpa senha) {
        this.login = login;
        this.senha = senha.hash();

    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

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
        return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instanteCriacao=" + instanteCriacao
                + ", perfis=" + perfis + "]";
    }


}
