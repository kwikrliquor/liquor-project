package com.example.springblog.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
  //    @Value("${twilio_sid}")
//    private String SID;
//    @Value("${twilio_token}")
//    private String uploadPath;
  // Find your Account Sid and Auth Token at twilio.com/console
  public static final String ACCOUNT_SID =
      "AC482f078e90585c8ef6a184c90889a9b4";
  public static final String AUTH_TOKEN =
      "a2a9ca70b5d50d98c7835144f9c56998";

  //    public static void main(String[] args) {
  public void sendTextDelivery(){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message
        .creator(new PhoneNumber("+12105162093"), // to
            new PhoneNumber("+12108803863"), // from
            "Your Order is Out for Delivery!")
        .create();

    System.out.println(message.getSid());
  }

  public void sendTextDelivered(){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message
        .creator(new PhoneNumber("+12105162093"), // to
            new PhoneNumber("+12108803863"), // from
            "Your Order is Delivered")
        .create();

    System.out.println(message.getSid());
  }

}
