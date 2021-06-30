package kwy.demo.commons;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CatalogPojoTest {
    @Test
    public void createObject() throws Exception{
        Catalog catalog = new Catalog("id1", "Kwy");
        Assertions.assertEquals(catalog.getName(), "Kwy");
        Assertions.assertNotNull(catalog);
    }
}