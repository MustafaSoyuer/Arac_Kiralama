package com.mustafa.service;

import com.mustafa.dto.request.LoginRequestDto;
import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import com.mustafa.entity.Auth;
import com.mustafa.exception.AuthManagerException;
import com.mustafa.exception.ErrorType;
import com.mustafa.mapper.AuthMapper;
import com.mustafa.repository.AuthRepository;
import com.mustafa.utility.CodeGenerator;
import com.mustafa.utility.JwtTokenManager;
import com.mustafa.utility.ServiceManager;
import com.mustafa.utility.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final AuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;



    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }


    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(authOptional.isEmpty()){
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        return jwtTokenManager.createToken(authOptional.get().getId())
                .orElseThrow(() ->
                        new AuthManagerException(ErrorType.TOKEN_NOT_CREATED));
    }
}





