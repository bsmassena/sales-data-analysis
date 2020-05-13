package com.bsmlima.salesdataanalysis.application;

import com.bsmlima.salesdataanalysis.watcher.DirectoryWatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        DirectoryWatcher directoryWatcher = ac.getBean("directoryWatcher", DirectoryWatcher.class);
        try {
            directoryWatcher.watch();
        } catch (InterruptedException e) {
            System.out.println("Error: unable to watch directory");
            e.printStackTrace();
        }
    }
}
