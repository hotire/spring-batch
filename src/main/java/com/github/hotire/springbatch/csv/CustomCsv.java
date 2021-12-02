package com.github.hotire.springbatch.csv;

import lombok.Data;

@Data
public class CustomCsv {
    @CsvColumn(index = 0, name = "name")
    private String name;
    @CsvColumn(index = 1, name = "age")
    private Long age;
    @CsvColumn(index = 2, name = "email")
    private String email;
}
