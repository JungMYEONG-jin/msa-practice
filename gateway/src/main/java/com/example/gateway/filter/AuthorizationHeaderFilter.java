package com.example.gateway.filter;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gw에서 사용자에 대한 권한을 체크하려고 추가
 */
@Component
@Slf4j
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {
    private final Environment environment;

    public AuthorizationHeaderFilter(Environment environment) {
        super(Config.class); // casting 정보 전달
        this.environment = environment;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                log.info("uri {}", request.getURI());
                return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            }
            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String jwt = authorizationHeader.replace("Bearer", "");

            if (!isValidToken(jwt)) {
                return onError(exchange, "Token is invalid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }

    private Mono<Void> onError(ServerWebExchange exchange, String msg, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.error(msg);
        return response.setComplete();
    }

    private boolean isValidToken(String jwt) {
        boolean isValid = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(environment.getProperty("token.secret"))
                    .parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception ex) {
            isValid = false;
        }

        if (subject == null || subject.isEmpty()) {
            isValid = false;
        }
        return isValid;
    }

}
