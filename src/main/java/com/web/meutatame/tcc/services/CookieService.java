package com.web.meutatame.tcc.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

@Service
public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws IOException {
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor, "UTF-8"));
        cookie.setMaxAge(segundos);
        cookie.setHttpOnly(true); // mais seguro
        cookie.setPath("/"); // acessível em toda a aplicação
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String valor = Optional.ofNullable(request.getCookies())
                .flatMap(cookies -> Arrays.stream(cookies)
                        .filter(cookie -> key.equals(cookie.getName()))
                        .findAny()
                )
                .map(Cookie::getValue)
                .orElse(null);

        return valor != null ? URLDecoder.decode(valor, "UTF-8") : null;
    }
}
