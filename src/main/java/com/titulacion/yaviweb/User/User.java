package com.titulacion.yaviweb.User;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.titulacion.yaviweb.Rol.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Register")

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id_register;
    @Column(length = 50)
    private String username;
    @Column(length = 50)
    private String last_name;
    @Column(length = 9, unique = true)
    private Integer number;
    @Column(length = 100, unique = true)
    private String email;
    @Column
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName="id_rol")
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((rol.getName())));
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
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
