package com.etaskify.etaskifyapp.model;

import com.etaskify.etaskifyapp.enums.AuthorityName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.List;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String surname;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthorityName authority;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "organization_profile_id")
    private OrganizationProfile organizationProfile;

    @JsonIgnore
    @ManyToMany(mappedBy = "assignees")
    private List<Task> tasks;

    @JsonIgnore
    @OneToMany(mappedBy = "createdBy")
    private List<Task> ownerTasks;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String name, String surname, @Email String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
