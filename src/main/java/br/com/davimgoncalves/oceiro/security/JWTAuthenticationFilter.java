package br.com.davimgoncalves.oceiro.security;

import br.com.davimgoncalves.oceiro.service.UsuarioDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioDetailService usuarioDetailService;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = request.getHeader("Authorization");
        var jwt = getTokenDetail(token);
        if(jwtUtil.isValid(jwt)) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(jwt));
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid authentication.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/v1/usuario/**", request.getServletPath());
    }

    private Authentication getAuthentication(String jwt) {
        var email = jwtUtil.getUsername(jwt);
        var user = usuarioDetailService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(email, null, user.getAuthorities());
    }

    private String getTokenDetail(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }
}
