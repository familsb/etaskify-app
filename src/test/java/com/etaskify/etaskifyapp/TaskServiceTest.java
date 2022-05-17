package com.etaskify.etaskifyapp;

import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.TaskDto;
import com.etaskify.etaskifyapp.dto.UserDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.Task;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.TaskRepository;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.EmailService;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.etaskify.etaskifyapp.service.impl.TaskServiceImpl;
import com.etaskify.etaskifyapp.util.SecurityContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @InjectMocks
    private TaskServiceImpl taskService;
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private OrganizationProfileService organizationProfileService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailService emailService;

    @Test
    void givenOrgWithNoUserWhenSaveOrUpdateThenThrowUserNotExistException() {
        TaskDto taskDto = new TaskDto();
        taskDto.setUserDtoList(Collections.emptyList());
        OrganizationProfile organizationProfile = new OrganizationProfile();
        organizationProfile.setUsers(Collections.emptyList());
        when(organizationProfileService.findOrganizationProfileByEmail(any())).thenReturn(organizationProfile);
        Assertions.assertEquals(new ResponseDto(AppMessage.TASK_CREATED), taskService.saveOrUpdateTask(taskDto));
    }

    @Test
    void givenTaskWithoutAssigneesWhenSaveOrUpdateThenDoNotSendEmail() {
        User user = new User();
        user.setId(1L);
        OrganizationProfile organization = new OrganizationProfile();
        organization.setUsers(Collections.singletonList(user));
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        TaskDto taskDto = new TaskDto();
        taskDto.setUserDtoList(Collections.singletonList(userDto));
        when(organizationProfileService.findOrganizationProfileByEmail(any())).thenReturn(organization);
        when(userRepository.findUserByEmail(any())).thenReturn(user);
        when(taskRepository.save(any())).thenAnswer(i -> {
            Task task = i.getArgument(0);
            task.setAssignees(Collections.emptyList());
            return task;
        });
        verify(emailService, times(0)).sendMail(any(), any(), any());
        Assertions.assertEquals(new ResponseDto(AppMessage.TASK_CREATED), taskService.saveOrUpdateTask(taskDto));
    }

    @Test
    void givenOrgEmailWhenGetTasksOfOrgThenReturnTasks() {
        List<TaskDto> taskDtoList = new ArrayList<>();
        when(organizationProfileService.findOrganizationProfileByEmail(any())).thenReturn(new OrganizationProfile());
        when(taskRepository.findAllByOrganizationProfile(any())).thenReturn(Collections.emptyList());
        Assertions.assertEquals(null, taskService. getTasksOrganization());
    }

}
