package pl.arkani.LZ_2022301_LX.service;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;




@Service
public class MailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void  sendMail (String to,
                          String replyTo,
                           String subject,
                           String body,
                           boolean isHtmlContent ) throws MessagingException {
       // MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        jakarta.mail.internet.MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setReplyTo(replyTo);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body,isHtmlContent);
        javaMailSender.send(mimeMessage);

    }

}
