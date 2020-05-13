package com.bsmlima.salesdataanalysis;

import com.bsmlima.salesdataanalysis.dao.ReportDao;
import com.bsmlima.salesdataanalysis.parser.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.HashMap;

@Configuration
public class AppConfig {

    private static final String INPUT_PATH = System.getenv("HOME") + "/data/in";
    private static final String OUTPUT_PATH = System.getenv("HOME") + "/data/out";
    private static final String SALESMAN_CODE = "001";
    private static final String CUSTOMER_CODE = "002";
    private static final String SALE_CODE = "003";

    @Bean
    public SalesmanDataParser salesmanDataParser() {
        return new SalesmanDataParser();
    }

    @Bean
    public CustomerDataParser customerDataParser() {
        return new CustomerDataParser();
    }

    @Bean
    public SaleDataParser saleDataParser() {
        return new SaleDataParser();
    }

    @Bean
    public HashMap<String, DataParser> parsersHashMap() {
        HashMap<String, DataParser> parsersHashMap = new HashMap<>();

        parsersHashMap.put(SALESMAN_CODE, salesmanDataParser());
        parsersHashMap.put(CUSTOMER_CODE, customerDataParser());
        parsersHashMap.put(SALE_CODE,     saleDataParser());

        return parsersHashMap;
    }

    @Bean
    public ReportDao reportDao() {
        return new ReportDao(INPUT_PATH, OUTPUT_PATH);
    }

    @Bean
    public FileParser fileParser() {
        return new FileParser(reportDao(), parsersHashMap());
    }

    @Bean
    public DirectoryWatcher directoryWatcher() throws IOException {
        return new DirectoryWatcher(fileParser(), reportDao());
    }

}
