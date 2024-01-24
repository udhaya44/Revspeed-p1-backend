package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.Email;
import com.revature.RevSpeed.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins = "*")
public class EmailSendController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendemail")
//    @Timed(value="sendEmail.time",description="email sending")
    public ResponseEntity<?> sendEmail(@RequestBody Email email){
        System.out.println(email);
        boolean result =this.emailService.sendEmail(email.getSubject(), email.getMessage(),email.getToMail());
        if(result) {
//            registry.counter("sendEmail.counter").increment();
            return ResponseEntity.ok("Email is sent successfully.");

        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email Not send");
        }
    }


    @PostMapping(value="/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        System.out.println(email+newPassword);
        emailService.updatePassword(email, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }



}
