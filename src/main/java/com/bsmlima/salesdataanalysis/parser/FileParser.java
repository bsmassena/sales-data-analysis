package com.bsmlima.salesdataanalysis.parser;

import com.bsmlima.salesdataanalysis.model.SalesData;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileParser {

    private final HashMap<String, DataParser> parsersHashMap;

    private static final String SEPARATOR = "ç";

    public FileParser(HashMap<String, DataParser> parsersHashMap) {
        this.parsersHashMap = parsersHashMap;
    }

    public SalesData parse(Stream<String> fileLines) {
        SalesData salesData = new SalesData();

        fileLines.forEach(line -> {
            List<String> data = Arrays.asList(line.split(SEPARATOR));
            String dataType = data.get(0);
            parsersHashMap.get(dataType).parse(data, salesData);
        });

        return salesData;
    }
}
