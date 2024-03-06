package com.mustafa.controller;

import com.mustafa.dto.request.AddUserRequestDto;
import com.mustafa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(USER)
public class UserController {

    private final UserService userService;

    @PostMapping(ADD)
    public ResponseEntity<Boolean> add(@RequestBody AddUserRequestDto dto){
        return ResponseEntity.ok(userService.add(dto));
    }


}
