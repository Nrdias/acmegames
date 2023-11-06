package dados;

public enum Categoria {

	ACT("Acao"),

	STR("Estrategia"),

	SIM("Simulacao");

	private String nome;

	private Categoria(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public static Categoria getCategoria(String nome) {
		if(nome.equals("Acao")) {
			return Categoria.ACT;
		}else if(nome.equals("Estrategia")) {
			return Categoria.STR;
		}else if(nome.equals("Simulacao")) {
			return Categoria.SIM;
		}
		return null;
	}

}
