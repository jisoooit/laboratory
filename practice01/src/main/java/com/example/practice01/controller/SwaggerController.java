package com.example.practice01.controller;

import com.example.practice01.dto.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Swagger Test API"})
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "x", value = "x값"),
//            @ApiImplicitParam(name="y", value = "y값")
//    })
    @GetMapping("/hello")
    public int hello(@ApiParam(value = "x좌표") @RequestParam int x,
                                @ApiParam(value = "y좌표") @PathVariable int y){
        return x+y;
    }

    @GetMapping("/user")
    public User getUser(User user){
        return new User(user.getUserId(), user.getName(), user.getEmail());
    }


    @ApiResponses({
            @ApiResponse(code = 404, message = "notfound"),
            @ApiResponse(code=502, message = "잘못된 입력입니다.")
    })
    @PostMapping("/user")
    public User postUser (User user){
        return new User(user.getUserId(), user.getName(), user.getEmail());
    }
}
