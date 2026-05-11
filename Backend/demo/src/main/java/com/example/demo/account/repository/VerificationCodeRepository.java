package com.example.demo.account.repository;

import com.example.demo.account.models.VerificationCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

  @Query("SELECT vc FROM VerificationCode vc WHERE vc.code = :code")
  Optional<VerificationCode> findByCode(String code);
}