package com.productproject.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
// import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product_table")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_Id")
    private int pid;

    @NotBlank(message="pname required")
    @Column(name="prod_name")
    private String pname;

    @Column(name="description")
    private String desc;

    @Min(value = 50,message="please enter a value above 50")
    @Max(value = 100000,message="please enter a value below 100000")
    @Column(name="price")
    private long price;

    // @Lob
    // @Column(name="image")
    // @NotBlank(message="img is required")
    // private byte[] image;

    @Lob
    @Column(name="image",columnDefinition = "TEXT")
    @NotBlank(message="img is required")
    private String image;


    }


