package com.codegym.dto;

import javax.validation.constraints.*;

public class UserDto {
    private int id;

    @NotEmpty(message = "Không được để trống")
    @Size(min = 5, max = 45, message = "First name bắt buộc có độ dài tối thiểu 5, tối đa 45 ký tự")
    private String firstName;

    @Size(min = 5, max = 45, message = "Last name bắt buộc có độ dài tối thiểu 5, tối đa 45 ký tự")
    private String lastName;

    @NotEmpty(message = "Không được để trống")
    @Pattern(regexp = "^[0][9][0-9]{8}$", message = "Số điện thoại phải đúng 10 số và đúng định dạng: 090xxxxxxxx")
    private String phoneNumber;

    @Min(value = 18, message = "Tuổi không bé hơn 18")
    private int age;

    @NotEmpty(message = "Không được để trống")
    @Email(message = "Đúng định dạng email: abc123@gmail.com")
    private String email;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, String phoneNumber, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
