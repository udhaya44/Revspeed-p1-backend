package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.User;
import com.revature.RevSpeed.repositorys.UserRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    public boolean sendEmail(String subject, String message, String toMail) {

        String fromMail= "aakashsolanke99@gmail.com";

        boolean f = false;

        //Variable for mail
        String host="smtp.gmail.com";


        Properties properties = System.getProperties();
        System.out.println("PROPERTIES "+properties);

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");



        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromMail, "phid tffj zain pxvs");
            }
        });

        session.setDebug(true);


        MimeMessage m = new MimeMessage(session);

        try {


            m.setFrom(fromMail);

//            InternetAddress[] recipients = new InternetAddress[toMail.length];
//            int count = 0;
//            for(String res : toMail) {
//                recipients[count]= new InternetAddress(res.trim());
//                count++;
//            }

            m.setRecipients(Message.RecipientType.TO, toMail);



            m.setSubject(subject);



            m.setText(message);

            Transport.send(m);

            System.out.println("Sent success...................");
            f=true;

        }catch (Exception e) {
            e.printStackTrace();
        }

        return f;



    }


//    public void updatePassword(String email, String newPassword) {
//
//        Optional<User> userOptional = userRepository.findByEmail(email);
//        System.out.println(userOptional);
//
//        if (userOptional.isPresent()) {
//
//            User user = userOptional.get();
//            user.setPassword( passwordEncoder.encode(newPassword));
//            userRepository.save(user);
//        } else {
//
//            // Handle the case where the user with the given email is not found
//            // You can throw an exception, return a specific response, etc.
//        }
//    }



}
