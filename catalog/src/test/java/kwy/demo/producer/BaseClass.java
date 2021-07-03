package kwy.demo.producer;

import io.restassured.RestAssured;
import kwy.demo.commons.Catalog;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;

@Import(CatalogHttpConfiguration.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseClass {

    @MockBean
    private CatalogRepository catalogRepository;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void before(){
        RestAssured.baseURI = "http://localhost:" + port;
        Mockito.when(catalogRepository.findAll())
                .thenReturn(Flux.just(new Catalog("id-test", "catalog-test")));
    }
}
