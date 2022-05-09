package com.etaskify.etaskifyapp.model;

import com.etaskify.etaskifyapp.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime deadline;
    @Column(nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(name = "task_assignees")
    private List<User> assignees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    //reporter
    private User createdBy;


    @ManyToOne
    @JoinColumn(name = "organization_profile_id")
    @JsonIgnore
    private OrganizationProfile organizationProfile;

}
