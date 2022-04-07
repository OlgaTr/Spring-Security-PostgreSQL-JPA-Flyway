package com.trainingcenter.security.filters;

import com.trainingcenter.security.authentications.OtpAuthentication;
import com.trainingcenter.security.authentications.UsernamePasswordAuthentication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    private static final SecretKey KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String code = request.getHeader("code");

        if (code == null) {
            Authentication a = new UsernamePasswordAuthentication(username, password);
            authenticationManager.authenticate(a);
        } else {
            Authentication a = new OtpAuthentication(username, code);
            Authentication authentication = authenticationManager.authenticate(a);
            String role = authentication
                    .getAuthorities()
                    .stream()
                    .findFirst()
                    .get()
                    .getAuthority();

            String jws = Jwts.builder()
                    .setClaims(Map.of("username", username, "role", role))
                    .signWith(KEY)
                    .compact();
            response.setHeader("Authorization", jws);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    public static String getSecretKey() {
        return Encoders.BASE64.encode(KEY.getEncoded());
    }
}
