import java.util.Scanner;

public class Main {
    public void ValidaCorreto(String palavraDoDia, char[] letrasTentativa, char[] letrasCorretas) {
        // array de char que armazena as letras que ja estão certas, evitando verificação do amarelo de novo
        char[] verificadas = new char[palavraDoDia.length()];

        //Validação de letrasTentativa com emoji
        String emojiVerde = "✅";
        String emojiAmarelo = "🟨";
        String emojiVermelho = "❌";

        for (int i = 0; i < letrasCorretas.length; i++) {
            // Verifica se a letra está correta
            if (Character.toLowerCase(letrasTentativa[i]) == Character.toLowerCase(letrasCorretas[i])) {
                System.out.print(emojiVerde + " ");
                verificadas[i] = letrasTentativa[i];
            } else {
                // Verifica se a letra existe na palavra mas está errada
                boolean letraErrada = true;
                for (int j = 0; j < letrasCorretas.length; j++) {
                    if (verificadas[j] == '\u0000' && Character.toLowerCase(letrasTentativa[i]) == Character.toLowerCase(letrasCorretas[j])) {
                        letraErrada = false;
                        break;
                    }
                }

                if (letraErrada) {
                    System.out.print(emojiVermelho + " ");
                } else {
                    System.out.print(emojiAmarelo + " ");
                }
            }

        }
        System.out.println();
    }
    public void JogarTentativas(String palavraDoDia, Scanner sc){
        int tentativasMax = 6;
        boolean acertou = false;

        char[] letrasCorretas = palavraDoDia.toCharArray();
        int tentativasFeitas = 0;

        while(tentativasFeitas < tentativasMax){

            String tentativa;
            tentativasFeitas++;

            if (tentativasFeitas <= 1){
                //Pega a primeira tentativa do usuario
                System.out.println("Insira uma palavra com 6 letras para começar: ");
                tentativa = sc.nextLine();
            }
            else {
                // Pega todas as outras tentativas
                System.out.println("Tente novamente: (" + (tentativasMax + 1 - tentativasFeitas ) + " tentativas restantes)");
                tentativa = sc.nextLine();

            }
            // Validação das tentativas
            while (!tentativa.matches("[a-zA-Z]+")|| tentativa.length() != letrasCorretas.length) {
                System.out.println("A palavra está incorreta. Tente novamente:");
                tentativa = sc.nextLine();
            }

           // Transforma em array de char
           char[] letrasTentativa = tentativa.toCharArray();


           // Chama o metodo de validação
           ValidaCorreto(palavraDoDia, letrasTentativa, letrasCorretas);
                // Valida se ta correto e termina o jogo
               if(tentativa.equalsIgnoreCase(palavraDoDia)){
                   System.out.println("Parabéns você conseguiu com " + tentativasFeitas + " tentativa(s)!!");
                    acertou = true;
                   break;
           }

       }
        if (!acertou) {
            System.out.println("Você perdeu! 😢 A palavra era: " + palavraDoDia);
        }

    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // Definição da palavra do dia
        String palavraDoDia = "croto"; // apenas palavras de 5 letras

        // inicializa o jogo
        Main jogo = new Main();
        jogo.JogarTentativas(palavraDoDia, sc);





    }
}
