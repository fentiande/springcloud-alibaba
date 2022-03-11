package com.chow.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {

    public AgeRoutePredicateFactory() {
        super(AgeRoutePredicateFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Arrays.asList("minAge", "maxAge");
    }

    public Predicate<ServerWebExchange> apply(AgeRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                String ageStr = exchange.getRequest().getQueryParams().getFirst("age");
                if (StringUtils.isBlank(ageStr)) {
//                return false;
                    return true;
                }

                Integer age = Integer.parseInt(ageStr);
                if (age >= config.getMinAge() && age <= config.getMaxAge()) {
                    return true;
                }
                return false;
            }
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private int maxAge;

    }
}
