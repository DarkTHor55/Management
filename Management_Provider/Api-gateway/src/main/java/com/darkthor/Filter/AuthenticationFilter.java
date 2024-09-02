package com.darkthor.Filter;

import com.darkthor.Util.JwtUtils;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private RestTemplate template;
    @Autowired
    private JwtUtils jwtUtils;
    public AuthenticationFilter() {
        super(Config.class);
    }
    public static class Config {
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
//                header have toekn or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Not haveAuthorization header");
                }
                String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authHeader!=null&&authHeader.startsWith("Bearer ")){
                    authHeader=authHeader.substring(7);
                }
                try{
                     jwtUtils.validateToken(authHeader);
                    System.out.println("Valid access......");
                    return chain.filter(exchange);
                }catch (Exception e){
                    System.out.println("Invalid access......");
                    throw new RuntimeException("Invalid token");
                }
            }
            return chain.filter(exchange);
        };
    }
}
