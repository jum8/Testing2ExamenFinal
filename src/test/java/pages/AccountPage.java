package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage {
     private By linkOpenNewAccount = By.linkText("Open New Account");
     private By linkAccountOverview = By.linkText("Accounts Overview");
     private By linkTransferFunds = By.linkText("Transfer Funds");
     private By textoAlPie = By.cssSelector("#accountTable > tfoot > tr > td");

     private By desplegableAccountType = By.id("type");

     private By botonOpenNewAccount = By.cssSelector("input[value='Open New Account'][type='submit']");

     private By mensaje = By.cssSelector("h1+p");

     public AccountPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }

     public void clickLinkCrearNuevaCuenta() throws InterruptedException {
          this.clickear(linkOpenNewAccount);
     }

     public void clickLinkResumenDeCuentas() throws InterruptedException {
          this.clickear(linkAccountOverview);
//          Thread.sleep(500);
     }
     public void clickLinkTransferenciaDeFondos() throws InterruptedException {
          this.clickear(linkTransferFunds);
          Thread.sleep(1000);
     }

     public void seleccionarCajaAhorro() throws InterruptedException {
          this.selectByText(desplegableAccountType, "SAVINGS");
     }

     public void clickBotonCrearNuevaCuenta() throws InterruptedException {
          Thread.sleep(500);
          this.clickear(botonOpenNewAccount);
     }


     public String obtenerTextoMensaje() throws InterruptedException {
          String res = this.getText(mensaje);
          System.out.println("Texto de mensaje: " + res);
          return res;
     }

     public String obtenerTextoAlPie() throws InterruptedException {
          String res = this.getText(textoAlPie);
          System.out.println("Texto de mensaje: " + res);
          return res;
     }
}