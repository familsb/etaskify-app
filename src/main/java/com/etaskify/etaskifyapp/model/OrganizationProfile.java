package com.etaskify.etaskifyapp.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Entity
@Data
public class OrganizationProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(nullable = false)
    @NotBlank
    private String address;

    @OneToMany(mappedBy = "organizationProfile", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;

    @OneToMany(mappedBy = "organizationProfile")
    private List<Task> tasks;
}
