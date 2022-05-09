package com.etaskify.etaskifyapp.controller;

import com.etaskify.etaskifyapp.dto.ApiResponse;
import com.etaskify.etaskifyapp.dto.ResponseDto;
import com.etaskify.etaskifyapp.dto.TaskDto;
import com.etaskify.etaskifyapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping(value = "/saveTask")
    public ApiResponse<ResponseDto> assignTask(@Valid @RequestBody TaskDto taskDto) {
        return ApiResponse.withSuccess(taskService.saveOrUpdateTask(taskDto));
    }

    @GetMapping("/organization-profile/tasks")
    public ApiResponse<List<TaskDto>> getTasks() {
        return ApiResponse.withSuccess(taskService.getTasksOrganization());
    }
}
