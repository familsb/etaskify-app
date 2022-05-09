package com.etaskify.etaskifyapp.repository;

import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByOrganizationProfile(OrganizationProfile organizationProfile);

}
