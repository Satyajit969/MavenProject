package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	
		public static String getValueForKey(String key)throws Throwable
		{
			Properties config = new Properties();
			config.load(new FileInputStream("D:\\satya\\selenium work\\Maven_Project\\propertyfile\\Enviornment.properties"));
			return config.getProperty(key);
		}
	}
	


