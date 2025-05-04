package api.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfigFilesData {

	public static Properties readConfigFilesData(String filePath)
	{
		Properties properties = new Properties();
		
		try
		{
			FileInputStream fis = new FileInputStream(filePath);
			properties.load(fis);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return properties;
		
	}
	
}
