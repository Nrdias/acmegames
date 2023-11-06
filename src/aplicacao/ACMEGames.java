package aplicacao;

import dados.Categoria;
import dados.JogoEletronico;
import dados.JogoTabuleiro;
import dados.Ludoteca;
import dados.Jogo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ACMEGames {

	private Ludoteca ludoteca;
	private Leitor leitor;
	private Escritor escritor;

	public ACMEGames() {
		ludoteca = new Ludoteca();
		leitor = new Leitor();
		escritor = new Escritor();
	}

	public void executa() {
		if(!leitor.open()){
			System.out.println("Certifique-se de que o arquivo \'dadosin.txt\' esteja na pasta correta");
			return;
		}
		cadastrosDoArquivo(leitor, escritor);
		consultaJogoPeloNome(leitor.getNextLine(), escritor);
		consultaJogosPeloAno(Integer.parseInt(leitor.getNextLine()), escritor);
		consultaJogosPelaCategoria(leitor.getNextLine(), escritor);
		somaPrecoFinal(escritor);
		jogoDeTabuleiroComMaiorPreco(escritor);
		jogosComPrecoBasePertoDaMedia(escritor);
		jogoDeTabuleiroMaisAntigo(escritor);
		leitor.close();
		escritor.close();
	}

	public void cadastrosDoArquivo(Leitor leitor, Escritor escritor){
		String[] dados;
		String linha = leitor.getNextLine();
		while(linha != null && !linha.equals("")){
			if(linha.contains("-1")) break;
			dados = linha.split(";");
			cadastrarJogosEletronicos(dados, escritor);
			linha = leitor.getNextLine();
		}
		linha = leitor.getNextLine();
		while(linha != null && !linha.equals("")){
			if(linha.contains("-1")) break;
			dados = linha.split(";");
			cadastrarjogosDeTabuleiro(dados, escritor);
			linha = leitor.getNextLine();
		}
	}
	public void cadastrarJogosEletronicos(String[] dados, Escritor escritor){
		JogoEletronico jogo;
		try {
			jogo = new JogoEletronico(dados[0], Integer.parseInt(dados[1]), Double.parseDouble(dados[2]), dados[3], Categoria.getCategoria(dados[4]));
		}catch (Exception e){
			System.out.println("1:Dados incorretos e/ou informados incorretamente, verifique o arquivo e tente novamente \n o jogo não será cadastrado");
			return;
		}
			if(!ludoteca.getJogo(jogo)){
				ludoteca.addJogo(jogo);
				System.out.println("1:" + dados[0] + ",R$ " + jogo.calculaPrecoFinal());
				escritor.writer("1:" + dados[0] + ",R$ " + jogo.calculaPrecoFinal());
			}else{
				System.out.println("1:Erro-jogo com nome repetido: " + dados[0]);
				escritor.writer("1:Erro-jogo com nome repetido: " + dados[0]);
			}
	}
	public void cadastrarjogosDeTabuleiro(String[] dados, Escritor escritor){
		JogoTabuleiro jogo;
		try {
			jogo = new JogoTabuleiro(dados[0], Integer.parseInt(dados[1]), Double.parseDouble(dados[2]), Integer.parseInt(dados[3]));
		}catch (Exception e){
			System.out.println("2:Dados incorretos e/ou informados incorretamente, verifique o arquivo e tente novamente \n o jogo não será cadastradp");
			return;
		}
			if(!ludoteca.getJogo(jogo)){
				ludoteca.addJogo(jogo);
				System.out.println("2:" + dados[0] + ",R$ " + jogo.calculaPrecoFinal());
				escritor.writer("2:" + dados[0] + ",R$ " + jogo.calculaPrecoFinal());
			}else{
				System.out.println("2:Erro-jogo com nome repetido: " + dados[0]);
				escritor.writer("2:Erro-jogo com nome repetido: " + dados[0]);
			}
	}
	public void consultaJogoPeloNome(String nome, Escritor escritor){
		Jogo aux = ludoteca.consultaPorNome(nome);
		if(aux != null) {
			System.out.println("3:" + aux.toString() + ",R$ " + aux.calculaPrecoFinal());
			escritor.writer("3:" + aux.toString() + ",R$ " + aux.calculaPrecoFinal());
		}else{
			System.out.println("3:Nome inexistente");
			escritor.writer("3:Nome inexistente");
		}
	}
	public void consultaJogosPeloAno(int ano, Escritor escritor){
		ArrayList<Jogo> aux = ludoteca.consultaPorAno(ano);
		if (aux.isEmpty()){
			System.out.println("4:Nenhum jogo encontrado");
			escritor.writer("4:Nenhum jogo encontrado");
			return;
		}
		for (Jogo jogo: aux
			 ) {
			System.out.println("4:" + jogo.toString() + ",R$ " + jogo.calculaPrecoFinal());
			escritor.writer("4:" + jogo.toString() + ",R$ " + jogo.calculaPrecoFinal());
		}
	}
	public void consultaJogosPelaCategoria(String categoria, Escritor escritor){
		ArrayList<Jogo> aux = ludoteca.consultaPorCategoria(categoria);
		if (aux.isEmpty()) {
			System.out.println("5:Nenhum jogo encontrado");
			escritor.writer("5:Nenhum jogo encontrado");
			return;
		};
		for (Jogo jogo: aux
				) {
			System.out.println("5:" + jogo.toString() + ",R$ " + jogo.calculaPrecoFinal());
			escritor.writer("5:" + jogo.toString() + ",R$ " + jogo.calculaPrecoFinal());
		}
	}
	public void somaPrecoFinal(Escritor escritor){
		DecimalFormat formatador = new DecimalFormat(".00");
		double aux = Double.parseDouble(formatador.format(ludoteca.somaPrecoFinal()));

		if (aux == 0) {
			System.out.println("6:Nenhum jogo encontrado");
			escritor.writer("6:Nenhum jogo encontrado");
			return;
		};
		System.out.println("6:R$ " + aux);
		escritor.writer("6:R$ " + aux);
	}
	public void jogoDeTabuleiroComMaiorPreco(Escritor escritor){
		JogoTabuleiro aux = ludoteca.jogoDeTabuleiroComMaiorPreco();
		if (aux == null) {
			System.out.println("7:Nenhum jogo encontrado");
			escritor.writer("7:Nenhum jogo encontrado");
		}else {
			System.out.println("7:" + aux.getNome() + ",R$ " + aux.calculaPrecoFinal());
			escritor.writer("7:" + aux.getNome() + ",R$ " + aux.calculaPrecoFinal());
		}
	}
	public void jogosComPrecoBasePertoDaMedia(Escritor escritor){
		DecimalFormat formatador = new DecimalFormat("#.00");
		List<Jogo> jogos = ludoteca.getJogos();
		if (jogos.isEmpty()) {
			System.out.println("8:Nenhum jogo encontrado.");
			escritor.writer("8:Nenhum jogo encontrado.");
			return;
		}
		double somatorio = 0;
		for (Jogo jogo : jogos) {
			somatorio += jogo.getPrecoBase();
		}
		double media = somatorio / jogos.size();

		Jogo jogoMaisProximo = null;
		double valorMaisProximo = Double.MAX_VALUE;

		for (Jogo jogo : jogos) {
			double diferenca = Math.abs(jogo.getPrecoBase() - media);
			if (diferenca < valorMaisProximo) {
				valorMaisProximo = diferenca;
				jogoMaisProximo = jogo;
			}
		}

		System.out.println("8:R$ " + formatador.format(media) + "," + jogoMaisProximo.toString() + ",R$ " + jogoMaisProximo.calculaPrecoFinal());
		escritor.writer("8:R$ " + formatador.format(media) + "," + jogoMaisProximo.toString() + ",R$ " + jogoMaisProximo.calculaPrecoFinal());
	}
	public void jogoDeTabuleiroMaisAntigo(Escritor escritor){
		JogoTabuleiro aux = ludoteca.getJogoMaisAntigo();
		if (aux == null) {
			System.out.println("9:Nenhum jogo encontrado");
			escritor.writer("9:Nenhum jogo encontrado");
			return;
		}
		System.out.println("9:" + aux.getNome() + "," + aux.getAno());
		escritor.writer("9:" + aux.getNome() + "," + aux.getAno());
	}
}