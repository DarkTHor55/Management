package com.darkthor.Filter;

import com.darkthor.Util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoint = List.of(
            "/api/v1/users/login",
            "/api/v1/users/signup",
            "/api/v1/users/customer/**",
            "/api/v1/users/bill/**",
            "/api/v1/users/payroll/**",
            "/eureka"

    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoint
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
