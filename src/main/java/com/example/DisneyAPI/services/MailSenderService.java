package com.example.DisneyAPI.services;

import com.example.DisneyAPI.dto.UserDto;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.Mail;

@Service
public class MailSenderService {

    @Value("disneyAPI.preaceleración.sendgrid.apikey")
    private String apiKey;

    @Value("disneyAPI.preaceleración.sendgrid.senderemail")
    private String senderEmail;

    public void sendRegistrationEmail(UserDto user) {
        Email from = new Email(senderEmail);
        String subject = "Welcome to DisneyAPI";
        Email to = new Email(user.getMail());
        String contentText = "Welcome "+ user.getName() + " your registration has been confirmed. Your usarName is " 
                            + user.getUserName();
        Content content = new Content("text/plain", contentText);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv(apiKey));
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
