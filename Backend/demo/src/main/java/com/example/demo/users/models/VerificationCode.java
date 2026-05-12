package com.example.demo.users.models;

import com.example.demo.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.security.SecureRandom;

@Entity
@Getter
@NoArgsConstructor
public class VerificationCode extends AbstractEntity {

  private String code;
  @Setter
  private boolean emailSent = false;
  @OneToOne
  private User user;

  private static final SecureRandom random = new SecureRandom();

  public VerificationCode(User user) {
      this.user = user;
      this.code = generateCode(6);
  }

  private String generateCode(int length) {
      StringBuilder sb = new StringBuilder(length);
      for (int i = 0; i < length; i++) {
          sb.append(random.nextInt(10));
      }
    return sb.toString();
  }
}