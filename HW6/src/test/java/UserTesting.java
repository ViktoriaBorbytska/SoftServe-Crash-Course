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


public class UserTesting {
    PayloadGenerator generator = new PayloadGenerator();
    private final String mainUrl = "https://petstore.swagger.io/v2/user/";

    @Test (priority = 0)
    public void testUserCreation(){
        generator.generatePayload();
        String payload = generator.getPayload();
        given().
                contentType(ContentType.JSON).
                header("Content-Type","application/json" ).
                body(payload).
                when().
                post(mainUrl).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }

    @Test (priority = 1, dependsOnMethods = "testUserCreation")
    public void testUserGetting(){
        given().
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "" + generator.getUserName()).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L)).
                and().
                body("id", greaterThan(0)).
                and().
                body("password", equalTo(generator.getPassword())).
                and().
                body("email", equalTo(generator.getEmail())).
                and().
                body("username", equalTo(generator.getUserName()));
    }

    @Test (priority = 2, dependsOnMethods = "testUserCreation")
    public void testUserUpdate(){
        generator.setFirstName("Name");
        generator.setLastName("Surname");
        generator.setEmail("qwert123@abc.de");
        generator.generatePayload();
        String payload = generator.getPayload();
        given().
                contentType(ContentType.JSON).
                header("Content-Type","application/json" ).
                body(payload).
                when().
                put(mainUrl + "" + generator.getUserName()).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L));
    }

    @Test (priority = 3, dependsOnMethods = "testUserUpdate")
    public void testUserGettingAfterUpdate(){
        given().
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "" + generator.getUserName()).
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L)).
                and().
                body("id", greaterThan(0)).
                and().
                body("firstName", equalTo(generator.getFirstName())).
                and().
                body("lastName", equalTo(generator.getLastName())).
                and().
                body("email", equalTo(generator.getEmail()));
    }

    @Test (priority = 4, dependsOnMethods = "testUserCreation")
    public void testUserLogin(){
        given().
                param("username", generator.getUserName()).
                param("password", generator.getPassword()).
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "login").
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L)).
                and().
                body(containsString("logged in user session")).
                and().
                header("X-Expires-After", notNullValue()).
                and().
                header("X-Rate-Limit", notNullValue());
    }

    @Test (priority = 5, dependsOnMethods = "testUserLogin")
    public void testUserLogout(){
        given().
                when().
                header("Content-Type","application/json" ).
                get(mainUrl + "logout").
                then().
                assertThat().statusCode(200).
                and().
                contentType(ContentType.JSON).
                and().
                statusLine("HTTP/1.1 200 OK").
                and().
                time(lessThan(5000L)).
                and().
                header("X-Expires-After", equalTo(null)).
                and().
                header("X-Rate-Limit", equalTo(null));
    }

    @Test (priority = 6, dependsOnMethods = "testUserCreation")
    public void testUserDeletion(){
        given().
                when().
                header("Content-Type","application/json" ).
                delete(mainUrl + "" + generator.getUserName()).
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