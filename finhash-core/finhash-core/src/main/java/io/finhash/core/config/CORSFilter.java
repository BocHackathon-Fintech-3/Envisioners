package io.finhash.core.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.resource.WebJarsResourceResolver;
import org.springframework.web.server.WebFilter;

@Configuration
public class CORSFilter implements WebFluxConfigurer {

    private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN";
    private static final String ALLOWED_METHODS = "GET, PUT, POST, DELETE, OPTIONS, PATCH";
    private static final String ALLOWED_ORIGIN = "*";
    private static final String MAX_AGE = "3600";

    private static final Logger LOGGER = LoggerFactory.getLogger(CORSFilter.class);


    @Autowired
    ObjectMapper mapper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/")
                .resourceChain(true)
                .addResolver(new WebJarsResourceResolver());
        //swagger resource
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
    }

    @Bean
    public WebFilter filter() {
        return (exchange, chain) -> {
            ServerHttpRequest req = exchange.getRequest();
            String uri = req.getURI().toString();
            LOGGER.trace("Intercepting request on uri: " + uri);
            try {
                LOGGER.trace(mapper.writeValueAsString(req.getQueryParams()));
                LOGGER.trace(mapper.writeValueAsString(req.getBody()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            return chain.filter(exchange);

        };
    }


}
