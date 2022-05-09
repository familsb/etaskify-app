package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.dto.ApiResponse;
import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserOwnerDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.exception.AppException;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.OrganizationProfileRepository;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.etaskify.etaskifyapp.service.impl.OrganizationProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizationProfileTest {
    @InjectMocks
    private OrganizationProfileServiceImpl organizationProfileService;

    @Mock
    private OrganizationProfileRepository organizationProfileRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder passwordEncoder;

    @Test
    void givenEmailWhenFindOrganizationByEmailThenReturnOrganization() {
        OrganizationProfile organization = new OrganizationProfile();
        when(organizationProfileRepository.findOrganizationProfileByEmail(any())).thenReturn(organization);
        Assertions.assertEquals(organization, organizationProfileService.findOrganizationProfileByEmail("test@gmail.com"));

    }

//    @Test
//    void givenOrgWithUserOwnerDtoWhenSaveOrUpdateThenThrowAlreadyExistsException() {
//        User user = new User();
//        OrganizationProfileDto organizationDto = new OrganizationProfileDto();
//        organizationDto.setOwnerDto(new UserOwnerDto("email"));
//        when(userRepository.findUserByEmail(any())).thenReturn(user);
//        Assertions.assertSame(AppException.class, () -> organizationProfileService.saveOrgProfileData(organizationDto));
//
//    }

    @Test
    void givenOrgDtoWithUsrOwnDtoWhenSaveOrUpdateThenReturnOrganization() {
        OrganizationProfile organization = new OrganizationProfile();
        User user = new User();
        OrganizationProfileDto organizationDto = new OrganizationProfileDto();
        organizationDto.setOwnerDto(new UserOwnerDto("email"));
        when(userRepository.findUserByEmail(any())).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn("bcryptPass");
        when(organizationProfileRepository.save(any())).thenReturn(organization);
        Assertions.assertEquals(new ResponseDto(AppMessage.ALREADY_HAVE), organizationProfileService.saveOrgProfileData(organizationDto));


    }

    @Test
    void givenOwnerUserIdWhenFindOrganizationOwnerIdThenReturnOrganization() {
        OrganizationProfile organization = new OrganizationProfile();
        when(organizationProfileRepository.findOrganizationProfileByOwnerId(any())).thenReturn(organization);
        Assertions.assertEquals(organization, organizationProfileService.findOrganizationProfileByOwnerId(3l));
    }
}
