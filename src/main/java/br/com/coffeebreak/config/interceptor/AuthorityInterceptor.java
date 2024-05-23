package br.com.coffeebreak.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAuthority = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch("USER"::equals);

        if (request.getRequestURI().startsWith("/administrator") && hasAuthority) {
            throw new AccessDeniedException("Usuário sem permissão");
        }

        return true;
    }
}
