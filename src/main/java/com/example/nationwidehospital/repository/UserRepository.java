package com.example.nationwidehospital.repository;

import com.example.nationwidehospital.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName); //username 중복으로 가입되지 않게 중복 체크하는 메서드
}
