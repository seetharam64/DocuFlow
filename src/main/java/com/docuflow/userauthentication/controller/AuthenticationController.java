package com.docuflow.userauthentication.controller;

import com.docuflow.userauthentication.requestDTO.RequestDTO;
import com.docuflow.userauthentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    AuthenticationService service;

    @GetMapping("/test")
    public String testEndpoint() {
        return "Controller is working!";
    }

    @PostMapping("/signup")
    public ResponseEntity<String> userSignup(@RequestBody RequestDTO request){
        return service.saveUserInfo(request);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@RequestBody RequestDTO request){
        return service.userLogin(request);
    }
}
