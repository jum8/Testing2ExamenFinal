package tests;

import pages.HomePage;
import pages.RegisterPage;
import reports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Data;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRegister {
    public WebDriver driver;
    public WebDriverWait wait;


    static ExtentSparkReporter info  = new ExtentSparkReporter("target/registerTestsReport.html");
    static ExtentReports extent;

    @BeforeAll
    public static void reportSetup() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }


    @Test
    @Tag("FrontRegister")
    public void registroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro exitoso");
        test.log(Status.INFO, "Comienza el test");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        HomePage homePage = new HomePage(driver, wait);

        homePage.clickLinkRegistro();
        test.log(Status.PASS, "Ingreso en el formulario de registro");
        registerPage.escribirNombre("Max");
        registerPage.escribirApellido("Power");
        registerPage.escribirDireccion("Calle falsa 123");
        registerPage.escribirCiudad("Springfield");
        registerPage.escribirEstado("????");
        registerPage.escribirCodigoPostal("123");
        registerPage.escribirTelefono("(875) 327-0886");
        registerPage.escribirSSN("123456789");
        registerPage.escribirUsuario(Data.USERNAME);
        registerPage.escribirContrasena(Data.PASSWORD);
        registerPage.repetirContrasena(Data.PASSWORD);

        test.log(Status.PASS, "Se ingresaron todos los datos de registro");

        registerPage.clickBotonRegistro();

        String textoBienvenida = registerPage.obtenerTextoBienvenida();
        assertEquals(textoBienvenida, "Welcome " + Data.USERNAME);
        test.log(Status.PASS, "Validación de texto de bienvenida");
    }



    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void createReport() {
        extent.flush();
    }
}
