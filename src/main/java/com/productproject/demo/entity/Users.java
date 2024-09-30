package com.productproject.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name="users_table")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_Id")
    private int uid;

    @NotBlank(message="fname cannot be blank")
    @Size(min=2,message="minimum 2 characters required")
    @Column(name="first_name")
    private String fname;

    @NotBlank(message="lname cannot be blank")
    @Size(min=1,message="minimum 2 characters required")
    @Column(name="last_name")
    private String lname;

    @NotBlank(message="email cannot be blank")
    @Column(name="email")
    @Email(message="enter a valid email")
    private String email;

    
}
