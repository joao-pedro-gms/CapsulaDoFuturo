/*
 * João Pedro G M Silva
 * Matrícula: 20251012000740
 * ADS PUC Goiás
 */
package com.capsuladofuturo.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.capsuladofuturo.app.databinding.ActivityMainBinding;

/**
 * Tela 1: criação da cápsula do tempo.
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configurarSeekBar();
        binding.botaoSelarCapsula.setOnClickListener(v -> selarCapsula());
    }

    private void configurarSeekBar() {
        binding.textoImportancia.setText(
                getString(R.string.valor_importancia, binding.barraImportancia.getProgress()));
        binding.barraImportancia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textoImportancia.setText(getString(R.string.valor_importancia, progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void selarCapsula() {
        String autor = binding.campoAutor.getText().toString();
        String titulo = binding.campoTitulo.getText().toString();
        String ano = binding.campoAno.getText().toString();
        String mensagem = binding.campoMensagem.getText().toString();

        boolean autorValido = marcarErro(binding.campoAutor, ValidadorCapsula.validarAutor(autor));
        boolean tituloValido = marcarErro(binding.campoTitulo, ValidadorCapsula.validarTitulo(titulo));
        boolean anoValido = marcarErro(binding.campoAno, ValidadorCapsula.validarAno(ano));
        boolean mensagemValido = marcarErro(binding.campoMensagem, ValidadorCapsula.validarMensagem(mensagem));

        if (!autorValido || !tituloValido || !anoValido || !mensagemValido) {
            if (!autorValido) {
                binding.campoAutor.requestFocus();
            } else if (!tituloValido) {
                binding.campoTitulo.requestFocus();
            } else if (!anoValido) {
                binding.campoAno.requestFocus();
            } else {
                binding.campoMensagem.requestFocus();
            }
            return;
        }

        Intent intent = new Intent(this, CapsulaActivity.class);
        intent.putExtra(getString(R.string.EXTRA_AUTOR), autor);
        intent.putExtra(getString(R.string.EXTRA_TITULO), titulo);
        intent.putExtra(getString(R.string.EXTRA_ANO), ano);
        intent.putExtra(getString(R.string.EXTRA_MENSAGEM), mensagem);
        intent.putExtra(getString(R.string.EXTRA_IMPORTANCIA), binding.barraImportancia.getProgress());
        startActivity(intent);
    }

    /** Mostra o erro no campo e devolve false quando o campo está inválido. */
    private boolean marcarErro(EditText campo, ValidadorCapsula.Erro erro) {
        if (erro == ValidadorCapsula.Erro.NENHUM) {
            campo.setError(null);
            return true;
        }
        campo.setError(getString(mensagemDe(erro)));
        return false;
    }

    private int mensagemDe(ValidadorCapsula.Erro erro) {
        switch (erro) {
            case AUTOR_VAZIO:
                return R.string.erro_autor_vazio;
            case TITULO_VAZIO:
                return R.string.erro_titulo_vazio;
            case ANO_VAZIO:
                return R.string.erro_ano_vazio;
            case ANO_NAO_POSTERIOR:
                return R.string.erro_ano_nao_posterior;
            case MENSAGEM_VAZIA:
                return R.string.erro_mensagem_vazia;
            case MENSAGEM_CURTA:
                return R.string.erro_mensagem_curta;
            default:
                throw new IllegalArgumentException("Erro sem mensagem de tela: " + erro);
        }
    }
}
