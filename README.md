# Cápsula do Futuro

App Android (Java) em que o usuário escreve uma mensagem para o futuro, escolhe o ano de
abertura e visualiza a cápsula "selada" em uma segunda tela. Atividade sobre layouts,
componentes de formulário, validação, eventos, View Binding e navegação com Intents.

## Autoria

- **Nome:** João Pedro G M Silva
- **Matrícula:** 20251012000740
- **Curso:** ADS — PUC Goiás

## Como compilar

Requisitos: JDK 17 e Android SDK (`ANDROID_HOME`). O Gradle 8.9 vem pelo wrapper (`./gradlew`), compatível com o Android Gradle Plugin 8.5.2.

```bash
export ANDROID_HOME=$HOME/Android/Sdk
./gradlew :app:assembleDebug
# APK gerado em: app/build/outputs/apk/debug/app-debug.apk
```

Testes de validação (JVM pura, sem dispositivo):

```bash
./gradlew testDebugUnitTest
```

## Estrutura

- `MainActivity` — tela 1: formulário de criação (autor, título, ano, mensagem, SeekBar de
  importância) com validação e `setError`/`requestFocus` nos campos.
- `CapsulaActivity` — tela 2: exibe a cápsula; botão **Editar** (`finish()`) e
  **Compartilhar** (Intent implícita `ACTION_SEND`).
- `ValidadorCapsula` — regras de validação isoladas da interface (testável em JVM):
  autor/título/ano/mensagem preenchidos, ano posterior a 2026, mensagem ≥ 20 caracteres.
- View Binding nas duas telas (`buildFeatures { viewBinding true }`), zero `findViewById`.
- Sem banco de dados, internet, RecyclerView, fragmentos ou bibliotecas externas novas.

## O que foi validado e como

1. `gradle assembleDebug` → **BUILD SUCCESSFUL**.
2. `gradle testDebugUnitTest` → 7 testes passando: autor vazio inválido, ano 2026
   inválido, ano 2030 válido, mensagem de 19 caracteres inválida, mensagem de 20
   caracteres válida (mais título vazio e mensagem vazia).
3. Smoke test no emulador Android (API 35): erro no campo vazio com foco, ano 2026
   rejeitado, preenchimento válido abrindo a tela 2, rotação sem crash, botão
   Compartilhar abrindo o seletor de apps — screenshots em `/tmp/capsula_*.png`
   (descritos no relatório da entrega).
