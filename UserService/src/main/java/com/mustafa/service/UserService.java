package com.mustafa.service;

import com.mustafa.domain.User;
import com.mustafa.dto.request.AddUserRequestDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserManagerException;
import com.mustafa.mapper.UserMapper;
import com.mustafa.repository.UserRepository;
import com.mustafa.utility.ServiceManager;
import com.mustafa.utility.enums.EStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User,String> {

    private final UserRepository userRepository;

    public UserService(MongoRepository<User, String> repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;

    }


    public Boolean save(AddUserRequestDto dto) {
        try {
            save(UserMapper.INSTANCE.fromCreateRequestToUserProfile(dto));
            return true;
        } catch (Exception e) {
            throw new UserManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    public Boolean activateStatus(Long authId) {
        Optional<User> optionalUserProfile = userRepository.findByAuthId(authId);
        if(optionalUserProfile.isPresent()){
            optionalUserProfile.get().setStatus(EStatus.ACTIVE);
            update(optionalUserProfile.get());
            return true;
        } else {
            throw new UserManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

}
