package soft.exe.zone.clear.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import soft.exe.zone.clear.entity.Credenciales;

import java.util.stream.Collectors;

@Component
public class JwtUtils
{


    @Value("${spring.security.jwt.user-creator}")
    private String UserCreator;

    @Value("${spring.security.jwt.private-key}")
    private String PrivateKey;

    public String createToken(UserDetails user)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(PrivateKey);

            System.out.println(PrivateKey);
            System.out.println(UserCreator);


            String autorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining());

            return JWT.create()
                    .withIssuer(UserCreator)
                    .withSubject(user.getUsername())
                    .withClaim("role", autorities)
                    .sign(algorithm);


        }catch(JWTCreationException e)
        {
            return null;
        }
    }

    public String createToken(Credenciales credenciales)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(PrivateKey);

            System.out.println(PrivateKey);
            System.out.println(UserCreator);

            return JWT.create()
                    .withIssuer(UserCreator)
                    .withSubject(credenciales.getEmail())
                    .withClaim("role", credenciales.getRole().getRol().name())
                    .sign(algorithm);


        }catch(JWTCreationException e)
        {
            return null;
        }
    }

    public DecodedJWT comprobarToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(PrivateKey);

             JWTVerifier a = JWT.require(algorithm)
                     .withIssuer(UserCreator)
                     .build();

             return a.verify(token);

        }catch (JWTDecodeException e)
        {
            return null;
        }
    }

}
