package com.example.otpservice.repositories;

import com.example.otpservice.entities.Code;
import com.example.otpservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeRepository extends JpaRepository<Code,Long> {

    List<Code> findAllByUserId(Long userId);

  //  List<Code> findAllByUser(User user);
}
