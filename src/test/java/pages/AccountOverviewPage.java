package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountOverviewPage extends BasePage {
     private By linkFirstAccount = By.cssSelector("#accountTable > tbody > tr:nth-child(1) > td:nth-child(1) > a");

     private By textoAlPie = By.cssSelector("#accountTable > tfoot > tr > td");

     private By desplegableActivityPeriod = By.id("month");
     private By desplegableTypeOfActivity = By.id("transactionType");

     private By botonGo = By.cssSelector("input[value='Go'][type='submit']");

     private By titulo = By.cssSelector("h1.title");

     public AccountOverviewPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }

     public void clickLinkFirstAccount() throws InterruptedException {
          this.clickear(linkFirstAccount);
     }


     public void clickBotonIr() throws InterruptedException {
          this.clickear(botonGo);
          Thread.sleep(500);
     }


     public String obtenerTitulo() throws InterruptedException {
          String res = this.getText(titulo);
          System.out.println("Texto de titulo: " + res);
          return res;
     }

     public String obtenerTextoAlPie() throws InterruptedException {
          String res = this.getText(textoAlPie);
          System.out.println("Texto al pie: " + res);
          return res;
     }

     public void seleccionarPeriodoActividadTodo() {
          this.selectByText(desplegableActivityPeriod, "All");
     }
     public void seleccionarTipoActividadTodo() {
          this.selectByText(desplegableTypeOfActivity, "All");
     }
}