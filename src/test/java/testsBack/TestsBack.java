package testsBack;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.Data;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestsBack {

    static String customerId = "";
    static String checkingAccountId = "";
    static String savingsAccountId = "";

    @Test
    @Tag("Back")
    public void paginaRegistro() {
        given()
                .when()
                .get("https://parabank.parasoft.com/parabank/register.htm")
                .then()
                .assertThat().statusCode(200)
                .log().status();
    }

    @Test
    @Order(1)
    @Tag("Back")
    public void login() {
        Response response =
                given()
                        .header("accept", "application/json")
                        .get("https://parabank.parasoft.com/parabank/services/bank/login/"
                                + Data.USERNAME + "/" + Data.PASSWORD);

        response
                .then()
                .assertThat().statusCode(200)
                .log().status()
                .log().body();

        customerId = response.jsonPath().getString("id");
        System.out.println("CustomerId: " + customerId);

    }


    @Test
    @Order(2)
    @Tag("Back")
    public void verResumenDeCuentas() {
        String url = "https://parabank.parasoft.com/parabank/services/bank/customers/" + customerId + "/accounts";
        Response response =
                given()
                        .header("accept", "application/json")
                        .when().get(url);
        response
                .then()
                .assertThat().statusCode(200)
                .log().status()
                .log().body();

        checkingAccountId = response.jsonPath().getString("[0].id");
        System.out.println("CheckingAccountId: " + checkingAccountId);
    }

    @Test
    @Order(3)
    @Tag("Back")
    public void aperturaDeCuentaExitosa() {
        Response response =
                given()
                        .header("accept", "application/json")
                        .queryParam("customerId", customerId)
                        .queryParam("newAccountType", 1)
                        .queryParam("fromAccountId", checkingAccountId)
                        .when()
                        .post("https://parabank.parasoft.com/parabank/services/bank/createAccount");

        response
                .then()
                .assertThat().statusCode(200)
                .log().status()
                .log().body();

        savingsAccountId = response.jsonPath().getString("id");
        System.out.println("SavingsAccountId: " + savingsAccountId);
    }

    @Test
    @Order(4)
    @Tag("Back")
    public void transferenciaDeFondosExitosa() {

        given()
                .header("accept", "application/json")
                .queryParam("fromAccountId", checkingAccountId)
                .queryParam("toAccountId", savingsAccountId)
                .queryParam("amount", 125)
                .when()
                .post("https://parabank.parasoft.com/parabank/services/bank/transfer")
                .then()
                .assertThat().statusCode(200)
                .log().status()
                .log().body();
    }

    @Test
    @Order(5)
    @Tag("Back")
    public void verActividadDeCuenta() {
        String url = "https://parabank.parasoft.com/parabank/services/bank/accounts/" +
                checkingAccountId + "/transactions/month/All/type/All";

        Response response =
                given()
                        .header("accept", "application/json")
                        .when().get(url);
        response
                .then()
                .assertThat().statusCode(200)
                .log().status()
                .log().body();

    }

}
