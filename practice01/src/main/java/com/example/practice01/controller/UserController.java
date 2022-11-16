package com.example.practice01.controller;

import com.example.practice01.dto.User;
import com.example.practice01.response.BaseResponse;
import com.example.practice01.response.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("app/user")
@Validated
public class UserController {

    @GetMapping("/quaryparam/id")
    public User getUserQuaryParam(@RequestParam int id){
        User user = new User(id, "jisu","j@naver.com");
        return user;
    }

    @GetMapping("/path-variable/{id}")
    public User getUserPathVariable(@PathVariable int id){
        User user = new User(id, "jisu","j@naver.com");
        return user;
    }

    @GetMapping("/quaryparam")
    public User getUser(User user){
        return user;
    }

    @GetMapping("/jsoninclude")
    public User user(){
        var user = new User();
        user.setName("jisu");
        return user;
    }

    @GetMapping("/quaryparam/re/id")
    public ResponseEntity<User> getResponseEntityUser(@RequestParam @Min(1) Integer id){
        //User user = new User(id, "jisu","j@naver.com");
        User user = User.builder()
                .userId(1)
                .name("jisu")
                .email("j@naver.com")
                .build();

        User user1 = User.builder()
                .userId(2)
                .name("choi")
                .build();
        return new ResponseEntity<>(user1,HttpStatus.BAD_REQUEST);
    }


    @PostMapping("/post")
    public ResponseEntity<User> post(@RequestBody User user){
        return ResponseEntity.ok().header("content","post").body(user); //헤더에도 값 지정해서 넣을 수 있네
    }

    @PutMapping("/put/{userId}")
    public ResponseEntity<User> put(@RequestBody User user, @PathVariable int userId){
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<User> delete(@RequestParam String name, @PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(new User(userId, name, "j@naver.com"));
    }

    @GetMapping("/baseResponse")
    public ResponseEntity<BaseResponse> response(@RequestParam String name){
        User user = User.builder()
                .userId(3)
                .name(name)
                .email("jj@naver.com")
                .build();

        //BaseResponse baseResponse = new BaseResponse();
        //baseResponse.setStatus(StatusEnum.OK);
        //baseResponse.setMessage("성공");
        //baseResponse.setData(user);

        BaseResponse baseResponse = BaseResponse.builder()
                .status(StatusEnum.BAD_REQUEST)
                .data(user)
                .build();

        BaseResponse baseResponse1 = new BaseResponse(user);
        return ResponseEntity.badRequest()
                .body(baseResponse);
    }

}
