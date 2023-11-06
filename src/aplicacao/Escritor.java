package aplicacao;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Escritor {
    private FileOutputStream arquivoSaida;
    private PrintStream saida;

    public Escritor(){
        try{
        this.arquivoSaida = new FileOutputStream("/home/ndias/workspace/acmegames/Arquivos/dadosout.txt");
        }catch (FileNotFoundException e){
            System.out.println("Arquivo não encontrado!");
            System.out.println("Informe o nome correto do arquivo:");
        }
        open(arquivoSaida);
    }

    public void open(FileOutputStream arquivoSaida) {
        saida = new PrintStream(arquivoSaida);
    }

    public void writer(String texto) {
        saida.println(texto);
    }

    public void close() {
        saida.close();
    }

}
