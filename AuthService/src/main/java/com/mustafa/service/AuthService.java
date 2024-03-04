package com.mustafa.service;

import com.mustafa.dto.request.RegisterRequestDto;
import com.mustafa.dto.response.RegisterResponseDto;
import com.mustafa.entity.Auth;
import com.mustafa.mapper.AuthMapper;
import com.mustafa.repository.AuthRepository;
import com.mustafa.utility.CodeGenerator;
import com.mustafa.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final AuthRepository authRepository;


    public AuthService(JpaRepository<Auth, Long> repository, AuthRepository authRepository) {
        super(repository);
        this.authRepository = authRepository;
    }


    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = AuthMapper.INSTANCE.fromRegisterRequestToAuth(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        save(auth);
        return AuthMapper.INSTANCE.fromAuthToRegisterResponseDto(auth);
    }
}
