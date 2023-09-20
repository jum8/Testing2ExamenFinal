package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

     private By inputUsername = By.cssSelector("input[name='username']");
     private By inputPassword = By.cssSelector("input[name='password']");
     private By botonLogin = By.cssSelector("input[value='Log In'][type='submit']");
     private By linkRegister = By.linkText("Register");

     public HomePage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }


     public void escribirUsuario(String username) throws InterruptedException {
          this.sendText(username, inputUsername);
     }
     public void escribirContrasena(String contrasena) throws InterruptedException {
          this.sendText(contrasena, inputPassword);
     }

     public void clickBotonLogin() {
          this.clickear(botonLogin);
     }
     public void clickLinkRegistro() throws InterruptedException {
          this.clickear(linkRegister);
          Thread.sleep(1000);
     }


}