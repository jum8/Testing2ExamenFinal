package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

     private By inputFirstName = By.id("customer.firstName");
     private By inputLastName = By.id("customer.lastName");
     private By inputAddress = By.id("customer.address.street");
     private By inputCity = By.id("customer.address.city");
     private By inputState = By.id("customer.address.state");
     private By inputZipCode = By.id("customer.address.zipCode");
     private By inputPhone = By.id("customer.phoneNumber");
     private By inputSSN = By.id("customer.ssn");
     private By inputUsername = By.id("customer.username");
     private By inputPassword = By.id("customer.password");
     private By inputConfirmPassword = By.id("repeatedPassword");
     private By botonRegister = By.cssSelector("input[value='Register'][type='submit']");
     private By textoBienvenida = By.cssSelector("h1.title");

     public RegisterPage(WebDriver driver, WebDriverWait wait) {
          super(driver, null);
     }




     public void escribirNombre(String nombre) throws InterruptedException {
          this.sendText(nombre, inputFirstName);
     }

     public void escribirApellido(String apellido) throws InterruptedException {
          this.sendText(apellido, inputLastName);
     }

     public void escribirDireccion(String direccion) throws InterruptedException {
          this.sendText(direccion, inputAddress);
     }

     public void escribirCiudad(String ciudad) throws InterruptedException {
          this.sendText(ciudad, inputCity);
     }

     public void escribirEstado(String estado) throws InterruptedException {
          this.sendText(estado, inputState);
     }

     public void escribirCodigoPostal(String codigoPostal) throws InterruptedException {
          this.sendText(codigoPostal, inputZipCode);
     }

     public void escribirTelefono(String tel) throws InterruptedException {
          this.sendText(tel, inputPhone);
     }

     public void escribirSSN(String ssn) throws InterruptedException {
          this.sendText(ssn, inputSSN);
     }

     public void escribirUsuario(String email) throws InterruptedException {
          this.sendText(email, inputUsername);
     }
     public void escribirContrasena(String contrasena) throws InterruptedException {
          this.sendText(contrasena, inputPassword);
     }

     public void repetirContrasena(String contrasena) throws InterruptedException {
          this.sendText(contrasena, inputConfirmPassword);
     }

     public void clickBotonRegistro() throws InterruptedException {
          this.clickear(botonRegister);
     }


     public String obtenerTextoBienvenida() throws InterruptedException {
          String res = this.getText(textoBienvenida);
          System.out.println("Feedback: " + res);
          return res;
     }


}