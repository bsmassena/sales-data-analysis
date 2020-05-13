package com.bsmlima.salesdataanalysis;

import com.bsmlima.salesdataanalysis.dao.ReportDao;
import com.bsmlima.salesdataanalysis.parser.FileParser;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {

    private final WatchService watchService;
    private FileParser fileParser;

    public DirectoryWatcher(FileParser fileParser, ReportDao reportDao) throws IOException {
        this.fileParser = fileParser;
        this.watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(reportDao.getInputPath());
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    public void watch() throws InterruptedException, IOException {
        while (true) {
            WatchKey queuedKey = watchService.take();
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                fileParser.parse(watchEvent.context().toString()); // TODO: catch errors on parsing
                queuedKey.reset();
            }
        }
    }
}
