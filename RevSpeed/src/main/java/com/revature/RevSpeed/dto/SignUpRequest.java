package com.revature.RevSpeed.dto;

import com.revature.RevSpeed.models.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String FirstName;
    private String LastName;
    private String email;
    private String phoneNo;
    private String password;
    private String address;
    private boolean isBroadBandUser;
    private boolean isBusinessUser;

}
