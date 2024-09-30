package com.productproject.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="cart_table")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Cart_Id")
    private int cid;

    @Column(name="quantity")
    @Min(value=1,message="minimum 1 prdt required")
    private long qty;

    @NotNull(message = "pid is required")
    @Min(value=1)
    @Column(name="product_Id")
    private int pid;

    @NotNull(message = "uid is required")
    @Min(value=1)
    @Column(name="User_Id")
    private int uid;
    }

    




