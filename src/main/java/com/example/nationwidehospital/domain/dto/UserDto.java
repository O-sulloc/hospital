package com.example.nationwidehospital.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserDto {
    //userService에서 사용되는
    private Long id;
    private String userName;
    private String password;
    private String email;
}
