package com.google.sso.spring_sso_google.config;

import com.google.sso.spring_sso_google.dtos.UserInfo;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

public class GoogleOpaqueTokenIntrospector implements OpaqueTokenIntrospector {
    private final WebClient userInfoClient;

    public GoogleOpaqueTokenIntrospector(WebClient userInfoClient) {
        this.userInfoClient = userInfoClient;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        //the token sent from front end are received as input parameter to this method
        UserInfo user =  userInfoClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("www.googleapis.com")
                        .path("oauth2/v3/userinfo").queryParam("access_token",token).build())
                .retrieve()
                .bodyToMono(UserInfo.class)
                .block();
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("sub",user.sub());
        attributes.put("name",user.name());
        return  new OAuth2IntrospectionAuthenticatedPrincipal(user.name(),attributes,null);
    }

}
