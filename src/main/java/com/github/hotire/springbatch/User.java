package com.github.hotire.springbatch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class User {
  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;

  public User() {
  }

  public User(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
