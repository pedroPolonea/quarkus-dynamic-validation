package br.com.resources;

import br.com.domain.OrderVO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
class OrdersResourcesTest {

    @Test
    void shouldReturnTwoErrorInOpenStatus() {
        final OrderVO order = createOrder();

        given()
            .body(order)
            .contentType(ContentType.JSON)
        .when()
            .post("/orders")
        .then()
            .log().all()
            .statusCode(400)
            .body("errorMessage", hasItems(
                        "O atributo 'openDate' deve ser preenchido",
                        "O atributo 'clerk' deve ser preenchido"
                    ));
    }

    void shouldNotReturnErrorInStatusProcessing() {
        final OrderVO order = createOrder();
        order.setStatusCurrent("PROCESSING");

        given()
            .body(order)
            .contentType(ContentType.JSON)
        .when()
            .post("/orders")
        .then()
            .log().all()
            .statusCode(200);
    }



    private OrderVO createOrder(){
        return OrderVO.builder()
                .client("Ciclano")
                .statusCurrent("OPEN")
                .build();
    }
}