package kwy.demo.consumer;

import com.github.tomakehurst.wiremock.client.WireMock;
import kwy.demo.commons.Catalog;
import lombok.var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
// 아래 stub 의 configuration 을 잡아준다.port 는 default 로 8080이긴 하다.
@AutoConfigureWireMock(port = 8080)
class ConsumerApplicationTest {

    @Autowired
    CatalogClient catalogClient;

    @Test
    public void clientTest(){
        var data = "[{\"id\": \"id-test\", \"name\": \"catalog-test\"}]";

        // 테스트용 서버가 필요한 경우,
        // 항상 그 서버가 존재할 수 없다.
        // 이런 경우, stub 이 용이한데, 다음의 코드가
        // pre-programmed way stub 이다.
        WireMock.stubFor(
                WireMock.get(WireMock.urlEqualTo("/catalog"))
                .willReturn(
                        WireMock.aResponse()
                        .withBody(data)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(HttpStatus.OK)
                        )
        );

        Flux<Catalog> catalogs = catalogClient.getAllCatalog();
        StepVerifier.create(catalogs)
                .expectNextMatches(catalog -> catalog.getId() != null && catalog.getName().equals("catalog-test"))
                .verifyComplete();

    }

}