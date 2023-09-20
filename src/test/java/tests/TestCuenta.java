package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AccountOverviewPage;
import pages.AccountPage;
import pages.HomePage;
import pages.TransferFundsPage;
import reports.ExtentFactory;
import utils.Data;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCuenta {
    public WebDriver driver;
    public WebDriverWait wait;

    static ExtentSparkReporter info = new ExtentSparkReporter("target/accountTestsReport.html");
    static ExtentReports extent;

    private void login() throws InterruptedException {
        HomePage homePage = new HomePage(driver, wait);

        homePage.escribirUsuario(Data.USERNAME);
        homePage.escribirContrasena(Data.PASSWORD);
        homePage.clickBotonLogin();

    }

    @BeforeAll
    public static void reportSetup() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(2000));
        AccountPage accountPage = new AccountPage(driver, wait);
        accountPage.setup();
        accountPage.url("https://parabank.parasoft.com/parabank/index.htm");
    }



    @Test
    @Order(1)
    @Tag("FrontAccount")
    public void CreacionCajaAhorro() throws InterruptedException {
        ExtentTest test = extent.createTest("Creacion caja de ahorros");
        test.log(Status.INFO, "Comienza el test");
        AccountPage accountPage = new AccountPage(driver, wait);

        this.login();
        accountPage.clickLinkCrearNuevaCuenta();
        accountPage.seleccionarCajaAhorro();
        accountPage.clickBotonCrearNuevaCuenta();
        test.log(Status.INFO, "Click boton crear nueva cuenta");

        String mensaje = accountPage.obtenerTextoMensaje();

        assertEquals(mensaje, "Congratulations, your account is now open.");
        test.log(Status.PASS, "Validación de texto de creacion exitosa de caja de ahorros");
    }

    @Tag("FrontAccount")
    @Order(2)
    @Test
    public void ComprobarTextoAlPieResumenDeCuentas() throws InterruptedException {
        ExtentTest test = extent.createTest("Comprobar texto al pie en resumen de cuentas");
        test.log(Status.INFO, "Comienza el test");
        AccountPage accountPage = new AccountPage(driver, wait);

        this.login();
        accountPage.clickLinkResumenDeCuentas();

        String mensaje = accountPage.obtenerTextoAlPie();

        assertEquals(mensaje, "*Balance includes deposits that may be subject to holds");
        test.log(Status.PASS, "Validación de texto al pie de resumen de cuentas");

    }

    @Tag("FrontAccount")
    @Order(3)
    @Test
    public void ComprobarTituloTranferencias() throws InterruptedException {
        ExtentTest test = extent.createTest("Comprobar titulo tranferencias");
        test.log(Status.INFO, "Comienza el test");
        TransferFundsPage transferPage = new TransferFundsPage(driver, wait);
        AccountPage accountPage = new AccountPage(driver, wait);

        this.login();
        accountPage.clickLinkTransferenciaDeFondos();

        String mensaje = transferPage.obtenerTextoTitulo();

        assertEquals(mensaje, "Transfer Funds");
        test.log(Status.PASS, "Validación de texto al pie de resumen de cuentas");

    }

    @Tag("FrontAccount")
    @Order(4)
    @Test
    public void verActividadDeCuenta() throws InterruptedException {
        ExtentTest test = extent.createTest("Ver actividad de cuenta");
        test.log(Status.INFO, "Comienza el test");
        AccountOverviewPage accountOverviewPage = new AccountOverviewPage(driver, wait);
        AccountPage accountPage = new AccountPage(driver, wait);

        this.login();
        accountPage.clickLinkResumenDeCuentas();
        accountOverviewPage.clickLinkFirstAccount();
        String titulo = accountOverviewPage.obtenerTitulo();
        accountOverviewPage.seleccionarPeriodoActividadTodo();
        accountOverviewPage.seleccionarTipoActividadTodo();
        accountOverviewPage.clickBotonIr();


        assertEquals(titulo, "Account Details");
        test.log(Status.PASS, "Validación de titulo de detalles de cuenta");

    }

    @Tag("FrontAccount")
    @Order(5)
    @Test
    public void tranferenciaExitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("Transferencia exitosa");
        test.log(Status.INFO, "Comienza el test");
        TransferFundsPage transferPage = new TransferFundsPage(driver, wait);
        AccountPage accountPage = new AccountPage(driver, wait);

        this.login();
        accountPage.clickLinkTransferenciaDeFondos();
        transferPage.escribirImporte("100");
        transferPage.seleccionarCuentaDe();
        transferPage.seleccionarCuentaA();
        transferPage.clickBotonTransfer();

        String mensaje = transferPage.obtenerTextoTitulo();

        assertEquals(mensaje, "Transfer Complete!");
        test.log(Status.PASS, "Validación de texto de transferencia exitosa");

    }


    @AfterEach
    public void cerrar() {
        AccountPage accountPage = new AccountPage(driver, wait);
        accountPage.close();
    }

    @AfterAll
    public static void createReport() {
        extent.flush();
    }
}
