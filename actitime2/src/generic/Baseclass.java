package generic;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.actitime.generic.FileLib;
import com.actitime.pom.Homepage;
import com.actitime.pom.Loginpage;

public class Baseclass {
	public static WebDriver driver;
	@BeforeTest()
	public void openBrowser() {
		Reporter.log("open browser",true);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	@BeforeMethod()
	public void login() throws IOException
	{
		FileLib f=new FileLib();
		String url=f.getPropertyData("url");																																												
		String usn=f.getPropertyData("username");
		String pwd=f.getPropertyData("password");
		driver.get(url);
		Loginpage l=new Loginpage(driver);
		l.setlogin(usn, pwd);
		
	}
	@AfterMethod()
	public void logout()
	{
		Reporter.log("logout",true);
		Homepage h=new Homepage(driver);
		h.setlogout();
		
	}
	@AfterTest()
	public void closeBrowser() {
		Reporter.log("close browser",true);
		driver.quit();
	}

}

}
