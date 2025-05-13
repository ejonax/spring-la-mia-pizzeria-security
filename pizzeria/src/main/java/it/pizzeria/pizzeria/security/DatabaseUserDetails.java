package it.pizzeria.pizzeria.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.pizzeria.pizzeria.model.Role;
import it.pizzeria.pizzeria.model.User;

public class DatabaseUserDetails implements UserDetails {

    private final Integer id;
    private final String username;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public DatabaseUserDetails(User user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.authorities=new ArrayList<>();
        for (Role role : user.getRoles()) {
            this.authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            
        }
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

}
