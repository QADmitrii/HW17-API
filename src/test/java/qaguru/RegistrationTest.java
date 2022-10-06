package qaguru;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTest {
    String urlReg = "https://reqres.in/api/register";
    String urlLog = "https://reqres.in/api/login";


    @Test
    @DisplayName("Positive. Registration account")
    public void registrationTestSuccessful() {
        TestData testData = new TestData();
        testData
                .setEmail("eve.holt@reqres.in")
                .setPassword("pistol");

        given()
                .contentType(JSON)
                .body(testData)
                .log().body()
                .when()
                .post(urlReg)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Positive. Login account")
    public void deleteUser() {
        String body = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";
        given()
                .contentType(JSON)
                .body(body)
                .log().body()
                .when()
                .post(urlLog)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }

    @Test
    @DisplayName("Negative. Registration account without pass")
    public void missingPasswordRegistrationTest() {
        TestData testData = new TestData();
        testData
                .setEmail("eve.holt@reqres.in");

        given()
                .contentType(JSON)
                .body(testData)
                .log().body()
                .when()
                .post(urlReg)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    @DisplayName("Negative. Registration account without email")
    public void missingEmailRegistrationTest() {
        TestData testData = new TestData();
        testData
                .setPassword("pistol");

        given()
                .contentType(JSON)
                .body(testData)
                .log().body()
                .when()
                .post(urlReg)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing email or username"));
    }

    @Test
    @DisplayName("Negative. Registration account with wrong data")
    public void wrongEmailRegistrationTest() {
        TestData testData = new TestData();
        testData
                .setEmail("111#$!@#@test.io")
                .setPassword("pistol");

        given()
                .contentType(JSON)
                .body(testData)
                .log().body()
                .when()
                .post(urlReg)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Note: Only defined users succeed registration"));
    }


}
