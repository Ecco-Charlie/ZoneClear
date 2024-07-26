package soft.exe.zone.clear.config.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter
{

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils)
    {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException
    {

        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer "))
        {
            String token = authorization.substring(7);

            DecodedJWT decodedJWT = jwtUtils.comprobarToken(token);

            String user = decodedJWT.getSubject();

            Collection<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(decodedJWT.getClaim("role").asString()));

            SecurityContext context = SecurityContextHolder.getContext();

            context.setAuthentication(new UsernamePasswordAuthenticationToken(user, null, authorities));

            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);

    }
}
