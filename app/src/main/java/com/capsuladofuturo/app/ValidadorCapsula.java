/*
 * João Pedro G M Silva
 * Matrícula: 20251012000740
 * ADS PUC Goiás
 */
package com.capsuladofuturo.app;

/**
 * Regras de validação da cápsula, isoladas da interface para poderem ser
 * testadas fora do dispositivo (JVM pura, sem dependência do Android).
 */
public final class ValidadorCapsula {

    /** O ano de abertura precisa ser estritamente posterior a este ano. */
    public static final int ANO_LIMITE = 2026;

    /** Tamanho mínimo da mensagem, em caracteres. */
    public static final int TAMANHO_MINIMO_MENSAGEM = 20;

    /** Erro encontrado em um campo, ou NENHUM quando o campo é válido. */
    public enum Erro {
        NENHUM,
        AUTOR_VAZIO,
        TITULO_VAZIO,
        ANO_VAZIO,
        ANO_NAO_POSTERIOR,
        MENSAGEM_VAZIA,
        MENSAGEM_CURTA
    }

    private ValidadorCapsula() {
    }

    public static Erro validarAutor(String autor) {
        return estaVazio(autor) ? Erro.AUTOR_VAZIO : Erro.NENHUM;
    }

    public static Erro validarTitulo(String titulo) {
        return estaVazio(titulo) ? Erro.TITULO_VAZIO : Erro.NENHUM;
    }

    public static Erro validarAno(String ano) {
        if (estaVazio(ano)) {
            return Erro.ANO_VAZIO;
        }
        int valor;
        try {
            valor = Integer.parseInt(ano.trim());
        } catch (NumberFormatException e) {
            return Erro.ANO_NAO_POSTERIOR;
        }
        return valor > ANO_LIMITE ? Erro.NENHUM : Erro.ANO_NAO_POSTERIOR;
    }

    public static Erro validarMensagem(String mensagem) {
        if (estaVazio(mensagem)) {
            return Erro.MENSAGEM_VAZIA;
        }
        return mensagem.trim().length() >= TAMANHO_MINIMO_MENSAGEM
                ? Erro.NENHUM
                : Erro.MENSAGEM_CURTA;
    }

    private static boolean estaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }
}
