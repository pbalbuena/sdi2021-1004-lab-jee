package com.uniovi.tests;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddUserView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NotaneitorComplementario {
	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "D:\\Universidad\\3\\SDI\\2021\\lab5\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):
	// static String PathFirefox65 =
	// "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	// static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	} /* Resto del código de la clase */

	// Antes de cada prueba se navega al URL home de la aplicación
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	////////////////////////////////////////////////
	// Registro de profesores con datos válidos. (relanzar la app para que el dni no este duplicado)
	@Test
	public void prueba1() {
		// entrar como admin
		PO_LoginView.entrarAdmin(driver);
		// registrar un profesor
		driver.navigate().to("http://localhost:8090/user/add");
		PO_AddUserView.fillForm(driver, "12345679A", "profesor", "apellidoprof", "123456");
		// Comprobamos que estamos en la lista de usuarios
		PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran");
	}

	// Registro de profesores con datos inválidos (nombre y categoría inválidos).
	@Test
	public void prueba2() {
		// entrar como admin
		PO_LoginView.entrarAdmin(driver);
		// registrar un profesor
		driver.navigate().to("http://localhost:8090/user/add");
		PO_AddUserView.fillForm(driver, "86523481E", "pr", "apellidoprof", "123456");
		// COmprobamos el error de Nombre corto .
		PO_AddUserView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
	}

	// Verificar que solo los usuarios autorizados pueden dar de alta un profesor.
	@Test
	public void prueba3Alumno() {
		// entrar como admin
		PO_LoginView.entrarAlumno(driver);
		// registrar un profesor
		driver.navigate().to("http://localhost:8090/user/add");
		// comprobamos que no tenemos acceso
		SeleniumUtils.textoNoPresentePagina(driver, "Añadir usuario");
	}

	// Verificar que solo los usuarios autorizados pueden dar de alta un profesor.
	@Test
	public void prueba3Profesor() {
		// entrar como admin
		PO_LoginView.entrarProfesor(driver);
		// registrar un profesor
		driver.navigate().to("http://localhost:8090/user/add");
		// comprobamos que no tenemos acceso
		SeleniumUtils.textoNoPresentePagina(driver, "Añadir usuario");
	}

	// muestre el listado de profesores y compruebe que se muestran todos los
	// registros de profesores que existen en el sistema
	@Test
	public void prueba4() {
		// entrar como admin
		PO_LoginView.entrarAdmin(driver);
		// registrar un profesor
		driver.navigate().to("http://localhost:8090/professor/list");
		// Contamos el número de filas
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
				PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
}
