package com.example.nationwidehospital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalException extends RuntimeException {
    //RuntimeException 상속받아서 나머지 내용은 개발자가 구체화한다.

    private ErrorCode errorCode; //ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)처럼 에러코드 담는 변수 선언
    private String message; //.body(Response.error(e.getMessage())) 처럼 에러 메세지 담는 변수 선언


}
