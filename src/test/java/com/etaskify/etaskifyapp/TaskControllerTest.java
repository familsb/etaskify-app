package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.config.JwtAuthenticationFilter;
import com.etaskify.etaskifyapp.config.WebSecurityConfig;
import com.etaskify.etaskifyapp.controller.OrganizationProfileController;
import com.etaskify.etaskifyapp.dto.TaskDto;
import com.etaskify.etaskifyapp.model.Task;
import com.etaskify.etaskifyapp.repository.TaskRepository;
import com.etaskify.etaskifyapp.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


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
public class TaskControllerTest {
    @MockBean
    TaskService taskService;

    @Autowired
    MockMvc mockMvc;

    @Mock
    TaskRepository taskRepository;

    @Test
    void testTasks() throws Exception {

        List<Task> taskList = new ArrayList<>(List.of());

        Mockito.when(taskRepository.findAllByOrganizationProfile(any())).thenReturn(taskList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/task/organization-profile/tasks")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println("status: " + mvcResult.getResponse().getStatus());

    }
}
