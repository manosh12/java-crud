package com.example.java_crud.models;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {

    @NotEmpty(message = "The name is required!")
    private String name;

    @NotEmpty(message = "The gender is required!")
    private String gender;

    @NotEmpty(message = "The email is required!")
    private String email;

    @NotEmpty(message = "The phone is required!")
    private String phone;

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
