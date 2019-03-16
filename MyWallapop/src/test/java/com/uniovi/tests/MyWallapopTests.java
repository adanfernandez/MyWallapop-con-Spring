
package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_AddProduct;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_ListView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_Pagination;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_SearchProducts;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;
import com.uniovi.entities.Product;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.ProductsService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {

	// En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas)):
	// static String PathFirefox64 = "C:\\Program Files\\Mozilla
	// Firefox\\firefox.exe";
	// static String Geckdriver022 = "C:\\Path\\geckodriver024win64.exe";
	// En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens
	// automáticas):

	static String PathFirefox65 = "C:\\Users\\adanv\\Desktop\\FirefoxPortable\\App\\Firefox64\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\adanv\\Desktop\\EII\\SDI\\Laboratorio\\5\\PL-SDI-Sesión5-material\\pl5\\geckodriver022win64.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	@Autowired
	private UsersService usersService;

	@Autowired
	private ProductsService productsService;

	@Autowired
	private RolesService rolesService;
	@Autowired
	private UsersRepository usersRepository;

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		initdb();
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

	public void initdb() {
		// Borramos todas las entidades.
		usersRepository.deleteAll();

		User user1 = new User("pedro@pedro", "Pedro", "Díaz");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);

		User user2 = new User("lucas@lucas", "Lucas", "Núñez");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);

		User user3 = new User("maria@maria", "María", "Rodríguez");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);

		User user4 = new User("marta@marta", "Marta", "Almonte");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);

		User user5 = new User("pelayo@pelayo", "Pelayo", "Valdes");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);

		User user6 = new User("admin@admin", "Edward", "Núñez");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		Set<Product> user1Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo A1", "Descripción A1", 100.0, user1));
				add(new Product("Titulo A2", "Descripción A2", 9.0, user1));
				add(new Product("Titulo A3", "Descripción A3", 7.0, user1));
				add(new Product("Titulo A4", "Descripción A4", 6.5, user1));
			}
		};

		Set<Product> user2Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo B1", "Descripción B1", 5.0, user2));
				add(new Product("Titulo B2", "Descripción B2", 4.3, user2));
				add(new Product("Titulo B3", "Descripción B3", 8.0, user2));
				add(new Product("Titulo B4", "Descripción B4", 3.5, user2));
			}
		};

		Set<Product> user3Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				;
				add(new Product("Titulo C1", "Descripción C1", 5.5, user3));
				add(new Product("Titulo C2", "Descripción C2", 6.6, user3));
				add(new Product("Titulo C3", "Descripción C3", 7.0, user3));
			}
		};

		Set<Product> user4Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo D1", "Descripción D1", 10.0, user4));
				add(new Product("Titulo D2", "Descripción D2", 8.0, user4));
				add(new Product("Titulo D3", "Descripción D3", 9.0, user4));
			}
		};

		Set<Product> user5Desc = new HashSet<Product>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Product("Titulo E1", "Descripción E1", 13.0, user5));
				add(new Product("Titulo E2", "Descripción E2", 12.0, user5));
				add(new Product("Titulo E3", "Descripción E3", 11.0, user5));
			}
		};

		/*
		 * Set<Product> user1Purchaseds = new HashSet<Product>(); Set<Product>
		 * user2Purchaseds = new HashSet<Product>(); Set<Product> user3Purchaseds = new
		 * HashSet<Product>(); Set<Product> user4Purchaseds = new HashSet<Product>();
		 * Set<Product> user5Purchaseds = new HashSet<Product>();
		 */

		Product product1 = new Product("Titulo F1", "Descripción F1", 14.0, user1, user2);
		user1Desc.add(product1);
		// user2Purchaseds.add(product1);

		Product product2 = new Product("Titulo G1", "Descripción G1", 15.0, user1, user3);
		user1Desc.add(product2);
		// user3Purchaseds.add(product2);

		Product product3 = new Product("Titulo H1", "Descripción H1", 16.0, user2, user4);
		user2Desc.add(product3);
		// user4Purchaseds.add(product3);

		Product product4 = new Product("Titulo I1", "Descripción I1", 17.0, user2, user5);
		user2Desc.add(product4);
		// user5Purchaseds.add(product4);

		Product product5 = new Product("Titulo J1", "Descripción J1", 18.0, user3, user1);
		user3Desc.add(product5);
		// user1Purchaseds.add(product5);

		Product product6 = new Product("Titulo K1", "Descripción K1", 19.0, user3, user2);
		user3Desc.add(product6);
		// user2Purchaseds.add(product6);

		Product product7 = new Product("Titulo L1", "Descripción L1", 20.0, user4, user3);
		user4Desc.add(product7);
		// user3Purchaseds.add(product7);

		Product product8 = new Product("Titulo M1", "Descripción M1", 21.0, user4, user5);
		user4Desc.add(product8);
		// user5Purchaseds.add(product8);

		Product product9 = new Product("Titulo N1", "Descripción N1", 22.0, user5, user4);
		user5Desc.add(product9);
		// user4Purchaseds.add(product9);

		Product product10 = new Product("Titulo O1", "Descripción O1", 23.0, user5, user1);
		user5Desc.add(product10);
		// user1Purchaseds.add(product10);

		user1.setProducts(user1Desc);
		user2.setProducts(user2Desc);
		user3.setProducts(user3Desc);
		user4.setProducts(user4Desc);
		user5.setProducts(user5Desc);

//		user1.setPurchaseds(user1Purchaseds);
//		user2.setPurchaseds(user2Purchaseds);
//		user3.setPurchaseds(user3Purchaseds);
//		user4.setPurchaseds(user4Purchaseds);
//		user5.setPurchaseds(user5Purchaseds);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

	}

	// PR01. Registro de Usuario con datos válidos
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "josefo@josefo", "Josefo", "Perez", "123456", "123456");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Gestión de productos");

	}

	// PR02. Registro de Usuario con datos inválidos (email vacío, nombre vacío,
	// apellidos vacíos)
	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "", "", "", "123456", "123456");
		// Comprobamos que no pasa nada puesto que es un campo required. Seguimos en la
		// misma página
		PO_View.checkElement(driver, "text", "Registrate");
	}

	// PR03. Registro de Usuario con datos inválidos(repetición de contraseña
	// inválida).
	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "adan@adan", "AdanVetusta", "Fernandez", "123456", "654321");
		// Comprobamos que no pasa nada puesto que es un campo required. Seguimos en la
		// misma página
		PO_View.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	// PR04. Registro de Usuario con datos inválidos (email existente).
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "pedro@pedro", "AdanVetusta", "Fernandez", "123456", "654321");
		// Comprobamos que no pasa nada puesto que es un campo required. Seguimos en la
		// misma página
		PO_View.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	// PR05. Inicio de sesión con datos válidos (administrador).
	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin", "admin");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Gestión de usuarios");
	}

	// PR06. Inicio de sesión con datos válidos (usuario estándar).
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Gestión de productos");

	}

	// PR07. Inicio de sesión con datos inválidos (usuario estándar,campo email y
	// contraseña vacíos).
	@Test
	public void PR07() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario con email y contraseñas vacíos
		PO_LoginView.fillForm(driver, "", "");
		// Comprobamos que no pasa nada puesto que es un campo required. Seguimos en la
		// misma página y miramos que sigue el botón
		PO_View.checkElement(driver, "text", "Identifícate");

	}

	// PR08. Inicio de sesión con datos válidos (usuario estándar, email existente,
	// pero contraseña incorrecta)
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");

		// Rellenamos el formulario de manera incorrecta introduciendo una contraseña
		// errónea
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456123456");

		// Comprobamos que nos muestra el mensaje de error
		PO_View.checkKey(driver, "Error.log", PO_Properties.getSPANISH());

	}

	// PR09. Inicio de sesión con datos inválidos (usuario estándar, email no
	// existente en la aplicación).
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario de manera incorrecta introduciendo un correo no
		// registrado
		PO_LoginView.fillForm(driver, "xurde@lluis", "123456");
		// Comprobamos que nos muestra el mensaje de error
		PO_View.checkKey(driver, "Error.log", PO_Properties.getSPANISH());

	}

	// PR10. Hacer click en la opción de salir de sesión y comprobar que se redirige
	// a la página de inicio
	// de sesión (Login).
	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Gestión de productos");
		// Seleccionamos el botón de cerrar sesión
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que estamos en la página de login
		PO_View.checkElement(driver, "text", "Identifícate");
	}

	// PR11. Comprobar que el botón cerrar sesión no está visible si el usuario no
	// está autenticado.
	@Test
	public void PR11() {
		// De primeras estamos sin identificar, por lo que el botón de logout no debería
		// de estar disponible
		PO_HomeView.checkNoElement(driver, "logout");
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456");
		// Comprobamos que entramos en la pagina privada del usuario
		PO_View.checkElement(driver, "text", "Gestión de productos");
		// Seleccionamos el botón de cerrar sesión
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		// Comprobamos que no sale el botón
		PO_HomeView.checkNoElement(driver, "logout");
	}

	// PR12. Mostrar el listado de usuarios y comprobar que se muestran todos los
	// que existen en el sistema.
	@Test
	public void PR12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Listado de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		// Contamos el número de filas de notas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// En total hay 6 usuarios contando el administrador, por eso tendrían que salir
		// 5
		assertTrue(elementos.size() == 5);
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

	}

	// PR13. Ir a la lista de usuarios, borrar el primer usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	@Test
	public void PR13() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Listado de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		// Checkeamos el primer elemento
		elementos = driver.findElements(By.name("checkbox_ids"));
		elementos.get(0).click();
		// Clickamos en el botón de eliminar
		PO_ListView.clickOption(driver, "buttonDelete", "@id", "buttonDelete");
		// PO_ListView.clickOption(driver, "buttonDelete", "class", "btn btn-primary");

		// Contamos el número de filas de notas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// En total hay 6 usuarios contando el administrador, por eso tendrían que salir
		// 5. Al eliminar uno, deberían de salir 4.
		assertTrue(elementos.size() == 4);

	}

	// PR14. Ir a la lista de usuarios, borrar el último usuario de la lista,
	// comprobar que la lista se actualiza y dicho usuario desaparece.
	@Test
	public void PR14() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Listado de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		// Checkeamos el ultimo elemento
		elementos = driver.findElements(By.name("checkbox_ids"));
		elementos.get(elementos.size() - 1).click();
		// Clickamos en el botón de eliminar
		PO_ListView.clickOption(driver, "buttonDelete", "@id", "buttonDelete");

		// Contamos el número de filas de notas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// En total hay 6 usuarios contando el administrador, por eso tendrían que salir
		// 5. Al eliminar uno, deberían de salir 4.
		assertTrue(elementos.size() == 4);

	}

	// PR15. Ir a la lista de usuarios, borrar 3 usuarios, comprobar que la lista se
	// actualiza y dichosusuarios desaparecen.
	@Test
	public void PR15() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@admin", "admin");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'users-menu')]/a");
		elementos.get(0).click();
		// Listado de usuarios
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'user/list')]");
		elementos.get(0).click();
		// Checkeamos el ultimo elemento
		elementos = driver.findElements(By.name("checkbox_ids"));
		elementos.get(0).click();
		elementos.get(1).click();
		elementos.get(2).click();
		// Clickamos en el botón de eliminar
		PO_ListView.clickOption(driver, "buttonDelete", "@id", "buttonDelete");
		// Contamos el número de filas de notas
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// En total hay 7 usuarios contando el administrador, por eso tendrían que salir
		// 6. Al eliminar uno, deberían de salir 5.
		assertTrue(elementos.size() == 2);
	}

	// PR16. Ir al formulario de alta de oferta, rellenarla con datos válidos y
	// pulsar el botón Submit.
	// Comprobar que la oferta sale en el listado de ofertas de dicho usuario
	@Test
	public void PR16() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/add')]");
		elementos.get(0).click();
		// Entramos en el formulario para añadir la oferta
		PO_AddProduct.fillForm(driver, "Flexo de estudio",
				"Flexo de estudio excelente para esas interminables noches haciendo SDI!", "15.00");
		// Miramos si está añadido en nuestras ofertas.
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/product/myProducts')]");
		elementos.get(0).click();
		PO_View.checkElement(driver, "text", "Flexo de estudio");
		PO_View.checkElement(driver, "text", "Flexo de estudio excelente para esas interminables noches haciendo SDI!");
	}

	// PR17. al formulario de alta de oferta, rellenarla con datos inválidos (campo
	// título vacío) y pulsar el botón Submit. Comprobar que se muestra el mensaje
	// de campo obligatorio
	@Test
	public void PR17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "pedro@pedro", "123456");
		// Pinchamos en la opción de menu de Usuarios: //li[contains(@id,
		// 'marks-menu')]/a
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/add')]");
		elementos.get(0).click();
		// Entramos en el formulario para añadir la oferta
		PO_AddProduct.fillForm(driver, "", "Flexo de estudio excelente para esas interminables noches haciendo SDI!",
				"15.00");
		// Al tener la validación en local y servidor, saltará por parte de local en
		// primer lugar al ser un campo requerido
		// Completamos de nuevo el formulario con datos erroneos, comprobando así que
		// seguimos en la misma página a pesar del error inicial
		PO_AddProduct.fillForm(driver, "1", "Flexo de estudio excelente para esas interminables noches haciendo SDI!",
				"15.00");
		PO_View.checkElement(driver, "text", "El título debe de conteer entre 5 y 24 caracteres");
	}

	// PR18. Mostrar el listado de ofertas para dicho usuario y comprobar que se
	// muestran todas los que existen para este usuario.
	@Test
	public void PR18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");
		// En el caso de todos los usuarios no administradores, habrá 6 ofertas.
		// Vamos a su listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/myProducts')]");
		elementos.get(0).click();
		// Comprobamos el número de ofertas que hay en la tabla.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(6, elementos.size());
	}

	// PR19. Ir a la lista de ofertas, borrar la primera oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece.
	@Test
	public void PR19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");
		// En el caso de todos los usuarios no administradores, habrá 6 ofertas.
		// Vamos a su listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/myProducts')]");
		elementos.get(0).click();
		// eliminamos la primera oferta de la lista
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Titulo B4')]/following-sibling::*/a[contains(@href, 'product/delete')]");
		elementos.get(0).click();
		// Comprobamos el número de ofertas que hay en la tabla.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(5, elementos.size());
	}

	// PR20. Ir a la lista de ofertas, borrar la última oferta de la lista,
	// comprobar que la lista se actualiza y
	// que la oferta desaparece
	@Test
	public void PR20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");
		// En el caso de todos los usuarios no administradores, habrá 6 ofertas.
		// Vamos a su listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/myProducts')]");
		elementos.get(0).click();
		// eliminamos la primera oferta de la lista
		elementos = PO_View.checkElement(driver, "free",
				"//td[contains(text(), 'Titulo H1')]/following-sibling::*/a[contains(@href, 'product/delete')]");
		elementos.get(0).click();
		// Comprobamos el número de ofertas que hay en la tabla.
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(5, elementos.size());
	}

	// PR21. Hacer una búsqueda con el campo vacío y comprobar que se muestra la
	// página que
	// corresponde con el listado de las ofertas existentes en el sistema
	@Test
	public void PR21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();

		// Metemos un texto vacío en la búsqueda.
		PO_SearchProducts.fillForm(driver, "");

		// Comprobamos que salen todos los elementos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(5, elementos.size());

		// Vamos de página en página. En total, hay 12 productos: 5 en cada página menos
		// en la última, que hay 2.0
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(2).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(5, elementos.size());
		for (int i = 0; i < 3; i++) {
			elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
			elementos.get(3).click();
			elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
			assertEquals(5, elementos.size());
		}

		elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
		elementos.get(3).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertEquals(2, elementos.size());

	}

	// PR22. Hacer una búsqueda escribiendo en el campo un texto que no exista y
	// comprobar que se muestra la página que corresponde, con la lista de ofertas
	// vacía.
	@Test
	public void PR22() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();

		// Metemos un texto que no existe en la búsqueda.
		PO_SearchProducts.fillForm(driver, "Este text no existe");

		// Comprobamos que salen todos los elementos
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody", PO_View.getTimeout());
		PO_HomeView.checkNoElement(driver, "Titulo");

	}

	// PR23. Sobre una búsqueda determinada (a elección de desarrollador), comprar
	// una oferta que deja
	// un saldo positivo en el contador del comprobador. Y comprobar que el contador
	// se actualiza
	// correctamente en la vista del comprador.
	@Test
	public void PR23() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();
		
		// Metemos un texto.
		PO_SearchProducts.fillForm(driver, "a2");

		PO_View.checkElement(driver, "text", "100.0");
		WebElement button = driver.findElement(By.name("buyButton"));
		button.click();
		PO_View.checkElement(driver, "text", "91.0");
	}

	// PR24. Sobre una búsqueda determinada (a elección de desarrollador), comprar
	// una oferta que deja
	// un saldo 0 en el contador del comprobador. Y comprobar que el contador se
	// actualiza correctamente en
	// la vista del comprador.
	@Test
	public void PR24() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();
		
		// Metemos un texto.
		PO_SearchProducts.fillForm(driver, "a1");

		PO_View.checkElement(driver, "text", "100.0");
		WebElement button = driver.findElement(By.name("buyButton"));
		button.click();
		PO_View.checkElement(driver, "text", "0.0");
	}

	// PR21. Sobre una búsqueda determinada (a elección de desarrollador), intentar
	// comprar una oferta que esté por encima de saldo disponible del comprador. Y comprobar que se
	// muestra el mensaje de saldo no suficiente.
	@Test
	public void PR25() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();
		
		// Metemos un texto.
		PO_SearchProducts.fillForm(driver, "a2");
		
		WebElement button = driver.findElement(By.name("buyButton"));
		button.click();
		
		// Metemos un texto.
		PO_SearchProducts.fillForm(driver, "a1");
		
		button = driver.findElement(By.name("buyButton"));
		button.click();
		
		assertTrue(driver.switchTo().alert().getText().toString().equals("No posee suficiente dinero para comprar el producto"));
		
		driver.switchTo().alert().accept();
		
	}

	// PR26. Ir a la opción de ofertas compradas del usuario y mostrar la lista. Comprobar que aparecen las ofertas que deben aparecer.
	@Test
	public void PR26() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "lucas@lucas", "123456");

		// Vamos al listado de ofertas.
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'product/list')]");
		elementos.get(0).click();
		
		// Metemos un texto.
		PO_SearchProducts.fillForm(driver, "a2");
		WebElement button = driver.findElement(By.name("buyButton"));
		button.click();
		
		//Cada usuario tenia dos compradas. Con este debería de tener 3.
		elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'products-menu')]/a");
		elementos.get(0).click();
		elementos = PO_View.checkElement(driver, "free", "//a[contains(@href,'/product/purchased')]");
		elementos.get(0).click();

		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size()==3);
		

	}

	// PR27. Visualizar al menos cuatro páginas en Español/Inglés/Español
	// (comprobando que algunas de las etiquetas cambian al idioma correspondiente).
	//	Página principal/Opciones Principales de Usuario/Listado de Usuarios de Admin/Vista de alta de Oferta.
	@Test
	public void PR27() {

	}

	// PR28. Intentar acceder sin estar autenticado a la opción de listado de
	// usuarios del administrador. Se  deberá volver al formulario de login.
	@Test
	public void PR28() {

	}

	// PR29. Intentar acceder sin estar autenticado a la opción de listado de
	// ofertas propias de un usuario
	// estándar. Se deberá volver al formulario de login.
	@Test
	public void PR29() {

	}

	// PR30. Estando autenticado como usuario estándar intentar acceder a la opción
	// de listado de
	// usuarios del administrador. Se deberá indicar un mensaje de acción prohibida.

	@Test
	public void PR30() {

	}

}
