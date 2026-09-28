/*
 * João Pedro G M Silva
 * Matrícula: 20251012000740
 * ADS PUC Goiás
 */
package com.capsuladofuturo.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Prova as 6 regras de validação da cápsula em JVM pura (sem dispositivo).
 */
public class ValidadorCapsulaTest {

    @Test
    public void autorVazioInvalido() {
        assertEquals(ValidadorCapsula.Erro.AUTOR_VAZIO, ValidadorCapsula.validarAutor("   "));
        assertEquals(ValidadorCapsula.Erro.NENHUM, ValidadorCapsula.validarAutor("Fernando"));
        System.out.println("autor vazio = INVÁLIDO; autor preenchido = VÁLIDO");
    }

    @Test
    public void tituloVazioInvalido() {
        assertEquals(ValidadorCapsula.Erro.TITULO_VAZIO, ValidadorCapsula.validarTitulo(""));
        assertEquals(ValidadorCapsula.Erro.NENHUM, ValidadorCapsula.validarTitulo("Uma mensagem para o meu futuro"));
        System.out.println("título vazio = INVÁLIDO; título preenchido = VÁLIDO");
    }

    @Test
    public void ano2026Invalido2030Valido() {
        assertEquals(ValidadorCapsula.Erro.ANO_NAO_POSTERIOR, ValidadorCapsula.validarAno("2026"));
        assertEquals(ValidadorCapsula.Erro.NENHUM, ValidadorCapsula.validarAno("2030"));
        System.out.println("ano 2026 = INVÁLIDO (não é posterior a 2026); ano 2030 = VÁLIDO");
    }

    @Test
    public void anoVazioInvalido() {
        assertEquals(ValidadorCapsula.Erro.ANO_VAZIO, ValidadorCapsula.validarAno(" "));
        assertEquals(ValidadorCapsula.Erro.ANO_NAO_POSTERIOR, ValidadorCapsula.validarAno("abc"));
        System.out.println("ano vazio = INVÁLIDO; ano não numérico = INVÁLIDO");
    }

    @Test
    public void mensagemDe19CaracteresInvalida() {
        String dezenove = "0123456789012345678"; // 19 caracteres
        assertEquals(19, dezenove.length());
        assertEquals(ValidadorCapsula.Erro.MENSAGEM_CURTA, ValidadorCapsula.validarMensagem(dezenove));
        System.out.println("mensagem com 19 caracteres = INVÁLIDA");
    }

    @Test
    public void mensagemDe20CaracteresValida() {
        String vinte = "01234567890123456789"; // 20 caracteres
        assertEquals(20, vinte.length());
        assertEquals(ValidadorCapsula.Erro.NENHUM, ValidadorCapsula.validarMensagem(vinte));
        System.out.println("mensagem com 20 caracteres = VÁLIDA");
    }

    @Test
    public void mensagemVaziaInvalida() {
        assertEquals(ValidadorCapsula.Erro.MENSAGEM_VAZIA, ValidadorCapsula.validarMensagem(""));
        System.out.println("mensagem vazia = INVÁLIDA");
    }
}
