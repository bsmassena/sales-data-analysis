package com.bsmlima.salesdataanalysis.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReportDao {

    private final String inputPath;
    private final String outputPath;

    public ReportDao(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public Stream<String> read(String filename) throws IOException {
        return Files.lines(Paths.get(inputPath, filename), StandardCharsets.UTF_8);
    }

    public void save(String filename, String content) throws IOException {
        Files.write(Paths.get(outputPath, filename), content.getBytes());
    }

    public String getInputPath() {
        return inputPath;
    }
}
