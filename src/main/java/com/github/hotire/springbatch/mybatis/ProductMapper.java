package com.github.hotire.springbatch.mybatis;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product selectById(Long id);
}
