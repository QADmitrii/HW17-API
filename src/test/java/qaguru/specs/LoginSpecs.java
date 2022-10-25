package qaguru.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;

import static org.hamcrest.CoreMatchers.notNullValue;
import static qaguru.helpers.AllureApi.withCustomTemplates;

public class LoginSpecs {

    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/login")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("token", notNullValue())
            .build();
    public static RequestSpecification getListUsers = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api/users?page")
            .log().uri()
            .log().body()
            .contentType(ContentType.JSON);
}