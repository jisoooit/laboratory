package com.example.practice01.controller;

import com.example.practice01.dto.ShopListDTO;
import com.example.practice01.dto.ShopListDetailDTO;
import com.example.practice01.dto.User;
import com.example.practice01.entity.Member;
import com.example.practice01.response.BaseResponse;
import com.example.practice01.response.StatusEnum;
import com.example.practice01.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/shop-list")
@Validated
public class naverPayRestController {

    private PaymentService paymentService;

    @Autowired
    public naverPayRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("detail/{pmId}")
    public ResponseEntity<BaseResponse> getShopListDetailByUserId(@PathVariable @Min(1) Integer pmId) {
        ShopListDetailDTO shopListDetailDTO = paymentService.getByPaymentId(pmId);

        if (shopListDetailDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(StatusEnum.NOT_FOUND, shopListDetailDTO));
        }

        System.out.println(shopListDetailDTO);
        return ResponseEntity.ok()
                .body(new BaseResponse<>(shopListDetailDTO));
    }

    @DeleteMapping("/detail/{paymentId}")
    public ResponseEntity<BaseResponse> removeBypaymentId(@PathVariable @Min(1) Integer paymentId) {

        StatusEnum status = paymentService.removeByPaymentId(paymentId) ?
                StatusEnum.SUCCESS : StatusEnum.CANT_DELETE;

        return ResponseEntity.ok()
                .body(new BaseResponse(status));
    }

    @GetMapping("/ex/{id}")
    public Member expr (@PathVariable @Min(1) int id){
        Member member = new Member();
        System.out.println(member);
        return member;
    }


    /*쇼핑리스트 반환*/
    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse> getUserShopList(@PathVariable int userId){
        List<ShopListDTO> shopListDTOs = paymentService.getShopList2(userId);

        if (shopListDTOs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponse<>(StatusEnum.NOT_FOUND));
        }

        return ResponseEntity.ok()
                .body(new BaseResponse<>(shopListDTOs));
    }
}
