package dados;

import java.text.DecimalFormat;

public class JogoEletronico extends Jogo {

	private String plataforma;

	private Categoria categoria;

	public JogoEletronico(String nome, int ano, double precoBase, String plataforma, Categoria categoria) {
		super(nome, ano, precoBase);
		this.plataforma = plataforma;
		this.categoria = categoria;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	@Override
	public double calculaPrecoFinal() {
		DecimalFormat formatador = new DecimalFormat("#.00");

		if(categoria == Categoria.SIM) {
			return Double.parseDouble(formatador.format(getPrecoBase() * 1.3).replace(",", "."));
		}else if(categoria == Categoria.STR) {
			return Double.parseDouble(formatador.format(getPrecoBase() * 1.7).replace(",", "."));
		}
			return Double.parseDouble(formatador.format(getPrecoBase() * 1.1).replace(",", "."));
	}

	@Override
	public String toString() {
		return getNome() + "," + getAno() + ",R$ " + getPrecoBase() + "," + plataforma + "," + categoria.getNome();
	}
}
