package com.bcsutil;

public class CustomSuccesType {

    private String succesMessage;

    public CustomSuccesType(String succesMessage){
        this.succesMessage = succesMessage;
    }

    public String getErrorMessage() {
        return succesMessage;
    }
}