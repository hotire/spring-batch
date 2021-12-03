package com.github.hotire.springbatch.csv;

import java.util.List;

import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

public class CsvColumnDelimitedLineTokenizer extends DelimitedLineTokenizer {

    public CsvColumnDelimitedLineTokenizer(Class<?> type) {
        final List<CsvColumn> csvColumnList = CsvColumnAnnotationReader.getAnnotation(type);
        setNames(csvColumnList.stream().map(CsvColumn::name).toArray(String[]::new));
        setIncludedFields(csvColumnList.stream().mapToInt(CsvColumn::index).toArray());
    }
}
