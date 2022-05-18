
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;


public class TestPotato {

    @Test


    public  void TestTomato() throws IOException {


        JSONObject jsonPotato = new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/potato.json"))));

        jsonPotato.put("name","Tomato");
        jsonPotato.put("job","Eat maket");



       Response response = given()
               .contentType(ContentType.JSON)
               .baseUri("https://reqres.in/")
               .body(jsonPotato.toString())
               .when()
               .post("api/users")
               .then()
               .assertThat()
               .statusCode(201)
               .body("name", Matchers.equalTo("Tomato"))
               .body("job", Matchers.equalTo("Eat maket"))
               .body("id", Matchers.notNullValue())
               .body("createdAt", Matchers.matchesRegex("^((?:19|20)\\d\\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])T(0[1-9]|1[0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])\\.([0-9][0-9][0-9])Z"))
               .extract().response();



            response.prettyPrint();











    }

}
