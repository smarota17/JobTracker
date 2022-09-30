package com.group21.jobTracker;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@PWA(name = "JobTracker", shortName = "Tracker", offlinePath="offline.html", offlineResources = { "./images/offline.png"})
public class MyApplication implements AppShellConfigurator{

    /**
     * The main method makes it possible to run the application as a plain Java
     * application which starts embedded web server via Spring Boot.
     *
     * @param args
     *            command line parameters
     */
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}