package br.com.edsonfrs.automacao.pageobject;

import br.com.edsonfrs.automacao.dsl.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CadastroComSucessoDSLTest {

    private WebDriver driver;
    private DSL dsl;
    private CadastroComSucessoDSLTestPage page;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");
        dsl = new DSL(driver);
        page = new CadastroComSucessoDSLTestPage(driver);
    }

    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }

    @Test
    public void deveRealizarUmCadastroComSucesso() {
        page.setNome("Mary");
        page.setSobrenome("Shelley");
        page.setSexoFeminino();
        page.setCarne();
        page.setEscolaridade("Superior");
        page.setEsporte("Natacao");
        page.cadastrar();

        Assertions.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
        Assertions.assertTrue(page.obterNomeCadastro().endsWith("Mary"));
        Assertions.assertTrue(page.obterSobrenomeCadastro().endsWith("Shelley"));
        Assertions.assertEquals("Sexo: Feminino", page.obterSexoCadastro());
        Assertions.assertEquals("Comida: Carne", page.obterComidaCadastro());
        Assertions.assertEquals("Escolaridade: superior", page.obterEscolaridadeCadastro());
        Assertions.assertEquals("Esportes: Natacao", page.obterEsporteCadastro());

        driver.quit();
    }

    @Test
    public void deveValidarNomeObrigatorio(){
        page.cadastrar();
        Assertions.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio(){
        page.setNome("Edgar");
        page.cadastrar();
        Assertions.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio(){
        page.setNome("Edgar");
        page.setSobrenome("Poe");
        page.cadastrar();
        Assertions.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana(){
        page.setNome("Edgar");
        page.setSobrenome("Poe");
        page.setSexoMasculino();
        page.setCarne();
        page.setVegetariano();
        page.cadastrar();

        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso(){
        page.setNome("Edgar");
        page.setSobrenome("Poe");
        page.setSexoMasculino();
        page.setCarne();
        page.setEsporte("Karate", "O que eh esporte?");
        page.cadastrar();

        Assertions.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
