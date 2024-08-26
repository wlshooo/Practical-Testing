package sample.cafekiosk.spring.client.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSendClient {

    public boolean sendEmail(String fromEmail, String toEmail, String subject, String content) {
        //실질적인 메일 전송 메서드
        log.info("메일 전송");
        //(메일을 전송할 때 마다 네트워크를 사용해야 한다)
        throw new IllegalArgumentException("메일 전송");
    }
}
