package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.config.JwtAuthenticationFilter;
import com.etaskify.etaskifyapp.config.WebSecurityConfig;
import com.etaskify.etaskifyapp.controller.OrganizationProfileController;
import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.UserOwnerDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(
        value = OrganizationProfileController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        JwtAuthenticationFilter.class, WebSecurityConfig.class
                })
        }
)
@AutoConfigureMockMvc(addFilters = false)
public class OrganizationProfileControllerTest {

    @MockBean
    OrganizationProfileService organizationProfileService;

    @Autowired
    MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
     void testSaveOrganizationProfile() throws Exception {
        OrganizationProfileDto dto = new OrganizationProfileDto();
        dto.setAddress("test address");
        dto.setName("testOrgName");
        dto.setPhoneNumber("+994504354654");
        UserOwnerDto userOwnerDto = new UserOwnerDto();
        userOwnerDto.setName("testUserName");
        userOwnerDto.setSurname("testUserSurname");
        userOwnerDto.setPassword("testPassword");
        userOwnerDto.setEmail("test@ef.fef");
        dto.setOwnerDto(userOwnerDto);

        when(organizationProfileService.saveOrgProfileData(any()))
                .thenReturn(new ResponseDto(AppMessage.ORGANIZATION_CREATED));

        mockMvc.perform(post("/api/organization-profile/sign-up")
                        .secure(false)
                        .content(mapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message").value(AppMessage.ORGANIZATION_CREATED.getMessage()));

    }
}
