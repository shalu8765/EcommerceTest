package testsaucedemo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ecommerce.DBConnection;

public class SaucedemoTest {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO Auto-generated method stub
           
        
         DBConnection conn;
          
                conn = new DBConnection("jdbc:mysql://localhost:3306/ecommerce","root","root");
                Statement stmt = conn.getConnection().createStatement();
                
                ResultSet rst = stmt.executeQuery("select username,password from Login");
                
        
                
                
               
                rst.next();
                String username1 = rst.getString("username");
                
                String passwd = rst.getString("password");
                
               
       
               
        
        
        //System.setProperty("webdriver.chrome.driver", "chromedriver");
        
        WebDriver driver = new ChromeDriver();
        
        driver.get("https://www.saucedemo.com/");
        
        System.setProperty("webdriver.gecko.driver","geckodriver");
        
        driver.manage().window().maximize();
        
        
        
        WebElement username=driver.findElement(By.id("user-name"));
        
        username.sendKeys(username1);
        
        WebElement pwd = driver.findElement(By.name("password"));
        
        pwd.sendKeys(passwd);
        
        WebElement button=driver.findElement(By.name("login-button"));
        button.click();
        
        WebElement Add_cart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
        Add_cart.click();
        
        WebElement show = driver.findElement(By.xpath("//*[@id='shopping_cart_container']"));
        
        show.click();
        
        
        WebElement item_name = driver.findElement(By.xpath("//div[@class='inventory_item_name']"));
        //String s = item_name.getText();
        //System.out.println(s);
        
        ResultSet epro = stmt.executeQuery("select * from eproduct");
        
        epro.next();
        String Name = epro.getString("name");
        double Price = epro.getDouble("price");
        
        if(item_name.getText().equals(Name)) {
            System.out.println("you choose right one");
        }
        else {
                System.out.println("you choose different one");
        }
        
        WebElement checkout = driver.findElement(By.xpath("//*[@id='checkout']"));
        
        checkout.click();
        
        WebElement FName = driver.findElement(By.xpath("//input[@id='first-name']"));
        FName.sendKeys("Devesh");
        //
        WebElement LName = driver.findElement(By.xpath("//input[@id='last-name']"));
        LName.sendKeys("Upadhyay");
        
        WebElement ZipCode = driver.findElement(By.xpath("//input[@id='postal-code']"));
        ZipCode.sendKeys("202001");
        
        WebElement Continue1 = driver.findElement(By.xpath("//input[@id='continue']"));
        Continue1.click();
        
        WebElement item_price = driver.findElement(By.xpath("//div[@class='inventory_item_price']"));
        System.out.println(item_price.getText());
        
        //if(item_price.getText().equals(Price)) {
            //System.out.println("yes");
       // }
        //else {
            //System.out.println("no");
        //}
        
        WebElement finish = driver.findElement(By.xpath("//button[@class='btn btn_action btn_medium cart_button']"));
        finish.click();
        
        String Last_msg ="THANK YOU FOR YOUR ORDER";
        
        WebElement m = driver.findElement(By.xpath("//h2[@class='complete-header']"));
        String msg = m.getText();
        
        if(Last_msg.equals(msg)) {
            System.out.println("your order is placed");
        }else {
            System.out.println("your order is not placed");
        }
        
      
        
        
        
        
        conn.closeConnection(); 
        
        

}
}
