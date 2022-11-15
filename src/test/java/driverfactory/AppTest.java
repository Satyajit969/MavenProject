package driverfactory;

import org.testng.annotations.Test;

public class AppTest {
	@Test
	public void kickstart() throws Throwable
	{
		Driverscript ds = new Driverscript();
		ds.startTest();
	}

	

	}


