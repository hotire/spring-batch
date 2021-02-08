package com.github.hotire.springbatch.mybatis;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
}
