package com.example.DisneyAPI.services;

import com.example.DisneyAPI.dto.UserDto;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final String apiKey = "2a349bb2827c55bbd22513dca5c59a50";
    private final String secretKey = "4051ce94b4333a8a5b8632c1887c709a";
    private final String senderEmail = "juan.ci.caballero@gmail.com";
    public void sendRegistrationEmail(UserDto user) throws MailjetException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        client = new MailjetClient(apiKey, secretKey);
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", senderEmail)
                                        .put("Name", "Juan"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", user.getMail())
                                                .put("Name", user.getName())))
                                .put(Emailv31.Message.SUBJECT, "Greetings from DisneyAPI.")
                                .put(Emailv31.Message.TEXTPART, "My first Mailjet email")
                                .put(Emailv31.Message.HTMLPART, "<h3>Dear user, welcome to disneyAPI</h3><br />Enjoy the app!")
                                .put(Emailv31.Message.CUSTOMID, "AppGettingStartedTest")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
