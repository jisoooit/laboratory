package com.example.practice01.service;

import com.example.practice01.dao.PaymentDAO;
import com.example.practice01.dto.ShopListDTO;
import com.example.practice01.dto.ShopListDetailDTO;
import com.example.practice01.entity.Payment;
import com.example.practice01.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PaymentService {
    private PaymentDAO paymentDAO;

    @Autowired
    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public ShopListDetailDTO getByPaymentId(int pmId){
        if (pmId ==0 ) return null;
        ShopListDetailDTO shopListDetailDTO = paymentDAO.shopListDetail(pmId);
//        Payment payment = paymentDAO.paymentSelect(pmId);
//        if (payment == null) return null;
//        Product product = paymentDAO.productSelect(payment.getPId());
//        if (product == null) return null;
//
//        ShopListDetailDTO shopListDetailDTO = ShopListDetailDTO.builder()
//                .pmId(pmId)
//                .uId(payment.getUId())
//                .pId(payment.getPId())
//                .cardName(payment.getCardName())
//                .cardNum(payment.getCardNum())
//                .paymentMethod(payment.getPaymentMethod())
//                .paymentDate(payment.getPaymentDate())
//                .progress(payment.getProgress())
//                .productNum(payment.getProductNum())
//                .savedPoint(payment.getSavedPoint())
//                .usePoint(payment.getUsePoint())
//                .pName(product.getPName())
//                .pPrice(product.getPPrice())
//                .companyName(product.getCompanyName())
//                .companyNamePhone(product.getCompanyNamePhone())
//                .build();
        return shopListDetailDTO;
    }



    public boolean removeByPaymentId(int paymentId){
        if (paymentId ==0) return false;

        int res = paymentDAO.delete(paymentId);
        return res>0;
    }

    public List<ShopListDTO> getShopList(int userId){
        List<ShopListDTO> shopListDTOs = new LinkedList<>();
        List<Payment> paymentList = paymentDAO.userPaymentAll(userId);
        if (paymentList == null) return null;
        for (Payment payment : paymentList){
            int pId = payment.getPId();
            Product product = paymentDAO.productSelect(pId);
            shopListDTOs.add(new ShopListDTO(payment.getPmId(),
                    payment.getUId(),
                    pId,
                    payment.getPaymentMethod(),
                    payment.getPaymentDate(),
                    payment.getProgress(),
                    payment.getProductNum(),
                    product.getPName(),
                    product.getPPrice(),
                    product.getCompanyName(),
                    product.getCompanyNamePhone()));
        }
        return shopListDTOs;
    }

    public List<ShopListDTO> getShopList2(int userId){
        List<ShopListDTO> shopListDTOs = paymentDAO.userPaymentAll2(userId);
        return shopListDTOs;
    }

    public ShopListDetailDTO getByPaymentId2(int pmId){
        if (pmId ==0 ) return null;

        Payment payment = paymentDAO.paymentSelect(pmId);
        if (payment == null) return null;
        Product product = paymentDAO.productSelect(payment.getPId());
        if (product == null) return null;

        ShopListDetailDTO shopListDetailDTO = ShopListDetailDTO.builder()
                .pmId(pmId)
                .uId(payment.getUId())
                .pId(payment.getPId())
                .cardName(payment.getCardName())
                .cardNum(payment.getCardNum())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDate(payment.getPaymentDate())
                .progress(payment.getProgress())
                .productNum(payment.getProductNum())
                .savedPoint(payment.getSavedPoint())
                .usePoint(payment.getUsePoint())
                .pName(product.getPName())
                .pPrice(product.getPPrice())
                .companyName(product.getCompanyName())
                .companyNamePhone(product.getCompanyNamePhone())
                .build();
        return shopListDetailDTO;
    }


}
