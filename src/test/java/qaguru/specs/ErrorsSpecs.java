package qaguru.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.CoreMatchers.notNullValue;

public class ErrorsSpecs {
    public static ResponseSpecification errorResponse  = new ResponseSpecBuilder()

            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectBody("error", notNullValue())
            .build();
}
