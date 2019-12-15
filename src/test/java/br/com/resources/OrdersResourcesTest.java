package br.com.resources;

import br.com.domain.OrderVO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class OrdersResourcesTest {

    @Test
    void addOrder() {
        final OrderVO order = OrderVO.builder()
                .clerk("Fulano")
                .client("Ciclano")
                //.openDate(LocalDateTime.now().toString())
                .statusCurrent("OPEN")
                .build();

        given()
                .contentType(ContentType.JSON)
                .body(order)
        .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(200);
                //.body(is("hello"));
    }
}