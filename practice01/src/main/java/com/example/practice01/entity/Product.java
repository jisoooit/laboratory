package com.example.practice01.entity;

import lombok.Getter;

@Getter
public class Product {
    private int pId;
    private String pName;
    private int pPrice;
    private String companyName;
    private String companyNamePhone;

    public Product() {
    }

    public Product(int pId, String pName, int pPrice, String companyName, String companyNamePhone) {
        this.pId = pId;
        this.pName = pName;
        this.pPrice = pPrice;
        this.companyName = companyName;
        this.companyNamePhone = companyNamePhone;
    }
}
