package com.etaskify.etaskifyapp.repository;

import com.etaskify.etaskifyapp.model.OrganizationProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationProfileRepository extends JpaRepository<OrganizationProfile, Long> {

    @Query(value = "select org from OrganizationProfile  org join org.users u where u.email=:email")
    OrganizationProfile findOrganizationProfileByEmail(String email);

    @Query(value = "select org from OrganizationProfile  org join  org.users u where u.id=:userId")
    OrganizationProfile findOrganizationProfileByOwnerId(Long userId);


}
