package com.trainingcenter.security.auth_providers;

import com.trainingcenter.security.AuthenticationServerProxy;
import com.trainingcenter.security.authentications.OtpAuthentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private final AuthenticationServerProxy proxy;

    public OtpAuthenticationProvider(AuthenticationServerProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();;
        String code = String.valueOf(authentication.getCredentials());

        var response = proxy.sendOTP(username, code);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return new OtpAuthentication(username, code, getAuthorities(response));
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return OtpAuthentication.class.isAssignableFrom(authentication);
    }

    public Collection<? extends GrantedAuthority> getAuthorities(ResponseEntity<Void> response) {
        List<String> roles = response.getHeaders().get("role");
        assert roles != null;
        String role = roles.get(0);
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }
}
