package com.docuflow.userauthentication.responseDTO;

public class ResponseDTO {
    private String userName;
    private String userEmail;

    public ResponseDTO(){}
    public ResponseDTO(String username, String useremail){
        this.userEmail = useremail;
        this.userName = username;
    }
    public ResponseDTO(String username){
        this.userName = username;
    }
    public ResponseDTO(String useremail, boolean isEmail){
        this.userEmail = useremail;
    }
    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }
}
