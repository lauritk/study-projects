package fi.jyu.soa.rest.security.demo4.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Entity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(value = "users", noClassnameStored = true)
public class Users extends BaseModel implements UserDetails {

    private String username;
    private String password;
    @Email
    private String email;
    private List<String> roles = new ArrayList<>();

    public void setPassword(String password) {

        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {

        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : this.roles) {
            authorities.add(new SimpleGrantedAuthority(role));
            // 'ROLE_' defined in DB so we can have roles and authorities
            // This allows hasAuthority() and hasRole() methods
        }
        return authorities;
    }
}