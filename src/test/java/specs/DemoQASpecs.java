package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class DemoQASpecs {

    public static ResponseSpecification responseLogs = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.ALL)
            .build();

    public static RequestSpecification requestWithJson = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();

    public static RequestSpecification requestCommon = with()
            .filter(withCustomTemplates())
            .log().all();
}
