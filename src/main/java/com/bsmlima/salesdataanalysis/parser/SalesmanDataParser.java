package com.bsmlima.salesdataanalysis.parser;

import java.util.List;

public class SalesmanDataParser implements DataParser {

    @Override
    public void parse(List<String> data) {
        System.out.println("Parsing salesman: " + data);
    }
}
