package my.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@PropertySource("classpath:config.properties")
@Service
public class AppConfigImpl implements AppConfig {

    @Value("${db.pathCsv}")
    private String pathFileCsv;

    @Value("${db.countRightQuestions}")
    private String countRightQuestions;

    public String getPathFileCsv() {
        return pathFileCsv.replace("\"", "");
    }

    public int getCountRightQuestions() {
        return Integer.parseInt(countRightQuestions.replace("\"", ""));}
}
