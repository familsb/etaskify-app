package com.etaskify.etaskifyapp.service;

import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.TaskDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    ResponseDto saveOrUpdateTask(TaskDto taskDto);

    List<TaskDto> getTasksOrganization();
}
