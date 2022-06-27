package my.spring;

import my.spring.service.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

    public static void main(String[] args) {

        var context = SpringApplication.run(SpringBootDemoApplication.class, args);

        ApplicationRunner application = context.getBean(ApplicationRunner.class);
        application.runApplication();

//        Logger logger = LoggerFactory.getLogger(SpringBootDemoApplication.class);
//        logger.info("Моя первая запись в логгер");
    }

}
