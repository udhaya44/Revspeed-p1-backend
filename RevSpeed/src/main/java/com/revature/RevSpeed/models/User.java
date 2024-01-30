package com.revature.RevSpeed.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "user_table")
public class User implements UserDetails {

    @Id
    private String userId;
    private String FirstName;
    private String LastName;
    private String email;
    private String phoneNo;
    @JsonIgnore
    private String password;
    private String address;

    @Column(nullable = false)
    private boolean isBroadBandUser=true;
    @Column(nullable = false)
    private boolean isBusinessUser=false;
    private Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) {
            return Collections.emptyList(); // or handle it appropriately
        }
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
