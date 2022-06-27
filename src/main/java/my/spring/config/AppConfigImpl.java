package my.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@ConfigurationProperties("application")
@Component
public class AppConfigImpl implements AppConfig {

    private String pathFileCsvEng;
    private String pathFileCsvRu;
    private String pathFileCsv;

    private int countRightQuestions;

    public void setPathFileCsvEng(String pathFileCsvEng) {
        this.pathFileCsvEng = pathFileCsvEng;
    }

    public void setPathFileCsvRu(String getPathFileCsvRu) {
        this.pathFileCsvRu = getPathFileCsvRu;
    }

    public void setCountRightQuestions(int countRightQuestions) {
        this.countRightQuestions = countRightQuestions;
    }
    public String getPathFileCsv() { return pathFileCsvEng; }

    public int getCountRightQuestions() { return countRightQuestions; }
}
