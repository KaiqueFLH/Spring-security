package com.usercrud.usercrud.SECURITY;

import com.usercrud.usercrud.User.Model.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Builder
public class UserDetailsENTITY implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false,updatable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled;
    private Collection<Authorities> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    @OneToOne(mappedBy = "userDetailsENTITY")
    private User user;
}
