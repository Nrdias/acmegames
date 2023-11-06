package dados;

import java.text.DecimalFormat;

public class JogoTabuleiro extends Jogo {

	private int numeroPecas;

	public JogoTabuleiro(String nome, int ano, double precoBase, int numeroPecas) {
		super(nome, ano, precoBase);
		this.numeroPecas = numeroPecas;
	}

	@Override
	public double calculaPrecoFinal() {
		DecimalFormat formatador = new DecimalFormat("#.00");
		double precoFinal = getPrecoBase() + (getPrecoBase() * (0.01 * numeroPecas));
		return Double.parseDouble(formatador.format(precoFinal).replace(",", "."));
	}

	@Override
	public String toString() {
		return getNome() + "," + getAno() + ",R$ " + getPrecoBase() + "," + numeroPecas;
	}
}
