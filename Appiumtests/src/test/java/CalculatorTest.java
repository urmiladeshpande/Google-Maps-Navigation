import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class CalculatorTest {

	public static AppiumDriver apDriver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OpenCalc();
	
	}
	
	public static void OpenCalc()
	{

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("udid", "efd60ff8");
		capabilities.setCapability("platformVersion", "11"); 
		capabilities.setCapability("deviceName","Sangram Patil");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage","com.google.android.apps.maps");
		capabilities.setCapability("appActivity","com.google.android.maps.MapsActivity");
		
		
		
		try {
			
			List<String>Coordinates =ReadKml();

			for (String string : Coordinates) {
				System.out.println(string);
			}		
			
 			AndroidDriver driver ;
			 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);
			 
			 float Lat,Lng,Alt;
			 
			String[] Parts= Coordinates.get(0).split(",");
			 
			
			System.out.println("Location: "+Double.parseDouble(Parts[1])+","+ Double.parseDouble(Parts[0]));
			
			 driver.setLocation(new Location(Double.parseDouble(Parts[1]), Double.parseDouble(Parts[0]),Double.parseDouble(Parts[2]))); 
			 
			 
			WebElement we= driver.findElement(By.xpath("//android.widget.EditText[@content-desc=\"Search here\"]/android.widget.TextView"));
			
			Thread.sleep(1000);
			we.click();
			Thread.sleep(1000);
			driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_edit_text")).sendKeys("Orlando");
			
			driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
			//driver.findElement(By.id("com.google.android.apps.maps:id/search_omnibox_edit_text")).sendKeys(Keys.ENTER);
				//driver.launchApp();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Start\"]")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("android:id/button1")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//android.view.View[@content-desc=\"Dismiss\"]")).click();
						
			Thread.sleep(1000);
			
			for (int i=0;i<Coordinates.size();i++) {
				Parts= Coordinates.get(i).split(",");
				 
				 driver.setLocation(new Location(Double.parseDouble(Parts[1]), Double.parseDouble(Parts[0]),Double.parseDouble(Parts[2]))); 
				 
				Thread.sleep(500);
			}		
			

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	
	public static List<String> ReadKml()
	{
		 Path fileName = Paths.get("C:\\Users\\sangr\\Downloads\\Directions.kml");
		 List<String> str=null;
     // Now calling Files.readString() method to
     // read the file
	     try {
			str = Files.readAllLines(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	     
	     return str;		
	}
	
	
	
//	public static void OpenCalc()
//	{
//		
//
//String kobitonServerUrl = "https://gyanadeeps:15a9ea3f-38fb-450c-9706-08a72ed71950@api.kobiton.com/wd/hub";
//
//DesiredCapabilities capabilities = new DesiredCapabilities();
//capabilities.setCapability("sessionName", "Automation test session");
//capabilities.setCapability("sessionDescription", "");
//capabilities.setCapability("deviceOrientation", "portrait");
//capabilities.setCapability("noReset", true);
//capabilities.setCapability("fullReset", false);
//capabilities.setCapability("captureScreenshots", true);
//capabilities.setCapability("useConfiguration", "");
//capabilities.setCapability("autoWebview", true);
//capabilities.setCapability("browserName", "chrome");
//// The given team is used for finding devices and the created session will be visible for all members within the team.
//capabilities.setCapability("groupId", 12170); // Group: AutomationDocs
//capabilities.setCapability("deviceGroup", "ORGANIZATION");
//// For deviceName, platformVersion Kobiton supports wildcard
//// character *, with 3 formats: *text, text* and *text*
//// If there is no *, Kobiton will match the exact text provided
//capabilities.setCapability("deviceName", "Galaxy S9+");
//// The tag is used for finding devices and the user can input only one tag. 
//// For example, the data value will be inputted: tagName="TagName1"
//capabilities.setCapability("tagName", "");
//capabilities.setCapability("platformVersion", "8.0.0");
//capabilities.setCapability("platformName", "Android"); 
//		
//
//		/*DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability("udid", "efd60ff8");
//		capabilities.setCapability("platformVersion", "11"); 
//		capabilities.setCapability("deviceName","Sangram Patil");
//		capabilities.setCapability("platformName","Android");
//		capabilities.setCapability("appPackage","com.miui.calculator");
//		capabilities.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity");*/
//		
//		URL url=null;
//		try {
//			url = new URL(kobitonServerUrl);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// driver = new RemoteWebDriver(new URL("https://127.0.0.1:4723/wd/hub"), capabilities);
//		apDriver= new AppiumDriver(url,capabilities);
//		SessionId kobitonSessionId = apDriver.getSessionId();// getSessionDetails().get("kobitonSessionId").toString(); 
//		System.out.println("kobitonSessionId: " + kobitonSessionId);
//		
//	}
	

}
