package com.bsmlima.salesdataanalysis;

import com.bsmlima.salesdataanalysis.dao.ReportDao;
import com.bsmlima.salesdataanalysis.parser.FileParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public ReportDao reportDao() {
        String homePath = System.getenv("HOME");
        return new ReportDao(homePath + "/data/in", homePath + "/data/out");
    }

    @Bean
    public DirectoryWatcher directoryWatcher(ReportDao reportDao, FileParser fileParser) throws IOException {
        return new DirectoryWatcher(fileParser, reportDao);
    }

    @Bean
    public FileParser fileParser(ReportDao reportDao) {
        return new FileParser(reportDao);
    }
}
