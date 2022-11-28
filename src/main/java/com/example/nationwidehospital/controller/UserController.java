package com.example.nationwidehospital.controller;

import com.example.nationwidehospital.domain.Response;
import com.example.nationwidehospital.domain.dto.UserDto;
import com.example.nationwidehospital.domain.dto.UserJoinRequest;
import com.example.nationwidehospital.domain.dto.UserJoinResponse;
import com.example.nationwidehospital.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest) {
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse());
    }
}