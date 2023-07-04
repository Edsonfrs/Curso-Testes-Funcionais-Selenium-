package br.com.edsonfrs.automacao.pageobject;

import br.com.edsonfrs.automacao.dsl.DSL;
import org.openqa.selenium.WebDriver;

public class CadastroComSucessoDSLTestPage {

    private DSL dsl;

    public CadastroComSucessoDSLTestPage(WebDriver driver){
        dsl =new DSL(driver);
    }

    /* ********     Escrevendo nos campos    ******** */
    public void setNome(String nome){
        dsl.escreve("elementosForm:nome", nome);
    }

    public void setSobrenome(String sobrenome){
        dsl.escreve("elementosForm:sobrenome", sobrenome);
    }

    /* ********     Selecionando elementos Radio - Sexo    ******** */
    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    /* ********     Selecionando elementos Radio - Comida Favorita    ******** */
    public void setCarne() {
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }
    public void setFrango() {
        dsl.clicarRadio("elementosForm:comidaFavorita:1");
    }

    public void setPizza() {
        dsl.clicarRadio("elementosForm:comidaFavorita:2");
    }

    public void setVegetariano() {
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }

    /* ********     Selecionando elementos Combo - Escolaridade    ******** */
    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    /* ********     Selecionando elementos Combo - Esportes    ******** */
    public void setEsporte(String... valores) {
        for (String valor: valores)
            dsl.selecionarCombo("elementosForm:esportes", valor );


    }

    /* ********     Clicando em um botão    ******** */
    public void cadastrar(){
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    /* ********     Obtendo os resultados    ******** */

    public String obterResultadoCadastro(){
        return dsl.obterTexto("resultado");
    }

    public String obterNomeCadastro(){
        return dsl.obterTexto("descNome");
    }

    public String obterSobrenomeCadastro(){
        return dsl.obterTexto("descSobrenome");
    }

    public String obterSexoCadastro(){
        return dsl.obterTexto("descSexo");
    }
    public String obterComidaCadastro(){
        return dsl.obterTexto("descComida");
    }
    public String obterEscolaridadeCadastro(){
        return dsl.obterTexto("descEscolaridade");
    }
    public String obterEsporteCadastro(){
        return dsl.obterTexto("descEsportes");
    }




}
