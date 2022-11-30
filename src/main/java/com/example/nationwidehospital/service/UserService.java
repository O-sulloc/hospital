package com.example.nationwidehospital.service;

import com.example.nationwidehospital.domain.User;
import com.example.nationwidehospital.domain.dto.UserDto;
import com.example.nationwidehospital.domain.dto.UserJoinRequest;
import com.example.nationwidehospital.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //lombok annotation
public class UserService {
    private final UserRepository userRepository;

    public UserDto join(UserJoinRequest request) {
        userRepository.findByUserName(request.getUserName()) //request에 있는 userName 꺼내서 중복체크하기
                .ifPresent(user -> {
                    // 결과가 있으면 이미 가입된 UserName. 예외처리해줌
                    throw new RuntimeException("이미 존재하는 USerName입니다.");
                });

        //가입되지 않은 username이면 그대로 가입 진행 (save)하면 됨.
        // 컨트롤러에서 보내준 userJoinRequest를 받아서 엔티티로 변환해주고
        // 그걸로 repository 처리하고
        User user = userRepository.save(request.toEntity()); //repository.save 매개변수 보면 entity로 받는거 알 수 있음.
        // 그걸 user 타입으로 받음

        // 그 user타입을 userDto 타입으로 return해서 컨트롤러로 보내줘
        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }
}
