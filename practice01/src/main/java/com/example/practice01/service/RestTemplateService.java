package com.example.practice01.service;


import com.example.practice01.dto.Req;
import com.example.practice01.dto.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // http://localhost/api/server/hello
    //response
    public String hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost")
                .path("/api/server/hello")
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }

    //json으로 받는거임
    public User hello2(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost")
                .path("/api/server/hello")
                .queryParam("name","steve")
                .queryParam("age",10)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> result = restTemplate.getForEntity(uri, User.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }


    public User post(){
        //http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();

        System.out.println(uri);

        //http body -> object -> object Mapper -> json -> rest template -> http body의 json으로

        User user = new User();
        user.setName("steve");
        user.setEmail("gmail.com");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.postForEntity(uri, user, User.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());

        return  response.getBody();
    }

    public User exchange(){
        //http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();

        System.out.println(uri);

        //http body -> object -> object Mapper -> json -> rest template -> http body의 json으로

        User user = new User();
        user.setName("steve");
        user.setEmail("gmail.com");


        RequestEntity<User> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header","ffff")
                .body(user);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(requestEntity, User.class);

        return response.getBody();
    }

    public User genericExchange(){
        //http://localhost:9090/api/server/user/{userId}/name/{userName}

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve")
                .toUri();

        System.out.println(uri);

        //http body -> object -> object Mapper -> json -> rest template -> http body의 json으로

        User user = new User();
        user.setName("steve");
        user.setEmail("gmail.com");

        Req<User> req = new Req<>();
        req.setHeader(
                new Req.Header()
        );

        req.setBody(user);



        RequestEntity<Req<User>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header","ffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Req<User>> response = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<Req<User>>() {});

        return response.getBody().getBody();
    }

}
