package br.com.edsonfrs.automacao.inicio;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CampoTreinamento2Test {
    private WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.driver = new ChromeDriver();
        driver.get("file:///"+System.getProperty("user.dir")+"/camp/componentes.html");
    }

    @AfterEach
    public void afterEach(){
        this.driver.quit();
    }

    @Test
    public void deveInteragirComTextField() {
        //driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste\n\n de\n\n escrita\n\n TextField");
        String texto = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
        System.out.println(texto);

        Assertions.assertEquals("Teste de escrita TextField", texto );
    }

    @Test
    public void deveInteragirComTextArea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste de escrita TextArea");
        String texto = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");

        Assertions.assertEquals("Teste de escrita TextArea", texto );
    }

    @Test
    public void deveInteragirComRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:sexo:0")).getAttribute("value");

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void deveInteragirComCheckBox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).getAttribute("value");

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void deveInteragirComCombo() {
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Mestrado");
        String texto = combo.getFirstSelectedOption().getText();

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
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        List<WebElement> options = combo.getAllSelectedOptions();
        Assertions.assertEquals(3, options.size());

        combo.deselectByVisibleText("Corrida");

        options = combo.getAllSelectedOptions();
        Assertions.assertEquals(2, options.size());

    }

    @Test
    public void deveInteragirComBotoes() {
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        String texto = botao.getAttribute("value");

        Assertions.assertEquals("Obrigado!", texto );
    }
    @Test
    public void deveInteragirComLinks() {
        driver.findElement(By.linkText("Voltar")).click();
        String texto = driver.findElement(By.id("resultado")).getText();

        Assertions.assertEquals("Voltou!", texto);
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        Assertions.assertEquals("Campo de Treinamento",
                driver.findElement(By.tagName("h3")).getText());

        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());
    }
}
