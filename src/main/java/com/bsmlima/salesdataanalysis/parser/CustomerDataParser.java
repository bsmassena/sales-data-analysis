package com.bsmlima.salesdataanalysis.parser;

import java.util.List;

public class CustomerDataParser implements DataParser {

    @Override
    public void parse(List<String> data) {
        System.out.println("Parsing customer: " + data);
    }
}
