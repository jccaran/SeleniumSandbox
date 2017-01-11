package newproject;

import java.awt.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.By; 
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MyClass {
    
	public static void main(String[] args) {
        // declaration and instantiation of objects/variables
		System.setProperty("webdriver.chrome.driver", "C:\\Git\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
        String baseUrl = "http://www.google.com"; //These can be parameterized
        String expectedTitle = "Google"; //These can be parameterized
        
        //String baseUrl = "https://www.google.com/?gws_rd=ssl#q=hacky+sack&tbm=shop&spd=2922824237855525892"; //These can be parameterized
      

        if (openBrowser(driver, baseUrl, expectedTitle)) {
            //Find Google Search and submit query
        	
        	WebElement element = driver.findElement(By.name("q"));
            element.sendKeys("hacky sack\n");
            element.submit();
            
            // wait until the google page shows the result
            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
            
            //Click the Shopping link
            driver.findElement(By.linkText("Shopping")).click();
            
            //Having trouble with this for some inexplicable reason. 
            By mySelector = 
            		//By.xpath("//*[@class=\"sh-pr__product-results\"]/div"); //No results
            		//By.xpath("//*[@id=\"rso\"]/div[1]/div/div[4]");  //No results
            		By.xpath("//*[@class=\"sh-pr__product-results\"]/div[4]");  //No results
            		//By.xpath("//*[@class=\"sh-pr__product-results .psgi\"]");  //No results
            		//By.cssSelector("#rso > div.sh-sr__shop-result-group._G2d > div > div:nth-child(4)");  //No results
            		

            
            java.util.List<WebElement> myElements = driver.findElements(mySelector);
            System.out.println("Size of List: "+myElements.size());
            for(WebElement e : myElements) 
            {        
                System.out.print("Text within the Anchor tab"+e.getText()+"\t");
                System.out.println("Anchor: "+e.getAttribute("href"));
            }

        	
        	//#TODO: Select fourth element
            
            //#TODO: Click "Save to Short List"
            
            //#TODO: Add a Note, saying "Please buy me"
                

        }
        
        //close Chrome
        driver.close();
       
        // exit the program explicitly
        System.exit(0);
    }
	
	private static Boolean openBrowser (WebDriver driver, String url, String title) {
		driver.get(url);
		String actualTitle = driver.getTitle();
        if (actualTitle.contentEquals(title)){
            System.out.println("Successfully logged into " + actualTitle);
            return true;
        } else {
            System.out.println("Wrong Site. Expected " + title + ". Logged into " + actualTitle);
            return false;
        }
	}
	
}
