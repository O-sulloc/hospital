package com.example.nationwidehospital.service;

import com.example.nationwidehospital.domain.dto.UserDto;
import com.example.nationwidehospital.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto join(UserJoinRequest request){

        return new UserDto();
    }
}
