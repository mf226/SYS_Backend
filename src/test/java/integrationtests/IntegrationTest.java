package integrationtests;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import org.apache.catalina.LifecycleException;
import org.junit.AfterClass;
import org.junit.Test;
import testutils.EmbeddedTomcat;
import utils.PuSelector;

public class IntegrationTest {

    protected static int SERVER_PORT;
    protected static String APP_CONTEXT;
    protected static String SERVER_URL;

    public IntegrationTest() {
    }

    private static EmbeddedTomcat tomcat;

    @BeforeClass
    public static void setUpBeforeAll() throws ServletException, MalformedURLException, LifecycleException {
        System.out.println("INTEGRATION TEST");
        PuSelector.getEntityManagerFactory("pu_integration_test");
        SERVER_PORT = 7777;
        APP_CONTEXT = "/jwtbackend";
        SERVER_URL = "http://localhost";

        //Setup and start Embedded Tomcat for testing
        tomcat = new EmbeddedTomcat();
        tomcat.start(SERVER_PORT, APP_CONTEXT);

        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.basePath = APP_CONTEXT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterClass
    public static void tearDownAfterAll() {
        tomcat.stop();
    }

//  //This is how we hold on to the token after login, similar to that a client must store the token somewhere
//  private static String securityToken;
//
//  //Utility method to login and set the returned securityToken
//  private static void login(String role, String password) {
//    String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
//    securityToken = given()
//            .contentType("application/json")
//            .body(json)
//            .when().post("/api/login")
//            .then()
//            .extract().path("token");
//  }
//
//  private void logOut() {
//    securityToken = null;
//  }
    @Test
    public void serverIsRunning() {
        System.out.println("Testing is server UP");
        given().when().get("/").then().statusCode(200);
    }

    @Test
    public void testTestEndpoint() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/test").then()
                .statusCode(200);
    }

    @Test
    public void testGetAllEndpoint() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/all").then()
                .statusCode(200);
    }

    @Test
    public void testGetAllAvailableEndpoint() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/available/2019-01-01/2019-02-02").then()
                .statusCode(200);
    }

    @Test
    public void testGetAllAvailableEndpoint2() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/available/20190101/20190202").then()
                .statusCode(409);
    }

    @Test
    public void testGetSpecificEndpoint() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/AH07908/dueinator").then()
                .statusCode(200);
    }

    @Test
    public void testGetSpecificEndpoint2() {
        given()
                .contentType("application/json")
                .when()
                .get("/api/car/ZZZZZZZ/dueinator").then()
                .statusCode(409);
    }

//  @Test
//  public void testRestForAdmin() {
//    login("admin", "test");
//    given()
//            .contentType("application/json")
//            .accept(ContentType.JSON)
//            .header("x-access-token", securityToken)
//            .when()
//            .get("/api/info/admin").then()
//            .statusCode(200)
//            .body("msg", equalTo("Hello to (admin) User: admin"));
//  }
//  @Test
//  public void testRestForUser() {
//    login("user", "test");
//    given()
//            .contentType("application/json")
//            .header("x-access-token", securityToken)
//            .when()
//            .get("/api/info/user").then()
//            .statusCode(200)
//            .body("msg", equalTo("Hello to User: user"));
//  }
//  @Test
//  public void testRestForMultiRole1() {
//    login("user_admin", "test");
//    given()
//            .contentType("application/json")
//            .accept(ContentType.JSON)
//            .header("x-access-token", securityToken)
//            .when()
//            .get("/api/info/admin").then()
//            .statusCode(200)
//            .body("msg", equalTo("Hello to (admin) User: user_admin"));
//  }
//
//  @Test
//  public void testRestForMultiRole2() {
//    login("user_admin", "test");
//    given()
//            .contentType("application/json")
//            .header("x-access-token", securityToken)
//            .when()
//            .get("/api/info/user").then()
//            .statusCode(200)
//            .body("msg", equalTo("Hello to User: user_admin"));
//  }
//
//  @Test
//  public void userNotAuthenticated() {
//    logOut();
//    given()
//            .contentType("application/json")
//            .when()
//            .get("/api/info/user").then()
//            .statusCode(403);
//  }
//
//  @Test
//  public void adminNotAuthenticated() {
//    logOut();
//    given()
//            .contentType("application/json")
//            .when()
//            .get("/api/info/user").then()
//            .statusCode(403);
//  }
}
