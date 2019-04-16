package com.cnstock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class GathernewsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(GathernewsApplication.class);
    }

//	public static void main(String[] args) throws IOException {
//
//		String projectPath = new File("").getAbsolutePath();
//		String configPath = projectPath + "/config/application.properties";
//		Properties properties = new Properties();
//		InputStream inputStream = new BufferedInputStream(new FileInputStream(configPath));
//		properties.load(inputStream);
//		SpringApplication app = new SpringApplication(GathernewsApplication.class);
//		properties.load(inputStream);
//		app.setDefaultProperties(properties);
//		app.run(args);
//	}
    public static void main(String[] args) {
        SpringApplication.run(GathernewsApplication.class,args);
    }

}

