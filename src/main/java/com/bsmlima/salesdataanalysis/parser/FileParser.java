package com.bsmlima.salesdataanalysis.parser;

import com.bsmlima.salesdataanalysis.dao.ReportDao;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FileParser {

    private final ReportDao reportDao;
    private final HashMap<String, DataParser> parsersHashMap;

    private static final String SEPARATOR = "ç";

    public FileParser(ReportDao reportDao, HashMap<String, DataParser> parsersHashMap) {
        this.reportDao = reportDao;
        this.parsersHashMap = parsersHashMap;
    }

    public void parse(String filename) throws IOException {
        reportDao.read(filename).forEach(line -> {
            List<String> data = Arrays.asList(line.split(SEPARATOR));
            String dataType = data.get(0);
            parsersHashMap.get(dataType).parse(data);
        });

        reportDao.save(filename, "abcdsesfdsfa");
    }
}
