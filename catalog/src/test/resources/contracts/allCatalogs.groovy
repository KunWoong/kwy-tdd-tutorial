package contracts

import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

Contract.make {
    description("return all catalogs")
    request {
        method("GET")
        url("/catalog")
    }
    response {
        status(HttpStatus.OK.value())
        headers {
            contentType(MediaType.APPLICATION_JSON_VALUE)
        }
        body([[id:"id-test", name:"catalog-test"]])
    }
}