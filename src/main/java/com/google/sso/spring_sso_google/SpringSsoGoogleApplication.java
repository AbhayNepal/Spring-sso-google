 package com.google.sso.spring_sso_google;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
public class SpringSsoGoogleApplication {

    @GetMapping("/user")
    public Principal user(Principal principal) {
        System.out.println("username: " + principal.getName());
        return principal;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringSsoGoogleApplication.class, args);
    }

}
