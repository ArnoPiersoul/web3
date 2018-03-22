package view;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class StudentZoekTest {

	// verander de <projectnaam> naar de naam van jouw project
	private static final String url = "http://localhost:8080/r0678779_Labo5Opdracht2/";
	private WebDriver driver;
	
	@Before
	public void setUp () {
//		 Voor Windows (vergeet "\" niet te escapen met een tweede "\")
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\...\\chromedriver.exe");
//		 Voor mac: 
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Arno\\OneDrive\\School\\webontwikkeling 2\\Labo's\\Labo 3\\nodigeBestanden\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void test_StudentForm_alles_invullen_gaat_naar_overzicht_en_toont_nieuwe_student_in_tabel () {
		driver.get(url+"studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.sendKeys("Kemme");
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.sendKeys("Mieke");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.sendKeys("18");

		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Overzicht studenten", driver.findElement(By.tagName("h1")).getText());
		assertNotNull(driver.findElement(By.id("Kemme")));
	}

	@Test
	public void test_StudentForm_helemaal_leeg_laten_gaat_terug_naar_formulier_En_Toont_Errors () {
		driver.get(url+"studentForm.jsp");
		
		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De naam mag niet leeg zijn", allId));
		assertTrue(allIdContains("De voornaam mag niet leeg zijn", allId));
		assertTrue(allIdContains("De leeftijd moet groter dan 0 zijn", allId));
		assertTrue(allIdContains("De studierichting mag niet leeg zijn", allId));
	}
	
	@Test
	public void test_StudentForm_naam_leeg_laten_gaat_terug_naar_formulier_En_Toont_Error () {
		driver.get(url+"studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.clear();
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.sendKeys("Elke");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.sendKeys("18");

		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De naam mag niet leeg zijn", allId));
	}

	@Test
	public void test_StudentForm_naam_en_leeftijd_leeg_laten_gaat_terug_naar_formulier_En_Toont_errors () {
		driver.get(url+"studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.clear();
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.clear();
		voornaamField.sendKeys("Elke");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.clear();
		
		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.clear();
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De naam mag niet leeg zijn", allId));
		assertTrue(allIdContains("De leeftijd moet groter dan 0 zijn", allId));
	}
	@Test
	public void test_StudentForm_Ongeldige_Invoer_Naam_En_Voornaam_gaat_terug_naar_formulier_en_toont_errors(){
		driver.get(url + "studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.clear();
		naamField.sendKeys("123£$%");
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.clear();
		voornaamField.sendKeys("=-)($");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.clear();
		leeftijdField.sendKeys("25");
		
		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.clear();
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De naam mag enkel letters bevatten", allId));
		assertTrue(allIdContains("De voornaam mag enkel letters bevatten", allId));
	}
	@Test
	public void test_StudentForm_Ongeldige_Invoer_Leeftijd_gaat_terug_naar_formulier_en_toont_error (){
		driver.get(url + "studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.clear();
		naamField.sendKeys("Steegmans");
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.clear();
		voornaamField.sendKeys("Elke");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.clear();
		leeftijdField.sendKeys("$%^");
		
		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.clear();
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De leeftijd mag enkel cijfers bevatten", allId));
	}
	@Test
	public void test_StudentForm_Leeftijd_kleiner_dan_0_gaat_terug_naar_formulier_en_toont_error (){
		driver.get(url + "studentForm.jsp");
		
		WebElement naamField = driver.findElement(By.id("naam"));
		naamField.clear();
		naamField.sendKeys("Steegmans");
		
		WebElement voornaamField = driver.findElement(By.id("voornaam"));
		voornaamField.clear();
		voornaamField.sendKeys("Elke");
		
		WebElement leeftijdField = driver.findElement(By.id("leeftijd"));
		leeftijdField.clear();
		leeftijdField.sendKeys("-15");
		
		WebElement studierichtingField = driver.findElement(By.id("studierichting"));
		studierichtingField.clear();
		studierichtingField.sendKeys("TI");

		WebElement button = driver.findElement(By.id("bewaar"));
		button.click();
		
		assertEquals("Voeg student toe", driver.getTitle());
		assertEquals("New Student", driver.findElement(By.tagName("h1")).getText());
		ArrayList<WebElement> allId = new ArrayList<WebElement>();
		allId = (ArrayList<WebElement>) driver.findElements(By.cssSelector("li"));
		assertTrue(allIdContains("De leeftijd mag niet leeg zijn", allId));
	}
	//Hulpfuncties
	
	public boolean allIdContains(String error, ArrayList<WebElement> allId){
		for (WebElement id : allId){
			if (error.equals(id.getText())){
				return true;
			}
		}
		return false;
	}
	@After
	public void tearDown () {
		driver.quit();
	}
	
}
