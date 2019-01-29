package com.example.springblog.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID =
            "AC8f08bef3fa916dafd635be14e5df21e3";
    public static final String AUTH_TOKEN =
            "dae561f1ad2ac06899f21fcb3b118875";

    //    public static void main(String[] args) {
    public void sendTextDelivery(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+12105162093"), // to
                        new PhoneNumber("+18302660946"), // from
                        "Your Order is Out for Delivery!")
                .create();

        System.out.println(message.getSid());
    }

    public void sendTextDelivered(){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+12105162093"), // to
                        new PhoneNumber("+18302660946"), // from
                        "Your Order is Delivered")
                .create();

        System.out.println(message.getSid());
    }
}
