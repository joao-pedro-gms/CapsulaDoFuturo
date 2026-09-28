/*
 * João Pedro G M Silva
 * Matrícula: 20251012000740
 * ADS PUC Goiás
 */
package com.capsuladofuturo.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.capsuladofuturo.app.databinding.ActivityCapsulaBinding;

/**
 * Tela 2: visualização da cápsula selada.
 */
public class CapsulaActivity extends AppCompatActivity {

    private ActivityCapsulaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCapsulaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String autor = textoExtra(getString(R.string.EXTRA_AUTOR));
        String titulo = textoExtra(getString(R.string.EXTRA_TITULO));
        String ano = textoExtra(getString(R.string.EXTRA_ANO));
        String mensagem = textoExtra(getString(R.string.EXTRA_MENSAGEM));
        int importancia = getIntent().getIntExtra(getString(R.string.EXTRA_IMPORTANCIA), 0);

        binding.textoTituloCapsula.setText(titulo);
        binding.textoFrase.setText(getString(R.string.frase_capsula, autor, ano));
        binding.textoImportancia.setText(getString(R.string.valor_importancia, importancia));
        binding.textoMensagem.setText(mensagem);

        binding.botaoEditar.setOnClickListener(v -> finish());

        binding.botaoCompartilhar.setOnClickListener(v -> {
            String texto = getString(R.string.texto_compartilhar, ano, titulo, mensagem);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, texto);
            startActivity(Intent.createChooser(intent, getString(R.string.titulo_compartilhar)));
        });
    }

    /** Lê um extra da Intent sem quebrar quando ele não existe (ex.: restauração pelo sistema). */
    private String textoExtra(String chave) {
        String valor = getIntent().getStringExtra(chave);
        return valor == null ? "" : valor;
    }
}
