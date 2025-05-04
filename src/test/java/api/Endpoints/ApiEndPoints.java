package api.Endpoints;

import static io.restassured.RestAssured.given;

import api.Payload.UserPOJO;
import api.Utilities.ReadEachConfigFilesData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ApiEndPoints {
	
	Response response;
	ReadEachConfigFilesData readEachConfigFilesData = new ReadEachConfigFilesData();
	
	
	public Response createAPI(UserPOJO payload)
	{
		return given()
			.header("Authorization","Bearer "+readEachConfigFilesData.readApplicationProperty().getProperty("application.bearerToken"))
			.contentType(ContentType.JSON)
			.body(payload)
		
		.when()
			.post(readEachConfigFilesData.readApplicationProperty().getProperty("application.postURL"));
	}
	
	public Response getAPI(int id)
	{
		return given()
			.header("Authorization","Bearer "+readEachConfigFilesData.readApplicationProperty().getProperty("application.bearerToken"))
			.contentType(ContentType.JSON)
			.pathParams("id",id)
			
		.when()
				.get(readEachConfigFilesData.readApplicationProperty().getProperty("application.getURL"));
	}
	
	public Response updateAPI(int id,UserPOJO payload)
	{
		return given()
			.header("Authorization","Bearer "+readEachConfigFilesData.readApplicationProperty().getProperty("application.bearerToken"))
			.contentType(ContentType.JSON)
			.pathParam("id", id)
			.body(payload)
			
		.when()
				.put(readEachConfigFilesData.readApplicationProperty().getProperty("application.updateURL"));
	}
	
	public Response deleteAPI(int id)
	{
		return given()
			.header("Authorization","Bearer "+readEachConfigFilesData.readApplicationProperty().getProperty("application.bearerToken"))
			.contentType(ContentType.JSON)
			.pathParam("id", id)
			
		.when()
				.delete(readEachConfigFilesData.readApplicationProperty().getProperty("application.deleteURL"));
	}

}
