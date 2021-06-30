package kwy.demo.producer;

import kwy.demo.commons.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class CatalogDocumentTest {

    @Autowired
    private CatalogRepository catalogRepository;

    @Test
    public void persistDocument(){
        Catalog catalog = new Catalog(null, "catalog-1");
        Mono<Catalog> save = catalogRepository.save(catalog);
        StepVerifier.create(save)
                .expectNextMatches(item -> item.getId() != null && item.getName().equals("catalog-1"))
                .verifyComplete();

    }
}
