package usopshiy.isec1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import usopshiy.isec1.utils.JWTUtils;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final JWTUtils jwtUtils;
    private final List<String> includedPaths = List.of(
            "/api/**"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return includedPaths.stream().noneMatch(pattern -> pathMatcher.match(pattern, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");

        if (!isHeaderValid(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or missing Authorization header");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private boolean isHeaderValid(String token) {
        return jwtUtils.validateJwtToken(token);
    }
}
