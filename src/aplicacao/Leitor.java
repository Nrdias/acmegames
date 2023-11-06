package aplicacao;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Leitor {

    private BufferedReader br;

    public boolean open(){
        Path path = Paths.get("src/../Arquivos/dadosin.txt");
        try {
            br = Files.newBufferedReader(path, Charset.defaultCharset());
            return true;
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado!");
            return false;
        }
    }

    public String getNextLine()  {
        try {
            String line = null;
            if( (line = br.readLine()) != null)
                return line;
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
        return null;
    }

    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }
    }


}
