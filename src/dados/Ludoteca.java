package dados;

import java.util.ArrayList;
import java.util.List;

public class Ludoteca implements Iterador {

	private int contador;
	private Jogo current;
	private List<Jogo> jogos;
	public Ludoteca() {
		contador = 0;
		jogos = new ArrayList<Jogo>();
	}

	public List<Jogo> getJogos() {
		List<Jogo> aux = jogos.stream().toList();
		return aux;
	}

	public int size(){
		return jogos.size();
	}

	public boolean addJogo(Jogo jogo) {
		jogos.add(jogo);
		return true;
	}

	public boolean getJogo(Jogo jogo){
		for (Jogo j: jogos) {
			if(j.getNome().equals(jogo.getNome())) return true;
		}
		return  false;
	}

	public Jogo consultaPorNome(String nome) {
		for (Jogo j: jogos) {
			if (j.getNome().equals(nome)) {
				return j;
			}
		}
		return null;
	}

	public ArrayList<Jogo> consultaPorAno(int ano) {
		ArrayList<Jogo> jogosAno = new ArrayList<Jogo>();
		for (Jogo j: jogos) {
			if (j.getAno() == ano) {
				jogosAno.add(j);
			}
		}
		return jogosAno;
	}

	public ArrayList<Jogo> consultaPorCategoria(String categoria) {
		ArrayList<Jogo> jogosCategoria = new ArrayList<Jogo>();
		for (Jogo j: jogos) {
			if(j instanceof JogoEletronico){
				if (((JogoEletronico) j).getCategoria().getNome().equals(categoria)) {
					jogosCategoria.add(j);
				}
			}
		}
		return jogosCategoria;
	}

	public double somaPrecoFinal() {
		double soma = 0;
		for (Jogo j: jogos) {
			soma += j.calculaPrecoFinal();
		}
		return soma;
	}

	public JogoTabuleiro jogoDeTabuleiroComMaiorPreco() {
		JogoTabuleiro jogo = null;
		double maior = 0;
		for (Jogo j: jogos) {
			if(j instanceof JogoTabuleiro){
				if (j.calculaPrecoFinal() > maior) {
					maior = j.calculaPrecoFinal();
					jogo = (JogoTabuleiro) j;
				}
			}
		}
		return jogo;
	}

	public JogoTabuleiro getJogoMaisAntigo(){
		JogoTabuleiro jogo = null;
		int menor = 9999;
		for (Jogo j: jogos) {
			if(j instanceof JogoTabuleiro){
				if (j.getAno() < menor) {
					menor = j.getAno();
					jogo = (JogoTabuleiro) j;
				}
			}
		}
		return jogo;
	}

	@Override
	public String toString() {
		String s = "";
		for (Jogo j: jogos) {
			s += j.toString() + "\n";
		}
		return s;
	}

	private void setCurrent(){
		current = jogos.get(contador);
	}

	@Override
	public void reset() {
		contador = 0;
		setCurrent();
	}

	@Override
	public boolean hasNext() {
		if(jogos.indexOf(current) == jogos.size() - 1) return false;
		return true;
	}

	@Override
	public Object next() {
		if(hasNext()){
			contador++;
			setCurrent();
			return current;
		}
		return null;
	}
}
