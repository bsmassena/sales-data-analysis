package com.bsmlima.salesdataanalysis;

import java.io.IOException;
import java.nio.file.*;

public class DirectoryWatcher {

    private WatchService watchService;

    public DirectoryWatcher() throws IOException {
        watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(System.getenv("HOME") + "/data/in");
        WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
    }

    public void watch() throws InterruptedException {
        while (true) {
            WatchKey queuedKey = watchService.take();
            for (WatchEvent<?> watchEvent : queuedKey.pollEvents()) {
                System.out.printf("Created: %s\n", watchEvent.context());

                queuedKey.reset();
            }
        }
    }
}
