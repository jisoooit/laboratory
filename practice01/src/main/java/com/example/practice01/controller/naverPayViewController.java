package com.example.practice01.controller;

import com.example.practice01.session.SessionMgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("shop-list")
public class naverPayViewController {

    private SessionMgr sessionMgr; // = SessionMgr.getInstance();

    @Autowired
    public naverPayViewController(SessionMgr sessionMgr) {
        this.sessionMgr = sessionMgr;
    }

    @GetMapping("/detail")
    public String shopListDetail(Model model, HttpSession session, HttpServletRequest httpServletRequest) {
        String view = "detail";
        if (session.getAttribute("SESSION_ID") != null) { // 로그인이 되어있는 상태
            return "redirect:/";
        }

        model.addAttribute("uId", sessionMgr.get(session));
        return view; //view못찾아서 오류남. view만들고 dispathcerServlet설정 제대로 해야...
    }



}
