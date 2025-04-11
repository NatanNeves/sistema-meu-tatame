package com.web.meutatame.tcc.services.autenticacao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.web.meutatame.tcc.services.CookieService;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String usuarioId = null;
        try {
            usuarioId = CookieService.getCookie(request, "usuarioId");
        } catch (Exception e) {
            // loga o erro se quiser
        }

        if (usuarioId != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
