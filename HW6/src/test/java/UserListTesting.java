import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import test.payloadGenerator.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserListTesting {

    PayloadGenerator generator = new PayloadGenerator();
    private final String mainUrl = "https://petstore.swagger.io/v2/user/";
    private String userNameFromList = "user2";

    @Test (priority = 0)
    public void testUserListWithArrayCreation(){
        generator.setId(123);
        generator.setUserName("user1");
        generator.setFirstName("John");
        generator.setLastName("Johnson");
        generator.setEmail("john1@abc.de");
        generator.setPassword("watermelon");
        generator.setPhone("10293847");
        generator.setUserStatus(4);
        generator.generatePayload();
        String payload1 = generator.getPayload();

        generator.setId(124);
        generator.setUserName("user2");
        generator.setFirstName("Lana");
        generator.setLastName("Lanson");
        generator.setEmail("lana22@sdf.ad");
        generator.setPassword("lemonpie");
        generator.setPhone("13243546");
        generator.setUserStatus(2);
        generator.generatePayload();
        String payload2 = generator.getPayload();

        generator.setId(125);
        generator.setUserName("user3");
        generator.setFirstName("Dave");
        generator.setLastName("Davidson");
        generator.setEmail("dave99@test.uu");
        generator.setPassword("caramel");
        generator.setPhone("97867564");
        generator.setUserStatus(1);
        generator.generatePayload();
        String payload3 = generator.getPayload();

        String bigPayload = generator.generateTriplePayload(payload1, payload2, payload3);

        given().
                contentType(ContentType.JSON).
                header("Content-Type","application/json" ).
                body(bigPayload).
                when().
                post(mainUrl + "createWithArray").
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }

    @Test (priority = 1, dependsOnMethods = "testUserListWithArrayCreation")
    public void testUserFromListGetting(){
        given().
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L)).
                and().
                body("id", equalTo(124)).
                and().
                body("userStatus", equalTo(2));
    }

    @Test (priority = 2)
    public void testUserFromListUpdate(){
        generator.setPassword("c00kie");
        generator.setPhone("6677543873");
        generator.setUserStatus(0);
        generator.generatePayload();
        String payload = generator.getPayload();
        given().
                contentType(ContentType.JSON).
                header("Content-Type","application/json" ).
                body(payload).
                when().
                put(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }

    @Test (priority = 3, dependsOnMethods = "testUserFromListUpdate")
    public void testUserFromListGettingAfterUpdate(){
        given().
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }

    @Test (priority = 4, dependsOnMethods = "testUserListWithArrayCreation")
    public void testUser2Deletion(){
        given().
                when().
                header("Content-Type","application/json" ).
                delete(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
        userNameFromList = "user1";
    }

    @Test (priority = 4, dependsOnMethods = "testUserListWithArrayCreation")
    public void testUser1Deletion(){
        given().
                when().
                header("Content-Type","application/json" ).
                delete(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
        userNameFromList = "user3";
    }

    @Test (priority = 4, dependsOnMethods = "testUserListWithArrayCreation")
    public void testUser3Deletion(){
        given().
                when().
                header("Content-Type","application/json" ).
                delete(mainUrl + "" + userNameFromList).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }
}