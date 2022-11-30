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

    !!! response는 컨트롤러와 뷰에서 왔다갔다 하는 객체
     */

    private String resultCode; //어떤 에러가 났는지 메세지로 알려주려고
    private T result;

    public static Response<Void> error(String resultCode) {
        //실패했을 때
        return new Response(resultCode, null); // ExceptionManager 클래스에서 사용됨. 매개변수로 받은 에러메세지를 response로 감싸서 리턴
    }

    public static <T> Response<T> success(T result) {
        //성공했을 때
        return new Response("SUCCESS", result); //"success", 컨트롤러에서 리턴해준 결과
    }
}
