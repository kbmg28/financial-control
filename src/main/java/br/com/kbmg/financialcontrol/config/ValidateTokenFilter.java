package br.com.kbmg.financialcontrol.config;

import br.com.kbmg.financialcontrol.exceptions.AuthorizationException;
import br.com.kbmg.financialcontrol.exceptions.ForbiddenException;
import br.com.kbmg.financialcontrol.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ValidateTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {

        if (isRequestForApi(request)) {
            try {
                validateJwtToken(request);

            } catch (AuthorizationException e) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
                return;
            } catch (ForbiddenException e) {
                response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
                return;
            } catch (Exception e) {
                log.warn("Not defined exception on filter. Details: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isRequestForApi(HttpServletRequest request) {
        return request.getRequestURL().indexOf("/api/") >= 0 && request.getRequestURL().indexOf("/api/auth") < 0;
    }

    private void validateJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        String jwtToken = jwtService.getJwtValidated(authorization);
        String email = jwtService.getEmailFromJwt(jwtToken);

        UserDetails userDetails = User.builder().username(email).roles("").password("").build();

        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(userToken);
    }


}
