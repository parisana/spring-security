package com.demo.spring_security.controller;

import com.demo.spring_security.config.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Parisana
 */
@Controller
@RequestMapping("/")
public class SecurityController {
    /*@GetMapping
    public String redirectLoginPage(@AuthenticationPrincipal CustomUserDetails currentUser){
        if (currentUser==null)
            return "redirect:/login";
        return "redirect:/messages/inbox";
    }*/
    @RequestMapping("login")
    public String getLoginPage(){
        return "login";
    }
}
