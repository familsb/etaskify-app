package com.etaskify.etaskifyapp.mapper;

import com.etaskify.etaskifyapp.dto.OrganizationProfileDto;
import com.etaskify.etaskifyapp.model.OrganizationProfile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationProfileMapper {
    OrganizationProfileMapper INSTANCE = Mappers.getMapper(OrganizationProfileMapper.class);

    OrganizationProfile toEntity(OrganizationProfileDto organizationProfileDto);
}
