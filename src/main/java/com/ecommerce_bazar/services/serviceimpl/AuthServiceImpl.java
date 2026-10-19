package com.ecommerce_bazar.services.serviceimpl;

import com.ecommerce_bazar.config.JwtProperties;
import com.ecommerce_bazar.dto.LoginRequest;
import com.ecommerce_bazar.dto.TokenResponse;
import com.ecommerce_bazar.services.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final JwtEncoder jwtEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtProperties jwtProperties, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtProperties = jwtProperties;
        this.jwtEncoder = jwtEncoder;
    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken.unauthenticated(
                        loginRequest.email().trim().toLowerCase(Locale.ROOT), loginRequest.password()));

        Instant now = Instant.now();
        Instant expiresAt = now.plus(jwtProperties.expiration());

        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(jwtProperties.issuer())
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(expiresAt)
                .claim("authorities", authorities)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = jwtEncoder.encode(
                JwtEncoderParameters.from(header, claims)).getTokenValue();
        return new TokenResponse(token, "Bearer",jwtProperties.expiration().toSeconds());
    }
}
