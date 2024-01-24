package com.revature.RevSpeed.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Email {
    private String toMail;
    private String subject;
    private String message;
}
