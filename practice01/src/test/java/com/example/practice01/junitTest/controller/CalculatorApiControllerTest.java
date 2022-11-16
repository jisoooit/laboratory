 package com.example.practice01.junitTest.controller;

import com.example.practice01.junitTest.component.Calculator;
import com.example.practice01.junitTest.component.DollarCalculator;
import com.example.practice01.junitTest.component.MarketApi;
import com.example.practice01.junitTest.dto.CalculatorReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;

import static org.mockito.Mockito.when;

@WebMvcTest(CalculatorApiController.class)
@AutoConfigureMockMvc
@Import({Calculator.class, DollarCalculator.class})
public class CalculatorApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketApi marketApi;

    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest() throws Exception {


//        LinkedMultiValueMap<String , String> map = new LinkedMultiValueMap();
//        map.add("x","10");
//        map.add("y","10");

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/calculator/sum")
                        .queryParam("x","10")
                        .queryParam("y","10")

        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(
                MockMvcResultHandlers.print());
    }

    @Test
    public void minusTest() throws Exception {

        CalculatorReq req = new CalculatorReq();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/calculator/minus")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(
                MockMvcResultHandlers.print()
        );

    }
}
