package com.mustafa.mapper;

import com.mustafa.domain.User;
import com.mustafa.dto.request.AddUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User fromCreateRequestToUserProfile(final AddUserRequestDto dto);







}
