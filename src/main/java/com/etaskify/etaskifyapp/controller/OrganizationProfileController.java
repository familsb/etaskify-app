package com.etaskify.etaskifyapp.controller;

import com.etaskify.etaskifyapp.dto.ApiResponse;
import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/organization-profile")
@RequiredArgsConstructor
public class OrganizationProfileController {

    private final OrganizationProfileService organizationProfileService;

    @PostMapping("/sign-up")
    public ApiResponse<ResponseDto> signUpOrganizationProfile(@Valid @RequestBody OrganizationProfileDto organizationProfileDto) {
        return ApiResponse.withSuccess(organizationProfileService.saveOrgProfileData(organizationProfileDto));
    }
}

