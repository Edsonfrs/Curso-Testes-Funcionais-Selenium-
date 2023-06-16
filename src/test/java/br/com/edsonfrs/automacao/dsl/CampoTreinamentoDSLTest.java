package br.com.edsonfrs.automacao.dsl;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CampoTreinamentoDSLTest {
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
    public void deveInteragirComTextField() {
        dsl.escreve("elementosForm:nome", "Teste de escrita TextField");
        String texto = dsl.obtemValorCampo("elementosForm:nome");
        Assertions.assertEquals("Teste de escrita TextField", texto );
    }

    @Test
    public void deveInteragirComTextArea() {
        dsl.escreve("elementosForm:sugestoes", "Teste de escrita TextArea" );
        String texto = dsl.obtemValorCampo("elementosForm:sugestoes");
        Assertions.assertEquals("Teste de escrita TextArea", texto );
    }

    @Test
    public void deveInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assertions.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
        Assertions.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        String texto = dsl.obterValorCombo("elementosForm:escolaridade");
        Assertions.assertEquals("Mestrado",texto);
    }

    @Test
    public void deveVerificarValoresDoCombo() {
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> options = combo.getOptions();
        //Checando a quantidade de opções do combo
        Assertions.assertEquals(8, options.size());
        //Checando se um determinado status do combo foi encontrado
        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Mestrado")) {
                encontrou = true;
            }
        }
        Assertions.assertTrue(encontrou);

    }

    @Test
    public void deveInteragirComComboDeMultiplaEscolha() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao" );
        dsl.selecionarCombo("elementosForm:esportes", "Corrida" );
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        List<WebElement> allSelectedOptionsoptions = combo.getAllSelectedOptions();
        Assertions.assertEquals(3, allSelectedOptionsoptions.size());

        combo.deselectByVisibleText("Corrida");
        allSelectedOptionsoptions = combo.getAllSelectedOptions();
        Assertions.assertEquals(2, allSelectedOptionsoptions.size());

    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        String texto = dsl.obterValorElemento("buttonSimple", "value");

        Assertions.assertEquals("Obrigado!", texto );
    }
    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        String texto = dsl.obterTexto("resultado");

        Assertions.assertEquals("Voltou!", texto);
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assertions.assertEquals("Campo de Treinamento",
                dsl.obterTexto(By.tagName("h3")));
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }
}
