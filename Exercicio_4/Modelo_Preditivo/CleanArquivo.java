import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CleanArquivo 
{
    public static boolean temEspecial(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char caractere = senha.charAt(i);
            if (!Character.isLetterOrDigit(caractere)) {
                return true;
            }
        }
        return false;
    } // end temEspecial ( )

    public static boolean temMaiuscula(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char caractere = senha.charAt(i);
            if (Character.isUpperCase(caractere)) {
                return true;
            }
        }
        return false;
    } // end temMaiuscula ( )

    public static boolean temNumero(String senha) {
        for (int i = 0; i < senha.length(); i++) {
            char caractere = senha.charAt(i);
            if (Character.isDigit(caractere)) {
                return true;
            }
        }
        return false;
    } // end temNumero ( )

    public static boolean isNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    } // end isNumero ( )

    public static boolean tamanhoMaiorQue8(String senha) {
        return senha.length() > 8;
    } // end tamanhoMaiorQue8 ( )
    public static boolean apenasUmaTrue(boolean a, boolean b, boolean c, boolean d) {
        return (a && !b && !c && !d) || (!a && b && !c && !d) || (!a && !b && c && !d) || (!a && !b && !c && d);
    } // end apenasUmaTrue ( )
    
    public static void main(String[] args) throws IOException {
        File inputFile = new File("data.csv");
        File outputFile = new File("output.csv");

        PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

        // Escrevendo cabeçalho no arquivo de saída
        writer.println("Senha,ContemEspecial,TamanhoMaiorQue8,ContemMaiuscula,ContemNumero,Power");

        Scanner scanner = new Scanner(inputFile);

        // Ignorando cabeçalho do arquivo de entrada
        scanner.nextLine();

        // Lendo e processando as primeiras 7000 linhas do arquivo
        int count = 0;
        while (scanner.hasNextLine() && count < 7000) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String senha = parts[0];
            String power = parts[1];
            
            // Verifica se o número de caracteres da primeira coluna é maior que 8
            boolean tamanhoMaior8 = tamanhoMaiorQue8(senha);
            
            // Verifica se a senha contém maiúsculas
            boolean contemMaiuscula = temMaiuscula(senha);
            
            // Verifica se o valor na segunda coluna é numérico
            if (!isNumero(power)) {
                // Se não for numérico, pula para a próxima linha
                continue;
            }
            
            // Verificando cada função e armazenando o resultado
            boolean contemEspecial = temEspecial(senha);
            boolean contemNumero = temNumero(senha);

            // Escrevendo no arquivo de saída
            writer.println(senha + "," + (contemEspecial ? 1 : 0) + "," + (tamanhoMaior8 ? 1 : 0) + "," + (contemMaiuscula ? 1 : 0) + "," + (contemNumero ? 1 : 0) + "," + power);
            
            count++;
        } 

        // Selecionando mais 1000 linhas onde o segundo valor é igual a zero
        // count = 0;
        // while (scanner.hasNextLine() && count < 1000) {
        //     String line = scanner.nextLine();
        //     String[] parts = line.split(",");
        //     String senha = parts[0];
        //     String power = parts[1];
            
        //     if (power.equals("0")) {
        //         // Escrevendo no arquivo de saída
        //         boolean tamanhoMaior8 = tamanhoMaiorQue8(senha);
        //         boolean contemEspecial = temEspecial(senha);
        //         boolean contemNumero = temNumero(senha);
        //     // Verifica se a senha contém maiúsculas
        //     boolean contemMaiuscula = temMaiuscula(senha);
        //         writer.println(senha + "," + (contemEspecial ? 1 : 0) + "," + (tamanhoMaior8 ? 1 : 0) + "," + (contemMaiuscula ? 1 : 0) + "," + (contemNumero ? 1 : 0) + "," + power);
        //         count++;
        //     }
        // }

        // Fechando os arquivos
        scanner.close();
        writer.close();

        System.out.println("Processamento concluído. Resultados armazenados em output.csv");
    } // end main ( )
} // end class CleanArquivo
