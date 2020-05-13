package com.bsmlima.salesdataanalysis;

import com.bsmlima.salesdataanalysis.dao.ReportDao;
import com.bsmlima.salesdataanalysis.service.ReportService;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {

    private final WatchService watchService;
    private ReportService reportService;

    public DirectoryWatcher(ReportService reportService, ReportDao reportDao) throws IOException {
        this.reportService = reportService;
        this.watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(reportDao.getInputPath());
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    public void watch() throws InterruptedException, IOException {
        while (true) {
            WatchKey queuedKey = watchService.take();
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                try {
                    reportService.processFile(watchEvent.context().toString()); // TODO: catch errors on parsing
                } catch ()
                queuedKey.reset();
            }
        }
    }
}
