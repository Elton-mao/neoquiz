package com.neoquiz.api.neoquizapi.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String login; 
    private String password;
    private UserRoles role;
    public User (String login, String password, UserRoles roles){
        this.login = login; 
        this.password = password;
        this.role = roles;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @Override
    public String getUsername() {
    
      //  throw new UnsupportedOperationException("Unimplemented method 'getUsername'");  
        return login;
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
}
