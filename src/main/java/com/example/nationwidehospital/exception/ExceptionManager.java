package com.example.nationwidehospital.exception;

import com.example.nationwidehospital.domain.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeExceptionHandler(RuntimeException e) {
        //어떤 오류가 올지 모르기 때문에 물음표로 처리

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(e.getMessage())); //에러 메세지를 가져와서 -> 그걸 error 메서드에 보내서 처리하고 response 타입으로 가져오고 -> 그걸 body에 담아주고
    }

    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<?> HospitalExceptionHandler(HospitalException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus()) //발생한 에러의 status를 가져옴 (DUPLICATED_USER_NAME(HttpStatus.CONFLICT,"유저 네임 중복 오류")) 여기서 Conflict를 가져
                .body(Response.error(e.getErrorCode().name()));  //DUPLICATED_USER_NAME가 출력
    }
}
