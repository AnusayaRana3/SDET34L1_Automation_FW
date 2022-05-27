package com.vtiger.practice;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Random;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;

	import com.mysql.cj.jdbc.Driver;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class CreateProjectrmgyantraTest {

		public static void main(String[] args) throws SQLException {
			Random r=new Random();
			int num=r.nextInt(10000);
			String actual="Sdet34"+num;
			WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("http://localhost:8084/");
			driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
			driver.findElement(By.linkText("Projects")).click();
			driver.findElement(By.className("btn-success")).click();
			driver.findElement(By.name("projectName")).sendKeys(actual);
			driver.findElement(By.name("createdBy")).sendKeys("Anu");
			WebElement dropdown=driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
			Select s=new Select(dropdown);
			s.selectByVisibleText("On Goging");
			driver.findElement(By.cssSelector("[value='Add Project']")).click();
			driver.findElement(By.className("table-hover")).getText();
			Connection connection=null;
			Driver driver1=new Driver();
			DriverManager.registerDriver(driver1);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			Statement statement=connection.createStatement();
			ResultSet result=statement.executeQuery("select * from project");
			while(result.next())
			{
				String s1=(result.getString(4));
				if(s1.equals(actual))
				{
				System.out.println("passed :"+actual);
				}	
			}
			connection.close();
			driver.close();
			}
			}

