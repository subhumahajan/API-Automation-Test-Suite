package api.TestCases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import api.Endpoints.ApiEndPoints;
import api.Payload.UserPOJO;
import api.Utilities.ReadEachConfigFilesData;
import io.restassured.response.Response;

public class ApiTestCases {
	
	Response response;
	SoftAssert softassert = new SoftAssert();
	ReadEachConfigFilesData readEachConfigFilesData = new ReadEachConfigFilesData();
	UserPOJO userPojo = new UserPOJO();
	ApiEndPoints apiendpoints = new ApiEndPoints();
    private static ThreadLocal<Integer> threadLocalId = new ThreadLocal<>();

	
	@BeforeClass
	public void setUserData()
	{
		userPojo.setName(readEachConfigFilesData.readCreateUserDataProperty().getProperty("createUserData.name"));
		userPojo.setGender(readEachConfigFilesData.readCreateUserDataProperty().getProperty("createUserData.gender"));
		userPojo.setEmail(readEachConfigFilesData.readCreateUserDataProperty().getProperty("createUserData.email"));
		userPojo.setStatus(readEachConfigFilesData.readCreateUserDataProperty().getProperty("createUserData.status"));
	}
	
	@Test(priority = 1, enabled = true)
	public void create()
	{
		response = apiendpoints.createAPI(userPojo);
		response.then().log().all();
        threadLocalId.set(response.jsonPath().getInt("id"));
		softassert.assertEquals(response.statusCode(), Integer.parseInt(readEachConfigFilesData.readApplicationProperty().getProperty("application.createStatusCode")), "Create Status Code MisMatch");
		softassert.assertAll();
	}
	
	@Test(priority = 2, enabled = true)
	public void get()
	{
		int id = threadLocalId.get();
		response = apiendpoints.getAPI(id);
		response.then().log().all();
		softassert.assertEquals(response.statusCode(), Integer.parseInt(readEachConfigFilesData.readApplicationProperty().getProperty("application.getStatusCode")),"Get Status Code MisMatch");
		softassert.assertAll();
	}
	
	@Test(priority = 3, enabled = true)
	public void update()
	{
		int id = threadLocalId.get();
		userPojo.setName(readEachConfigFilesData.readUpdateUserDataProperty().getProperty("updateUserData.name"));
		userPojo.setEmail(readEachConfigFilesData.readUpdateUserDataProperty().getProperty("updateUserData.email"));
		userPojo.setStatus(readEachConfigFilesData.readUpdateUserDataProperty().getProperty("updateUserData.status"));
		
		response = apiendpoints.updateAPI(id, userPojo);
		response.then().log().all();
		softassert.assertEquals(response.statusCode(), Integer.parseInt(readEachConfigFilesData.readApplicationProperty().getProperty("application.updateStatusCode")), "Update Status Code MisMatch");
		
		response = apiendpoints.getAPI(id);
		response.then().log().all();
		softassert.assertEquals(response.statusCode(), Integer.parseInt(readEachConfigFilesData.readApplicationProperty().getProperty("application.getStatusCode")), "Get Status Code MisMatch");
		softassert.assertAll();
	}
	
	@Test(priority = 4, enabled = true)
	public void delete()
	{
		int id = threadLocalId.get();
		response = apiendpoints.deleteAPI(id);
		response.then().log().all();
		softassert.assertEquals(response.statusCode(), Integer.parseInt(readEachConfigFilesData.readApplicationProperty().getProperty("application.deleteStatusCode")), "Delete Status Code MisMatch");
		softassert.assertAll();
	}

	@AfterClass
	public void cleanUp() {
	    threadLocalId.remove();
	}
	
}
