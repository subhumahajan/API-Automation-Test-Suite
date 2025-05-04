package api.Utilities;

import java.util.Properties;

public class ReadEachConfigFilesData extends ReadConfigFilesData{
	
	public Properties readApplicationProperty()
	{
		return ReadConfigFilesData.readConfigFilesData(System.getProperty("user.dir")+"\\src\\test\\resources\\api.configFiles\\application.properties");
	}
	
	public Properties readCreateUserDataProperty()
	{
		return ReadConfigFilesData.readConfigFilesData(System.getProperty("user.dir")+"\\src\\test\\resources\\api.configFiles\\createUserData.properties");
	}
	
	public Properties readUpdateUserDataProperty()
	{
		return ReadConfigFilesData.readConfigFilesData(System.getProperty("user.dir")+"\\src\\test\\resources\\api.configFiles\\updateUserData.properties");
	}

	
//	public static void main(String [] args)
//	{
//		System.out.println(readApplicationProperty().getProperty("application.bearerToken"));
//		System.out.println(readCreateUserDataProperty().getProperty("createUserData.name"));
//		System.out.println(readUpdateUserDataProperty().getProperty("updateUserData.name"));
//	}
	
}
