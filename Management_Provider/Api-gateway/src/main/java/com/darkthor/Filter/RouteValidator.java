package com.darkthor.Filter;

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
            "/api/v1/users/management/**",
            "/api/v1/users",
            "/eureka"
    );
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoint
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().matches(uri.replace("**", ".*")));
}

//    public Predicate<ServerHttpRequest> isSecured =
//            request -> openApiEndpoint
//                    .stream()
//                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
//}
