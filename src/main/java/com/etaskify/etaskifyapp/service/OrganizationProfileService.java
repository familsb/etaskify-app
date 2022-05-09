package com.etaskify.etaskifyapp.service;

import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.model.OrganizationProfile;

public interface OrganizationProfileService {

    ResponseDto saveOrgProfileData(OrganizationProfileDto organizationProfileDto);

    OrganizationProfile findOrganizationProfileByEmail(String email);

    OrganizationProfile findOrganizationProfileByOwnerId(Long userId);

}
