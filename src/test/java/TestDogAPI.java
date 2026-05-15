import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class TestDogAPI{

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://dog.ceo/api";

    }

    @Test
    public void testRetornarAlistaCachorros(){
        String getAllDogsListURL = "/breeds/list/all";
        Response response =  RestAssured.given()
                .contentType(ContentType.JSON)
                .get(getAllDogsListURL).andReturn();

        response.then().assertThat().statusCode(200)
                .body("message", notNullValue());
    }

    @Test
    public void testRetornarImagesPorBuscaRaca(){
        String raca = "labrador";
        String getAllDogsListURL = "/breed/{raca}/images/random".replace("{raca}",raca);
        Response response =  RestAssured.given()
                .contentType(ContentType.JSON)
                .get(getAllDogsListURL).andReturn();

        response.then().assertThat().statusCode(200)
                .body("message", notNullValue() )
                .body("status", equalTo("success"));
    }

    @Test
    public void testRetornarImagemCachorroEmRacaAleatorio(){
        String getAllDogsListURL = "/breeds/image/random";
        Response response =  RestAssured.given()
                .contentType(ContentType.JSON)
                .get(getAllDogsListURL).andReturn();

        response.then().assertThat().statusCode(200)
                .body("message", notNullValue() )
                .body("status", equalTo("success"));
    }

    @Test
    public void testTipoRacaNaoEncontrado(){
        String raca = "xoxo";
        String getAllDogsListURL = "/breed/{raca}/images/random".replace("{raca}",raca);
        Response response =  RestAssured.given()
                .contentType(ContentType.JSON)
                .get(getAllDogsListURL).andReturn();

        response.then().assertThat().statusCode(404)
                .body("message", equalTo("Breed not found (main breed does not exist)"))
                .body("status", equalTo("error"))
                .body("code", equalTo(404));
    }
}
