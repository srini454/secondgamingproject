package testCases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TC1_VideoGameAPI {

	@Test(priority = 1)
	public void test_getAllVideoDB() {

		given()

				.when()

				.get("http://localhost:8080/app/videogames")

				.then().statusCode(200);

	}

	@Test(priority = 2)
	public void test_addNewVideoGame() {

		HashMap data = new HashMap();

		data.put("id", "101");
		data.put("name", "Drakulla");
		data.put("releaseDate", "2020-07-10T14:49:04.237Z");
		data.put("reviewScore", "91");
		data.put("category", "Strategy");
		data.put("rating", "Universal");

		Response res =

				given().contentType("application/json").body(data).when().post("http://localhost:8080/app/videogames")
						.then().statusCode(200).log().body().extract().response();

		String Jsonstring = res.asString();

		Assert.assertEquals(Jsonstring.contains("Record Added Successfully"), true);

	}

	@Test(priority = 3)

	public void test_getNewVideo() {
		given()

				.when().get("http://localhost:8080/app/videogames/101")

				.then().statusCode(200).body("videoGame.id", equalTo("101")).body("videoGame.name", equalTo("Drakulla"))
				.log().body();

	}

	@Test(priority = 4)

	public void test_modityVideoGame() {

		HashMap data = new HashMap();

		data.put("id", "101");
		data.put("name", "Gorilla");
		data.put("releaseDate", "2020-07-10T14:49:04.237Z");
		data.put("reviewScore", "95");
		data.put("category", "Strategy");
		data.put("rating", "Universal");

		given().contentType("application/json").body(data).when().put("http://localhost:8080/app/videogames/101").then()
				.statusCode(200).log().body();

	}

	@Test(priority = 5)
	public void test_deleteVideoGame() throws InterruptedException {

		Response res =

				given()

						.when()

						.delete("http://localhost:8080/app/videogames/101")

						.then().statusCode(200).log().body().extract().response();

		Thread.sleep(3000);

		String JsonString = res.asString();

		Assert.assertEquals(JsonString.contains("Record Deleted Successfully"), true);

	}

}
