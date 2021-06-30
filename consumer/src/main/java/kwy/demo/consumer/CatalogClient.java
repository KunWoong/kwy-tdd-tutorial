package kwy.demo.consumer;

import kwy.demo.commons.Catalog;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class CatalogClient {

    private final WebClient webClient;

    public Flux<Catalog> getAllCatalog() {
        return webClient
                .get()
                .uri("http://localhost:8080/catalog")
                .retrieve()
                .bodyToFlux(Catalog.class);
    }
}
