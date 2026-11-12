
import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int MAX_RECORDISTAS = 10;

    static String[] nomesRecordistas = new String[MAX_RECORDISTAS];
    static int[] pontuacoesRecordistas = new int[MAX_RECORDISTAS];
    static int quantidadeRecordistas = 0;

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        boolean continuarJogando = true;

        System.out.println("==================================================");
        System.out.println("          BEM-VINDO AO JOGO DO MARCIANO!");
        System.out.println("==================================================");

        while (continuarJogando) {

            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Jogar");
            System.out.println("2. Instrucoes e Historia");
            System.out.println("3. Ver Ranking de Recordistas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opcao: ");

            int opcao = lerOpcaoValida(entrada);

            switch (opcao) {
                case 1:
                    iniciarPartida(entrada);
                    break;

                case 2:
                    exibirHistoriaEInstrucoes();
                    break;

                case 3:
                    exibirRecorde();
                    break;

                case 4:
                    System.out.println("\nObrigado por jogar! Ate a proxima.");
                    continuarJogando = false;
                    break;

                default:
                    System.out.println("Opcao invalida! Digite de 1 a 4.");
                    break;
            }
        }

        entrada.close();
    }

    public static void iniciarPartida(Scanner entrada) {

        Random random = new Random();
        int arvoreMarciano = random.nextInt(100) + 1;

        System.out.println("\n--- SELECAO DE DIFICULDADE ---");
        System.out.println("[F] Facil   - 10 tentativas");
        System.out.println("[M] Medio   - 8 tentativas");
        System.out.println("[D] Dificil - 7 tentativas");
        System.out.print("Escolha a dificuldade (F, M ou D): ");

        String dificuldade = entrada.nextLine().trim().toUpperCase();
        int maxTentativas;

        switch (dificuldade) {
            case "F":
                maxTentativas = 10;
                break;

            case "M":
                maxTentativas = 8;
                break;

            case "D":
                maxTentativas = 7;
                break;

            default:
                System.out.println(
                    "Opcao invalida. O modo Facil sera utilizado."
                );
                maxTentativas = 10;
                break;
        }

        System.out.println(
            "\nO Marciano esta escondido em uma arvore de 1 a 100."
        );
        System.out.println(
            "Voce tem " + maxTentativas + " tentativas para encontra-lo!"
        );

        int tentativasUsadas = 0;
        boolean acertou = false;

        while (tentativasUsadas < maxTentativas && !acertou) {

            System.out.print(
                "\nTentativa " + (tentativasUsadas + 1)
                + " de " + maxTentativas
                + " - Digite o numero da arvore: "
            );

            String texto = entrada.nextLine().trim();
            int palpite;

            try {
                palpite = Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Erro! Digite um numero inteiro.");
                continue;
            }

            if (palpite < 1 || palpite > 100) {
                System.out.println("Digite um numero entre 1 e 100.");
                continue;
            }

            tentativasUsadas++;

            if (palpite == arvoreMarciano) {

                acertou = true;

                System.out.println(
                    "\nPARABENS! Voce encontrou o Marciano!"
                );
                System.out.println(
                    "Arvore correta: " + arvoreMarciano
                );
                System.out.println(
                    "Tentativas utilizadas: " + tentativasUsadas
                );

                registrarRecorde(entrada, tentativasUsadas);

            } else if (palpite < arvoreMarciano) {

                System.out.println(
                    "DICA: O numero correto e MAIOR."
                );

            } else {

                System.out.println(
                    "DICA: O numero correto e MENOR."
                );
            }
        }

        if (!acertou) {
            System.out.println("\nQue pena! Suas tentativas acabaram.");
            System.out.println(
                "O Marciano estava na arvore " + arvoreMarciano + "."
            );
        }
    }

    public static void exibirHistoriaEInstrucoes() {

        System.out.println("\n==================================================");
        System.out.println("               HISTORIA DO MARCIANO");
        System.out.println("==================================================");

        System.out.println(
            "Um pequeno Marciano chegou a Terra por acidente."
        );
        System.out.println(
            "Ao explorar uma floresta, ele ficou assustado"
        );
        System.out.println(
            "e decidiu se esconder em uma das 100 arvores."
        );
        System.out.println(
            "Sua missao e encontrar o Marciano antes que"
        );
        System.out.println(
            "suas tentativas acabem!"
        );

        System.out.println("\n==================================================");
        System.out.println("                  COMO JOGAR");
        System.out.println("==================================================");

        System.out.println(
            "1. O jogo escolhe uma arvore numerada de 1 a 100."
        );
        System.out.println(
            "2. Digite um numero para encontrar o Marciano."
        );
        System.out.println(
            "3. O jogo informa se o numero correto e maior ou menor."
        );
        System.out.println(
            "4. Letras e numeros fora do intervalo nao contam"
        );
        System.out.println(
            "   como tentativas."
        );
        System.out.println(
            "5. Tente encontrar o Marciano usando poucas tentativas."
        );
        System.out.println(
            "6. Os melhores resultados podem entrar no ranking."
        );

        System.out.println("\n==================================================");
        System.out.println("             NIVEIS DE DIFICULDADE");
        System.out.println("==================================================");

        System.out.println("F - Facil: 10 tentativas");
        System.out.println("M - Medio: 8 tentativas");
        System.out.println("D - Dificil: 7 tentativas");

        System.out.println("\nBoa sorte na busca pelo Marciano!");
        System.out.println("==================================================");
    }

    public static void registrarRecorde(
            Scanner entrada, int pontuacao) {

        // Procura onde o resultado deve entrar no ranking.
        int posicao = 0;

        while (posicao < quantidadeRecordistas
                && pontuacoesRecordistas[posicao] <= pontuacao) {
            posicao++;
        }

        // Se o ranking estiver cheio e o resultado nao for suficiente,
        // o jogador nao sera adicionado.
        if (quantidadeRecordistas == MAX_RECORDISTAS
                && posicao == quantidadeRecordistas) {

            System.out.println(
                "\nSeu resultado nao entrou no ranking dos 10 melhores."
            );
            return;
        }

        System.out.print("Digite seu nome para o ranking: ");
        String nome = entrada.nextLine().trim();

        if (nome.isEmpty()) {
            nome = "Jogador";
        }

        // Aumenta a quantidade se ainda houver espaco.
        if (quantidadeRecordistas < MAX_RECORDISTAS) {
            quantidadeRecordistas++;
        }

        // Desloca os resultados para abrir espaco para o novo jogador.
        for (int i = quantidadeRecordistas - 1; i > posicao; i--) {
            nomesRecordistas[i] = nomesRecordistas[i - 1];
            pontuacoesRecordistas[i] = pontuacoesRecordistas[i - 1];
        }

        nomesRecordistas[posicao] = nome;
        pontuacoesRecordistas[posicao] = pontuacao;

        System.out.println(
            "\nResultado registrado no ranking com sucesso!"
        );
    }

    public static void exibirRecorde() {

        System.out.println("\n==================================================");
        System.out.println("             RANKING DE RECORDISTAS");
        System.out.println("==================================================");

        if (quantidadeRecordistas == 0) {

            System.out.println(
                "Ainda nao existem recordes registrados."
            );

        } else {

            System.out.printf(
                "%-6s %-25s %s%n",
                "Pos.", "Jogador", "Tentativas"
            );

            System.out.println(
                "--------------------------------------------------"
            );

            for (int i = 0; i < quantidadeRecordistas; i++) {
                System.out.printf(
                    "%-6d %-25s %d%n",
                    i + 1,
                    nomesRecordistas[i],
                    pontuacoesRecordistas[i]
                );
            }
        }

        System.out.println("==================================================");
    }

    public static int lerOpcaoValida(Scanner entrada) {

        String texto = entrada.nextLine().trim();

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}       