package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.enums.AuthorityName;
import com.etaskify.etaskifyapp.mapper.OrganizationProfileMapper;
import com.etaskify.etaskifyapp.mapper.UserOwnerMapper;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.OrganizationProfileRepository;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.etaskify.etaskifyapp.service.PasswordEncoderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationProfileServiceImpl implements OrganizationProfileService {

    private final OrganizationProfileRepository organizationProfileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderService encoderService;


    @Override
    public ResponseDto saveOrgProfileData(OrganizationProfileDto organizationProfileDto) {

        if (Objects.nonNull(userRepository.findUserByEmail(organizationProfileDto.getOwnerDto().getEmail()))) {
            return new ResponseDto(AppMessage.ALREADY_HAVE);
        }
        String password = organizationProfileDto.getOwnerDto().getPassword();
        organizationProfileDto.getOwnerDto().setPassword(encoderService.bcryptEncryptor(password));
        OrganizationProfile organizationProfile = OrganizationProfileMapper.INSTANCE.toEntity(organizationProfileDto);
        User user = UserOwnerMapper.INSTANCE.toEntity(organizationProfileDto.getOwnerDto());
        user.setOrganizationProfile(organizationProfile);
        user.setAuthority(AuthorityName.ROLE_ADMIN);
        organizationProfile.setUsers(Collections.singletonList(user));

        organizationProfileRepository.save(organizationProfile);

        return new ResponseDto(AppMessage.ORGANIZATION_CREATED);
    }

    @Override
    public OrganizationProfile findOrganizationProfileByEmail(String email) {
        return organizationProfileRepository.findOrganizationProfileByEmail(email);
    }

    @Override
    public OrganizationProfile findOrganizationProfileByOwnerId(Long userId) {
        return organizationProfileRepository.findOrganizationProfileByOwnerId(userId);
    }
}
