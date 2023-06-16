package br.com.edsonfrs.automacao.dsl;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CadastroComSucessoDSLTest {

    private WebDriver driver;
    private DSL dsl;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");
        dsl = new DSL(driver);
    }

    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }

    @Test
    public void deveRealizarUmCadastroComSucesso() {
        dsl.escreve("elementosForm:nome", "Bram");
        dsl.escreve("elementosForm:sobrenome", "Stoker");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao" );

        dsl.clicarBotao("elementosForm:cadastrar");

        Assertions.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
        Assertions.assertTrue(dsl.obterTexto("descNome").endsWith("Bram"));
        Assertions.assertTrue(dsl.obterTexto("descSobrenome").endsWith("Stoker"));
        Assertions.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
        Assertions.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
        Assertions.assertEquals("Escolaridade: superior", dsl.obterTexto("descEscolaridade"));
        Assertions.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));

        driver.quit();
    }
}
