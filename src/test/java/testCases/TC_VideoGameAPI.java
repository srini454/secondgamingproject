package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC_VideoGameAPI {

	// @Test(priority = 1)
	public void test_getAllVideoGames() {

		given()

				.when().get("http://localhost:8080/app/videogames")

				.then().statusCode(200);

	}

	// @Test(priority = 2)
	public void test_addNewVideoGame() {

		HashMap map = new HashMap();
		map.put("id", "11");
		map.put("name", "Spider-Man");
		map.put("releaseDate", "2020-07-09T12:50:00.409Z");
		map.put("reviewScore", "91");
		map.put("category", "Adventure");
		map.put("rating", "Universal");

		Response res =

				given().contentType("application/json").body(map)

						.when()

						.post("http://localhost:8080/app/videogames")

						.then().statusCode(200).log().body().extract().response();

		String JsonString = res.asString();

		Assert.assertEquals(JsonString.contains("Record Added Successfully"), true);

	}

	@Test(priority = 3)

	public void test_getVideoGame() {

		given()

				.when()

				.get("http://localhost:8080/app/videogames/11")

				.then()

				.statusCode(200).log().body().body("videoGame.id", equalTo("11"))
				.body("videoGame.name", equalTo("Spider-Man"));

	}

	// @Test(priority = 4)
	public void test_editVideoGame() {

		HashMap map = new HashMap();
		map.put("id", "11");
		map.put("name", "Pack-Man");
		map.put("releaseDate", "2020-07-09T12:50:00.409Z");
		map.put("reviewScore", "92");
		map.put("category", "Adventure");
		map.put("rating", "Universal");

		given().contentType("application/json").body(map).when().put("http://localhost:8080/app/videogames/11")

				.then().statusCode(200).log().body();

	}

	// @Test(priority = 5)
	public void test_deleteVideoGame() {

		Response res =

				given()

						.when()

						.delete("http://localhost:8080/app/videogames/11")

						.then().statusCode(200).log().body().extract().response();

		String Jsonstring = res.asString();

		Assert.assertEquals(Jsonstring.contains("Record Deleted Successfully"), true);

	}

}
