package com.example.nationwidehospital.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    /*
    !!response 추상화

    에러코드를 포함해서 리턴.
    모든 response는 response<T> 객체로 감싸서 return해 줄 것임.
     */

    private String resultCode; //어떤 에러가 났는지 알려주려고
    private T result;

    public static Response<Void> error(String resultCode) {
        //성공했을 때
        return new Response(resultCode, null);
    }

    public static <T> Response<T> success(T result) {
        //성공했을 때
        return new Response("SUCCESS", result);
    }
}
