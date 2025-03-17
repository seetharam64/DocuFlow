package com.docuflow.userauthentication.service;

import com.docuflow.userauthentication.entity.UserEntity;
import com.docuflow.userauthentication.repository.UserRepository;
import com.docuflow.userauthentication.requestDTO.RequestDTO;
import com.docuflow.userauthentication.responseDTO.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public ResponseEntity<String> saveUserInfo(RequestDTO userRequest) {
        if(userRequest.getUserName().isBlank()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("UserName field is required !");
        } else if (userRequest.getUserEmail().isBlank()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email field is required !");
        } else if (userRequest.getUserPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Password field is required !");
        }else {
            UserEntity user = new UserEntity();
            user.setUserName(userRequest.getUserName());
            user.setUserEmail(userRequest.getUserEmail());
            user.setUserPassword(passwordEncoder().encode(userRequest.getUserPassword()));
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User with username " + userRequest.getUserName()
                    + " created!");
        }
    }

    public ResponseEntity<String> userLogin(RequestDTO request){
        if(request.getUserName().isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UserName field is required !");
        } else if (request.getUserPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password field is required !");
        }
        UserEntity user = userRepository.findByuserName(request.getUserName());
        if(!user.getUserName().equals(request.getUserName())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect User Name!");
        } else if (!passwordEncoder().matches(request.getUserPassword(), user.getUserPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password!");
        }else if(user.getUserName().equals(request.getUserName()) &&
        passwordEncoder().matches(request.getUserPassword(), user.getUserPassword())){
            return ResponseEntity.ok().body("User Logged in Successfully!");
        }
        return null;
    }


}
