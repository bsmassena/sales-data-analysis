package com.bsmlima.salesdataanalysis.parser;

import java.util.List;

public class SaleDataParser implements DataParser {

    @Override
    public void parse(List<String> data) {
        System.out.println("Parsing sale: " + data);
    }
}
