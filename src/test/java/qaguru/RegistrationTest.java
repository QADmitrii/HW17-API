package qaguru;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qaguru.models.LombokBody;
import qaguru.models.LombokResponse;
import qaguru.pojodata.PojoBodyData;

import static org.assertj.core.api.Assertions.assertThat;
import static io.restassured.RestAssured.given;
import static qaguru.specs.ErrorsSpecs.errorResponse;
import static qaguru.specs.LoginSpecs.loginRequestSpec;
import static qaguru.specs.LoginSpecs.loginResponseSpec;
import static qaguru.specs.RegSpecs.regRequestSpec;
import static qaguru.specs.RegSpecs.regResponseSpec;

public class RegistrationTest {

    @Test
    @DisplayName("Positive. Registration account")
    public void registrationTestSuccessful() {
        LombokBody body = new LombokBody();

        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        LombokResponse response = given()

                .spec(regRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(regResponseSpec)
                .extract()
                .as(LombokResponse.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Positive. Login account")
    public void loginAccount() {
        LombokBody body = new LombokBody();

        body.setEmail("eve.holt@reqres.in");
        body.setPassword("pistol");
        LombokResponse response = given()

                .spec(loginRequestSpec)
                .body(body)
                .when()
                .post()
                .then()
                .spec(loginResponseSpec)
                .extract()
                .as(LombokResponse.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");

    }

    @Test
    @DisplayName("Negative. Registration account without pass")
    public void missingPasswordRegistrationTest() {
        PojoBodyData pojoBodyData = new PojoBodyData();
        pojoBodyData
                .setEmail("eve.holt@reqres.in");
        LombokResponse response = given()

                .spec(regRequestSpec)
                .body(pojoBodyData)
                .when()
                .post()
                .then()
                .spec(errorResponse)
                .extract()
                .as(LombokResponse.class);

        assertThat(response.getError()).isEqualTo("Missing password");
    }

    @Test
    @DisplayName("Negative. Registration account without email")
    public void missingEmailRegistrationTest() {
        PojoBodyData pojoBodyData = new PojoBodyData();
        pojoBodyData
                .setPassword("pistol");

        LombokResponse response = given()

                .spec(regRequestSpec)
                .body(pojoBodyData)
                .when()
                .post()
                .then()
                .spec(errorResponse)
                .extract()
                .as(LombokResponse.class);

        assertThat(response.getError()).isEqualTo("Missing email or username");
    }

    @Test
    @DisplayName("Negative. Registration account with wrong data")
    public void wrongEmailRegistrationTest() {
        PojoBodyData pojoBodyData = new PojoBodyData();
        pojoBodyData
                .setEmail("111#$!@#@test.io")
                .setPassword("pistol");

        LombokResponse response = given()

                .spec(regRequestSpec)
                .body(pojoBodyData)
                .when()
                .post()
                .then()
                .spec(errorResponse)
                .extract()
                .as(LombokResponse.class);

        assertThat(response.getError()).isEqualTo("Note: Only defined users succeed registration");
    }


}
