package com.math.mathcha.Util;

import com.math.mathcha.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.StringJoiner;

@Service
public class SecurityUtil {
    private final JwtEncoder jwtEncoder;

    public SecurityUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    @Value("${mathcha_edu.jwt.base64-secret}")
    private String jwtKey;

    @Value("${mathcha_edu.jwt.token-validity-in-seconds}")
    private long jwtExpiration;

//    public String createToken(Authentication authentication) {
//        Instant now = Instant.now();
//        Instant validity = now.plus(this.jwtExpiration, ChronoUnit.SECONDS);
//
//        // @formatter:off
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuedAt(now)
//                .expiresAt(validity)
//                .subject(authentication.getName())
//                .claim("mathcha_edu", authentication)
//                .build();
//        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
//        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
//    }
        public String createToken(User user) {
            Instant now = Instant.now();
            Instant validity = now.plus(this.jwtExpiration, ChronoUnit.SECONDS);

            // @formatter:off
            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuedAt(now)
                    .expiresAt(validity)
                    .subject(user.getUsername())
                    .claim("scope", buildScope(user))
                    .build();
            JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
            return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader,claims)).getTokenValue();
        }
        private String buildScope(User user){
            StringJoiner stringJoiner = new StringJoiner(" ");
            if (!CollectionUtils.isEmpty(user.getRoles()))
                user.getRoles().forEach(stringJoiner::add);

            return stringJoiner.toString();
        }

}
