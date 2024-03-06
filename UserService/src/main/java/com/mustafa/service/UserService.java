package com.mustafa.service;

import com.mustafa.domain.User;
import com.mustafa.dto.request.AddUserRequestDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserManagerException;
import com.mustafa.mapper.UserMapper;
import com.mustafa.utility.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceManager<User,String> {
    public UserService(MongoRepository<User, String> repository) {
        super(repository);
    }


    public Boolean add(AddUserRequestDto dto) {
        try {
            save(UserMapper.INSTANCE.fromCreateRequestToUserProfile(dto));
            return true;
        } catch (Exception e) {
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }
}
