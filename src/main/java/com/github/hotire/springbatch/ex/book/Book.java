package com.github.hotire.springbatch.ex.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Book {
    @Id @GeneratedValue
    private Long id;
}
