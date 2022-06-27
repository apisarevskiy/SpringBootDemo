package my.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@ConfigurationProperties("application")
@Component
public class AppConfigImpl implements AppConfig {

    private String pathFileCsv;

    private int countRightQuestions;

    public void setPathFileCsv(String pathFileCsv) {
        this.pathFileCsv = pathFileCsv;
    }

    public void setCountRightQuestions(int countRightQuestions) {
        this.countRightQuestions = countRightQuestions;
    }
    public String getPathFileCsv() { return pathFileCsv; }

    public int getCountRightQuestions() { return countRightQuestions; }
}
