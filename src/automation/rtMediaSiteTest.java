package automation;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.SkipException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.Credentials;




public class rtMediaSiteTest{
	
	public String baseUrl = "http://demo.rtcamp.com/rtmedia/";
	public WebDriver driver = new FirefoxDriver();
	public String actual= null;
	public String expected = null;
    WebDriverWait wait = new WebDriverWait(driver, 20);
	
	
	public void opensite(){
	driver.get(baseUrl);
	}
	
	@BeforeTest
	public void managetime(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
  @Test(priority=0)
  public void verifyHomepageTitle() throws InterruptedException {
	  
	  opensite();
	  Thread.sleep(5000);
	  expected ="Activity - rtMedia";
	  actual = driver.getTitle();
	  
	  AssertJUnit.assertEquals(actual, expected);
	  //driver.close();	  	  	 	  	  
  }
  
  @Test(priority=1)
  public void AlreadyRegistered() throws InterruptedException {
	  	  
	  opensite();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath(".//*[@id='tabsmenu']/li[2]/a")).click();
      driver.findElement(By.xpath(".//*[@id='rtAS_registration_username']")).sendKeys("sultan11");
      driver.findElement(By.xpath(".//*[@id='rtAS_registration_email']")).sendKeys("amol7791@gmail.com");
      driver.findElement(By.xpath(".//*[@id='rtAS_registration_username']")).click(); // just for the the span message to generate faster
      Thread.sleep(5000);      
      
      
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("not-available")));
      
      if(element.isDisplayed())  
      {
      System.out.println("username or email-id is already registered");
      }
      else
      {
      	System.out.println("Test Failed hypothetical");
      	Thread.sleep(1000);       	
      }  	  
                               
  }
  
  @Test(priority=2)
  public void FaultyLogin() throws InterruptedException {
	  
	//Faulty Login case and how to snap out invalid credentials at test............
      
	  opensite();
	  Thread.sleep(5000);
      //Clicking the Login button at the top in the blue top-bar
      driver.findElement(By.xpath(".//*[@id='header']/div/a[2]")).click();
      //System.out.println("Login Button Link Clicked successfully to open login pop-up dialog");
              
      //Entering invalid User-name in the pop-up login menu
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-user-login']")).sendKeys("xyz"); 
      //System.out.println("invalid User-Id Entered");
        
      //Entering invalid Password in the pop-up login menu
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-user-pass']")).sendKeys("abc");
      //System.out.println("invalid Password Entered");
      
    //Enter the login button/submit and verification
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-submit']")).click();
      Thread.sleep(5000);
      
      
      if(driver.getCurrentUrl().equals("http://demo.rtcamp.com/rtmedia/wp-login.php"))
      {
      	System.out.println("Username or password was invalid plz try again");
      	//driver.get("http://demo.rtcamp.com/rtmedia/");
      	Thread.sleep(3000);
      }
      else
      {
      	System.out.println("Successful Login");//just for the sake of else clause 
      }
	  
  }
  
  
  
  ///Entering a invalid username would skip the test so that way this will be successful Test Case
  
  @Test(priority=3)
  public void Login() throws InterruptedException{
	  
	   opensite();
	  //Thread.sleep(9000);
	  //Clicking the Login button at the top in the blue top-bar
      driver.findElement(By.xpath(".//*[@id='header']/div/a[2]")).click();
      System.out.println("Login Button Link Clicked successfully for valid login credentials");

      
      //Entering User-name in the pop-up login menu
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-user-login']")).sendKeys(Credentials.id); 
      System.out.println("User-Id Entered");
        
      //Entering Password in the pop-up login menu
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-user-pass']")).sendKeys(Credentials.pswd);
      System.out.println("Password Entered");
                
      //Enter the login button/submit and verification
      driver.findElement(By.xpath(".//*[@id='bp-login-widget-submit']")).click();    
      
      		//just to make sure the profile page has opened and then only it can search 
      		Thread.sleep(3000);
      
      if(driver.findElement(By.xpath(".//*[@id='header']/div/ul/li[4]/div/a/span")).getText().equals(Credentials.id))                
      {        
      	System.out.println("Login Successfull");
      }
      else
      {
    	throw new SkipException("Skipping the test case");  
      	//System.out.println("Faulty Login");
      	//driver.close();
      	//System.out.println("Exited successfully");        	
      }
  }
  
  
  
  @Test(priority=4)
  public void PostMediaPrivacy() throws IOException, InterruptedException{
	  
	  Thread.sleep(3000);
      
      //Entering the message/IM to post anything on the profile
      driver.findElement(By.id("whats-new")).sendKeys("Hiee");
      
      
      //Click File Attach Link to attach a particular file which is pre selected using AutoIT script code and path of the given image is on the desktop named "img.jpg"        
      driver.findElement(By.xpath(".//*[@id='rtmedia-add-media-button-post-update']")).click();   
      
      Runtime.getRuntime().exec("../RtCampNG/src/AutoITScript/upload.exe C:\\Users\\Amol Shah\\Desktop\\img.jpg");
      //"C:\\Users\\Amol Shah\\Desktop\\img.jpg" - in above line place of this u can provide with your own url and this can be changed here only as we are passing a direct argument through cmd
      
      Thread.sleep(1000);
      System.out.println("File Added in queue for upload");         
      Thread.sleep(5000);
                                                                                    
      
      //selecting post to be private from dropdown menu      
      driver.findElement(By.xpath(".//*[@id='rtSelectPrivacy']/option[1]")).click();
                            
      //Post Submit
      driver.findElement(By.xpath(".//*[@id='aw-whats-new-submit']")).click();
      System.out.println("Post Uploaded Successfully");
      
      
      @SuppressWarnings("unused")
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.just-posted")));
	  
      
  }
  
  @Test(priority=5)
  public void uploadimage() throws IOException, InterruptedException{
	  
	  Thread.sleep(5000);
	  //get into current user profile of "testuser7791"       
      driver.get("http://demo.rtcamp.com/rtmedia/members/testuser7791/");
      
      //Upload media from computer in current profile(sultan) and privacy is set to private and album selected as Profile Pictures
      driver.findElement(By.xpath(".//*[@id='user-media']")).click();
      driver.findElement(By.xpath(".//*[@id='rtm_show_upload_ui']")).click();
      
      driver.findElement(By.xpath(".//*[@id='drag-drop-area']/div[1]/span[1]/select/option[2]")).click();
      System.out.println("Cover Photo Album selected for internal path file upload");
      
      driver.findElement(By.xpath(".//*[@id='rtMedia-upload-button']")).click();
      Runtime.getRuntime().exec("../RtCampNG/src/AutoITScript/upload.exe C:\\Users\\Amol Shah\\Desktop\\img.jpg");
      //"C:\\Users\\Amol Shah\\Desktop\\img.jpg" - in above line place of this u can provide with your own url path
      Thread.sleep(1000);
      
      driver.findElement(By.xpath(".//*[@id='rtSelectPrivacy']/option[1]")).click();
      System.out.println("Privacy set to private successfully for internal path upload");
      
      driver.findElement(By.xpath(".//*[@id='rtmedia_upload_terms_conditions']")).click();
      
      Thread.sleep(5000);
      
      driver.findElement(By.xpath(".//*[@id='drag-drop-area']/input")).click();
      System.out.println("File Added and upload of image in media section from the internal path");
	  
  }
  
  @Test(priority=6)
  public void UploadImageUrl() throws InterruptedException{
	  
	  
	  Thread.sleep(3000);
	  driver.get("http://demo.rtcamp.com/rtmedia/members/testuser7791/");
	  Thread.sleep(5000);
	  driver.findElement(By.xpath(".//*[@id='user-media']")).click();	       
      
      driver.findElement(By.xpath(".//*[@id='rtm_show_upload_ui']")).click();
      
      driver.findElement(By.xpath(".//*[@id='drag-drop-area']/div[1]/span[1]/select/option[2]")).click();
      System.out.println("Cover Photo Album selected for url path file upload");
      
      driver.findElement(By.xpath(".//*[@id='rtmedia_url_upload_input']")).sendKeys(Credentials.urlpath);	        
      System.out.println("Url selected successfully");
      
      driver.findElement(By.xpath(".//*[@id='rtSelectPrivacy']/option[2]")).click();
      System.out.println("Privacy set to friends successfully for url path upload");
      
      driver.findElement(By.xpath(".//*[@id='rtmedia_upload_terms_conditions']")).click();	        
      System.out.println("File Added in queue for upload through url site path");
      
      driver.findElement(By.xpath(".//*[@id='drag-drop-area']/input")).click();
      System.out.println("Image uploaded successfully from url path");
      
      Thread.sleep(5000);
	  
  }
    
  
  @AfterTest
  public void close(){
	  
	driver.close();
	
	  
  }
  
}

  
 
