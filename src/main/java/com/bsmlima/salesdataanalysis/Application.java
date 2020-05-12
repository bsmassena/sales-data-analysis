package com.bsmlima.salesdataanalysis;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, InterruptedException {
        DirectoryWatcher directoryWatcher = new DirectoryWatcher();
        directoryWatcher.watch();
    }
}
