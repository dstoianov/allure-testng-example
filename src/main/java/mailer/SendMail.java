package mailer;

import java.io.IOException;

/**
 * Created by Funker on 04.07.2015.
 */
public class SendMail {

    public static final String USERNAME = "test.techinsight@gmail.com";
    public static final String PASS = "22222299";

    public static void main(String[] args) throws IOException {

        String att1 = ZipUtils.zipTargetFolder("/target/surefire-reports/html/", "/report_reportng_");
        String att2 = ZipUtils.zipTargetFolder("/target/site/allure-maven-plugin/", "/report_allure_");
        String[] att = {att1, att2};

        SendMailTLS email = new SendMailTLS(USERNAME, PASS);
        email.sendEmail(USERNAME, "Travis-CI Report", "body text", att);


    }
}
