package com.github.hotire.springbatch.csv;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.core.annotation.AnnotationUtils;

final class CsvColumnAnnotationReader {

    private CsvColumnAnnotationReader() { }

    static List<CsvColumn> getAnnotation(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                     .map(it -> {
                         final CsvColumn csvColumn = AnnotationUtils.findAnnotation(it, CsvColumn.class);

                         if (csvColumn != null && csvColumn.name().isEmpty()) {
                             return switchName(csvColumn, it.getName());
                         }

                         return csvColumn;
                     })
                     .filter(Objects::nonNull)
                     .sorted(Comparator.comparingInt(CsvColumn::index))
                     .collect(Collectors.toList());
    }


    private static CsvColumn switchName(CsvColumn csvColumn, String name) {
        return new CsvColumn() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return CsvColumn.class;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public int index() {
                return csvColumn.index();
            }
        };
    }
}
