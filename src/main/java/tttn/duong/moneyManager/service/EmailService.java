package tttn.duong.moneyManager.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.properties.mail.smtp.from}")
//    @Value("${EMAIL_USERNAME}")
    private String fromEmail;

//    public void sendEmail(String to,String subject, String body){
//        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(fromEmail);
//            message.setTo(to);
//            message.setSubject(subject);
//            message.setText(body);
//            mailSender.send(message);
//        }catch (Exception e){
//            throw new RuntimeException("Failed to send email",e);
//        }
//    }

    public void sendEmail(String to, String subject, String htmlBody) {
        try {


            log.info("Sending email to: {}", to); // Thêm dòng này

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8"); // true = multipart (hỗ trợ HTML)

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true); // true = isHtml

            mailSender.send(message);

            log.info("Email sent successfully to: {}",to);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }

}
