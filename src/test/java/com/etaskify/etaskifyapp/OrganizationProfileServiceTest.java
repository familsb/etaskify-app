package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserOwnerDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.OrganizationProfileRepository;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.impl.OrganizationProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizationProfileServiceTest {
    @InjectMocks
    private OrganizationProfileServiceImpl organizationProfileService;

    @Mock
    private OrganizationProfileRepository organizationProfileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Test
    void givenEmailWhenFindOrganizationProfileByEmailThenReturnOrganizationProfile() {
        OrganizationProfile organizationProfile = new OrganizationProfile();
        when(organizationProfileRepository.findOrganizationProfileByEmail(any())).thenReturn(organizationProfile);
        Assertions.assertEquals(organizationProfile, organizationProfileService.findOrganizationProfileByEmail("test@gmail.com"));

    }

    @Test
    void checkCreatingOrganizationProfileWhenSaveOrUpdateThenReturnOrganizationProfile() {
        OrganizationProfile organization = new OrganizationProfile();
        OrganizationProfileDto organizationDto = new OrganizationProfileDto();
        organizationDto.setOwnerDto(new UserOwnerDto("email"));
        when(passwordEncoder.encode(any())).thenReturn("bcryptPass");
        when(organizationProfileRepository.save(any())).thenReturn(organization);
        Assertions.assertEquals(new ResponseDto(AppMessage.ORGANIZATION_CREATED), organizationProfileService.saveOrgProfileData(organizationDto));

    }

    @Test
    void checkUserWhenSaveOrUpdateThenReturnUser() {
        User user = new User();
        OrganizationProfileDto organizationDto = new OrganizationProfileDto();
        organizationDto.setOwnerDto(new UserOwnerDto("email"));
        when(userRepository.findUserByEmail(any())).thenReturn(user);
        Assertions.assertEquals(new ResponseDto(AppMessage.ALREADY_HAVE), organizationProfileService.saveOrgProfileData(organizationDto));

    }

    @Test
    void givenOwnerUserIdWhenFindOrganizationOwnerIdThenReturnOrganization() {
        OrganizationProfile organizationProfile = new OrganizationProfile();
        when(organizationProfileRepository.findOrganizationProfileByOwnerId(any())).thenReturn(organizationProfile);
        Assertions.assertEquals(organizationProfile, organizationProfileService.findOrganizationProfileByOwnerId(2L));
    }
}
