package kwy.demo.producer;

import kwy.demo.commons.Catalog;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest
@Import(CatalogHttpConfiguration.class)
public class CatalogHttpTest {

    // CatalogHttpConfiguration 에서 repository 를 inject 받도록 하였는데,
    // WebFluxTest 를 통해 web tier 에서 테스트를 하기 때문에
    // repository 를 mock 으로 주입하였다. replace or create 로 작동한다.
    @MockBean
    CatalogRepository catalogRepository;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void getTest(){
        // 위 mock bean 은 empty object 인데,
        // stub 로, 즉 pre-programmed way 의 stub 가 필요하다.
        Mockito.when(catalogRepository.findAll())
                .thenReturn(Flux.just(new Catalog("id-mock", "catalog-mock")));

        this.webTestClient.get()
                .uri("http://localhost:8080/catalog")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectStatus().isOk()
                .expectBody().jsonPath("@.[0].name", "catalog-1").exists();
    }
}
