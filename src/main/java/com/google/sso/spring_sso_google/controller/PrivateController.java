package com.google.sso.spring_sso_google.controller;

import com.google.sso.spring_sso_google.dtos.MessageDto;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;

@RestController
public class PrivateController {
    @GetMapping("/messages")
    public ResponseEntity<MessageDto> privateMessages( @AuthenticationPrincipal(expression = "name") String name) {
        return ResponseEntity.ok(new MessageDto("private content :" + name));
    }
}
