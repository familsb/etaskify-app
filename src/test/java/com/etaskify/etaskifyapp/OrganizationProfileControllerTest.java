package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.controller.OrganizationProfileController;
import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.dto.UserOwnerDto;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrganizationProfileController.class)
public class OrganizationProfileControllerTest {
    @MockBean
    OrganizationProfileService organizationProfileService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testSaveOrganizationProfile() throws Exception {
//        OrganizationProfileDto dto = new OrganizationProfileDto();
//        dto.setAddress("rrwrwe");
//        dto.setName("fdfd");
//        dto.setPhoneNumber("+9943243546546");
//        UserOwnerDto userOwnerDto = new UserOwnerDto();
//        userOwnerDto.setName("34432434");
//        userOwnerDto.setSurname("2erewrwer");
//        userOwnerDto.setPassword("214253553");
//        userOwnerDto.setEmail("sadds@ef.fef");
//        dto.setOwnerDto(userOwnerDto);
//
//        Mockito.when(organizationProfileService.saveOrgProfileData(dto)).thenReturn(employees);

    }
}
