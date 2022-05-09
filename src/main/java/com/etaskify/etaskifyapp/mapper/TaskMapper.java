package com.etaskify.etaskifyapp.mapper;

import com.etaskify.etaskifyapp.dto.TaskDto;
import com.etaskify.etaskifyapp.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    Task toEntity(TaskDto taskDto);

    TaskDto toDto(Task task);

    List<TaskDto> toTaskDtoList(List<Task> tasks);
}
