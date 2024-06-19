
package com.math.mathcha.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class Filter extends OncePerRequestFilter {

    private final List<String> publicEndpoints;

    public Filter(String[] publicEndpoints) {
        this.publicEndpoints = Arrays.asList(publicEndpoints);
    }
    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        boolean isPublicEndpoint = publicEndpoints.stream().anyMatch(publicEndpoint -> requestURI.startsWith(publicEndpoint));

        if (isPublicEndpoint) {
            jakarta.servlet.http.HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(request) {
                @Override
                public String getHeader(String name) {
                    if ("Authorization".equalsIgnoreCase(name)) {
                        return "";
                    }
                    return super.getHeader(name);
                }
            };
            filterChain.doFilter(requestWrapper, response);
        }else{
            filterChain.doFilter(request, response);
        }

    }
}
