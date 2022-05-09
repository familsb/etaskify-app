package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.enums.AuthorityName;
import com.etaskify.etaskifyapp.mapper.UserMapper;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.etaskify.etaskifyapp.service.PasswordEncoderService;
import com.etaskify.etaskifyapp.service.UserService;
import com.etaskify.etaskifyapp.util.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final OrganizationProfileService organizationProfileService;
    private final PasswordEncoderService encoderService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if (Objects.nonNull(user)) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getAuthority().name()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(),
                    user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("Username as email " + email + " not found");
        }
    }

    @Override
    @Transactional
    public ResponseDto saveOrUpdateUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        OrganizationProfile organizationProfile = organizationProfileService.findOrganizationProfileByEmail(SecurityContext.getLoggedUsername());
        user.setOrganizationProfile(organizationProfile);
        user.setAuthority(AuthorityName.ROLE_USER);
        user.setPassword(encoderService.bcryptEncryptor("24355535"));
        UserMapper.INSTANCE.toDto(userRepository.save(user));
        return new ResponseDto(AppMessage.USER_CREATED);
    }

    @Override
    public List<UserDto> organizationUsers() {
        OrganizationProfile organizationProfile =
                organizationProfileService.findOrganizationProfileByEmail(SecurityContext.getLoggedUsername());
        List<User> userList = organizationProfile.getUsers();
        return UserMapper.INSTANCE.toUserDtoList(userList);
    }
}
