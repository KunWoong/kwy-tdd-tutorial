package kwy.demo.producer;

import kwy.demo.commons.Catalog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CatalogHttpConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(CatalogRepository catalogRepository){
//        return route().GET("/catalog", new HandlerFunction<ServerResponse>() {
//                    @Override
//                    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
//                        return ServerResponse.ok().body(catalogRepository.findAll(), Catalog.class);
//                    }
//                }
//        ).build();
        return route().GET("/catalog", serverRequest -> ServerResponse.ok().body(catalogRepository.findAll(), Catalog.class)
        ).build();

    }
}
