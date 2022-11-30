package com.example.nationwidehospital.controller;

import com.example.nationwidehospital.domain.dto.UserDto;
import com.example.nationwidehospital.domain.dto.UserJoinRequest;
import com.example.nationwidehospital.exception.ErrorCode;
import com.example.nationwidehospital.exception.HospitalException;
import com.example.nationwidehospital.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    void join_success() throws Exception {
        //가입 성공
        UserJoinRequest request = UserJoinRequest.builder()
                .userName("kjh") //가짜 데이터
                .password("1234")
                .email("1234@1234")
                .build();

        when(userService.join(any())).thenReturn(mock(UserDto.class));
        //userService의 join 메서드를 사용하면 UserDto.class를 리턴한다.?

        mockMvc.perform(post("/api/v1/users/join") //해당 Url에 post로 요청했을 때
                        .contentType(MediaType.APPLICATION_JSON) //json 형식으로 넣을거니까
                        .content(objectMapper.writeValueAsBytes(request))) //위에 적은 가짜 데이터를 넣을거임
                //정리하면 request에 있는 정보를 objectMapper로? 그 다음에 JSon형식으로 넣는다? 근데 언제? join에 Post 요청했을때?
                //그 Performdml status가 ok인지?
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void join_fail() throws Exception {
        //가입 실패
        UserJoinRequest userJoinRequest = UserJoinRequest.builder()
                .userName("kjh")
                .password("1234")
                .email("1234@1234")
                .build();

        when(userService.join(any())).thenThrow(new HospitalException(ErrorCode.DUPLICATED_USER_NAME, ""));

        mockMvc.perform(post("/api/v1/users/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userJoinRequest)))
                .andDo(print())
                .andExpect(status().isConflict()); //errorCode 클래스 가보면 HttpStatus에 Conflict라고 해놓은거 볼 수 있음
                //따라서 여기서도 status가 conflict여야 함.
    }
}