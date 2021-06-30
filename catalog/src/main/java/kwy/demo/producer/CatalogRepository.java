package kwy.demo.producer;

import kwy.demo.commons.Catalog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CatalogRepository extends ReactiveCrudRepository<Catalog, String> {
}
