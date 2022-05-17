package com.etaskify.etaskifyapp.service.impl;

import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.TaskDto;
import com.etaskify.etaskifyapp.enums.AppMessage;
import com.etaskify.etaskifyapp.exception.AppException;
import com.etaskify.etaskifyapp.mapper.TaskMapper;
import com.etaskify.etaskifyapp.mapper.UserMapper;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import com.etaskify.etaskifyapp.model.Task;
import com.etaskify.etaskifyapp.model.User;
import com.etaskify.etaskifyapp.repository.TaskRepository;
import com.etaskify.etaskifyapp.repository.UserRepository;
import com.etaskify.etaskifyapp.service.EmailService;
import com.etaskify.etaskifyapp.service.OrganizationProfileService;
import com.etaskify.etaskifyapp.service.TaskService;
import com.etaskify.etaskifyapp.util.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final OrganizationProfileService organizationProfileService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public ResponseDto saveOrUpdateTask(TaskDto taskDto) {
        OrganizationProfile organizationProfile = organizationProfileService.findOrganizationProfileByEmail(SecurityContext.getLoggedUsername());
        if (Objects.isNull(organizationProfile)) {
            new ResponseDto(AppMessage.ORGANIZATION_NOT_FOUND);
        }
        List<User> orgUserList = organizationProfile.getUsers();
        List<User> assignUserList = UserMapper.INSTANCE.toUserList(taskDto.getUserDtoList());
        checkUsersBelongToOrganization(orgUserList, assignUserList);
        Task task = TaskMapper.INSTANCE.toEntity(taskDto);
        User user = userRepository.findUserByEmail(SecurityContext.getLoggedUsername());
        task.setCreatedBy(user);
        task.setAssignees(assignUserList);
        task.setOrganizationProfile(organizationProfile);
        taskRepository.save(task);

        if (!assignUserList.isEmpty()) {
            sendMailTaskAssigned(task);
        }
        return new ResponseDto(AppMessage.TASK_CREATED);
    }

    @Override
    public List<TaskDto> getTasksOrganization() {
        OrganizationProfile organizationProfile =
                organizationProfileService.findOrganizationProfileByEmail(SecurityContext.getLoggedUsername());
        List<Task> taskList = taskRepository.findAllByOrganizationProfile(organizationProfile);
        return TaskMapper.INSTANCE.toTaskDtoList(taskList);
    }

    private void checkUsersBelongToOrganization(List<User> existUsers, List<User> receivedUsers) {

        if (existUsers.containsAll(receivedUsers)) {
            throw new AppException(AppMessage.USER_NOT_FOUND);
        }

    }

    private void sendMailTaskAssigned(Task task) {
        emailService.sendMail(
                task.getTitle(),
                task.getDescription(),
                task.getAssignees().stream().map(User::getEmail).collect(Collectors.toList())
        );
    }

}
