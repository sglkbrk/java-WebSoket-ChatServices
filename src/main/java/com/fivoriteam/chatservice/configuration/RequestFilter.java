package com.fivoriteam.chatservice.configuration;


        import org.springframework.stereotype.Component;
        import org.springframework.web.filter.OncePerRequestFilter;

        import javax.servlet.FilterChain;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("login");

        System.out.println(authorizationHeader);
        filterChain.doFilter(request, response);
    }
}