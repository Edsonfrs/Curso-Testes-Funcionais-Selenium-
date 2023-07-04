package br.com.edsonfrs.automacao.pageobject;

import br.com.edsonfrs.automacao.dsl.DSL;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
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

    /* ********     Elementos Simples    ******** */
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
        Assertions.assertTrue(dsl.isCheckMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckBox() {
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
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
        dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
        String texto = dsl.obtemValorCampo("elementosForm:escolaridade");
        Assertions.assertEquals("mestrado", texto);

    }

    @Test
    public void deveInteragirComComboDeMultiplaEscolha() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao" );
        dsl.selecionarCombo("elementosForm:esportes", "Corrida" );
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assertions.assertEquals(3, opcoesMarcadas.size());

        dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assertions.assertEquals(2, opcoesMarcadas.size());
        Assertions.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));


    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
       Assertions.assertEquals("Obrigado!", dsl.obterValorElemento("buttonSimple", "value"));
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assertions.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assertions.assertEquals("Campo de Treinamento",
                dsl.obterTexto(By.tagName("h3")));
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }


}
