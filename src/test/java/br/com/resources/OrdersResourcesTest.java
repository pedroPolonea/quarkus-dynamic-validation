package br.com.resources;

import br.com.domain.OrderVO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

@QuarkusTest
class OrdersResourcesTest {

    private static final String CLERCK = "O atributo 'clerk' deve ser preenchido";

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
                        CLERCK
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

    @Test
    void shouldReturnOneErrorInStausCanceled() {
        final OrderVO order = createOrder();
        order.setStatusCurrent("CANCELED");

        given()
                .body(order)
                .contentType(ContentType.JSON)
                .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(400)
                .body("errorMessage", hasItems(
                        CLERCK
                ));
    }



    private OrderVO createOrder(){
        return OrderVO.builder()
                .client("Ciclano")
                .statusCurrent("OPEN")
                .build();
    }
}