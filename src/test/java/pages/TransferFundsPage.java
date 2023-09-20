package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransferFundsPage extends BasePage {

     private By titulo = By.cssSelector("h1.title");
     private By inputAmount = By.id("amount");

     private By desplegableFromAccount = By.id("fromAccountId");
     private By desplegableToAccount = By.id("toAccountId");

     private By botonTransfer = By.cssSelector("input[value='Transfer'][type='submit']");


     public TransferFundsPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }


     public String obtenerTextoTitulo() throws InterruptedException {
          String res = this.getText(titulo);
          System.out.println("Texto de mensaje: " + res);
          return res;
     }


     public void escribirImporte(String importe) throws InterruptedException {
          this.sendText(importe, inputAmount);
     }

     public void seleccionarCuentaDe() throws InterruptedException {
          this.selectByIndex(desplegableFromAccount, 0);
     }

     public void seleccionarCuentaA() {
          this.selectByIndex(desplegableToAccount, 0);
     }


     public void clickBotonTransfer() throws InterruptedException {
          this.clickear(botonTransfer);
          Thread.sleep(500);
     }



}