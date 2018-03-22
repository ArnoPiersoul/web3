package view;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test2 {
	private static final String url = "";
	private WebDriver driver;
	
	@Before
	public void test() {
		driver = new ChromeDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arno\\OneDrive\\School\\webontwikkeling 2\\Labo's\\Labo 3\\nodigeBestanden\\chromedriver.exe");
		
	}
	@After
	public void clean(){
		driver.quit();
	}
	
	
	
}
