package com.math.mathcha.Util;

import com.math.mathcha.entity.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
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
import java.util.Date;
import java.util.StringJoiner;

@Service
public class SecurityUtil {




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

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issueTime(new Date(System.currentTimeMillis()))
                    .expirationTime(new Date(System.currentTimeMillis() + 1000*60*60*24*1))
                    .claim("scope", "ROLE_"+user.getRole())
                    .build();
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
            Payload payload = new Payload(claims.toJSONObject());
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            try{
                jwsObject.sign(new MACSigner(jwtKey.getBytes()));
                return jwsObject.serialize();
            }catch(JOSEException e){
                throw new RuntimeException(e);
            }
        }


}
