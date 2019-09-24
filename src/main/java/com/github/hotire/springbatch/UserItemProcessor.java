package com.github.hotire.springbatch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class UserItemProcessor implements ItemProcessor<User, User> {

  @Override
  public User process(User user) throws Exception {
    final String firstName = user.getFirstName().toUpperCase();
    final String lastName = user.getLastName().toUpperCase();

    final User transformedUser = new User(firstName, lastName);

    log.info("Converting ({}) into ({})", user, transformedUser);

    return transformedUser;
  }
}
