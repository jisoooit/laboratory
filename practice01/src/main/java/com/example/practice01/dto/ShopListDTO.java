package com.example.practice01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShopListDTO {
    private int pmId;
    private int uId;
    private int pId;

    private String paymentMethod;
    private String paymentDate;
    private String progress;
    private int productNum;

    private String pName;
    private int pPrice;
    private String companyName;
    private String companyNamePhone;

    @Builder
    public ShopListDTO(int pmId, int uId, int pId, String paymentMethod, String paymentDate, String progress, int productNum, String pName, int pPrice, String companyName, String companyNamePhone) {
        this.pmId = pmId;
        this.uId = uId;
        this.pId = pId;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.progress = progress;
        this.productNum = productNum;
        this.pName = pName;
        this.pPrice = pPrice;
        this.companyName = companyName;
        this.companyNamePhone = companyNamePhone;
    }
}
