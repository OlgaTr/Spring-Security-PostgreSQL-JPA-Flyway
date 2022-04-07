package com.trainingcenter.security;

import com.trainingcenter.entities.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationServerProxy {

    private final RestTemplate rest;

    public AuthenticationServerProxy(RestTemplate rest) {
        this.rest = rest;
    }

    @Value("${auth.server.baseurl}")
    private String baseUrl;

    public void sendAuth(String username, String password) {

        String url = baseUrl + "/auth";
        var user = new User(username, password);
        rest.postForEntity(url, new HttpEntity<>(user), Void.class);
    }

    public ResponseEntity<Void> sendOTP(String username, String code) {
        String url = baseUrl + "/check";
        User user = new User();
        user.setUsername(username);
        user.setCode(code);
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<Void> response = rest.postForEntity(url, request, Void.class);
        return response;
    }
}
