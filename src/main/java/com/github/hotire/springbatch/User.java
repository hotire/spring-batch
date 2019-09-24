package com.github.hotire.springbatch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
}
