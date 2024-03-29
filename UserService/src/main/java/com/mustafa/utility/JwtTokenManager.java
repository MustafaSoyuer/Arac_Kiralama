package com.mustafa.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mustafa.utility.enums.ERole;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    /**
     * 1. Kullanıcılara Token oluşturmak
     * 2. Gelen Token bilgisini doğrulamak
     */
    private final String SECRETKEY="QBBo6MNSm6xat+6B%zHCL_sBev2e~=KEOfdqk(ihaAj3Kuv9JX"; //255^6 ihtimal -> 3^6*100^6 ->81.000.000.000.000 / 3.400.000.000saniyede ->27.000sn
    private final String ISSUER = "Java13Auth";
    private final Long EXDATE = 1000L * 60 * 2 ; // 2 dakika

    public Optional<String> createToken(Long authId){
        String token;
        try{
            token = JWT.create().withAudience()
                    .withClaim("authid",authId)
                    .withIssuer(ISSUER) //oluşturan kişi
                    .withIssuedAt(new Date()) //oluşma zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE)) // token ın ne zaman süresinin dolacağı
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    public Optional<String> createToken(Long id, ERole role){
        String token = null;
        try {
            token = JWT.create()
                    .withAudience()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+EXDATE))
                    .withClaim("id",id)
                    .withClaim("role",role.toString())
                    .sign(Algorithm.HMAC512(SECRETKEY));
            return Optional.of(token);

        } catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }


    public Optional<Long> validateToken(String token){
        try {
            /**
             * Token doğrularken ve içinden bilgileri çekerken ilk olarak
             * 1- şifreleme algoritmasını kullanarak token verify edilmeli.
             * 2- Bu doğulama işleminde süresinin dolup dolmadığı da kontrol edilmesi
             * 3- Sahiplii bizim mi
             * Bunlar okey ise token decode edilmiş oluyor. Sonrasında ise claim nesnelerinin içinden
             * bilgiler alınarak dönüş yapılır.
             */
            Algorithm algorithm = Algorithm.HMAC512(SECRETKEY);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            Long authId = decodedJWT.getClaim("authid").asLong();
            return Optional.of(authId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
