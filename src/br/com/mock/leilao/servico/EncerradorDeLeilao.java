package br.com.mock.leilao.servico;

import java.util.Calendar;
import java.util.List;

import br.com.mock.leilao.dominio.Leilao;
import br.com.mock.leilao.infra.dao.RepositorioDeLeiloes;

public class EncerradorDeLeilao {

	private int total = 0;
	private final RepositorioDeLeiloes dao;

	public EncerradorDeLeilao(RepositorioDeLeiloes dao) {
		this.dao = dao;
	}

	public void encerra() {
		List<Leilao> todosLeiloesCorrentes = dao.correntes();

		for (Leilao leilao : todosLeiloesCorrentes) {
			if (comecouSemanaPassada(leilao)) {
				leilao.encerra();
				total++;
				dao.atualiza(leilao);
			}
		}
	}

	private boolean comecouSemanaPassada(Leilao leilao) {
		return diasEntre(leilao.getData(), Calendar.getInstance()) >= 7;
	}

	private int diasEntre(Calendar inicio, Calendar fim) {
		Calendar data = (Calendar) inicio.clone();
		int diasNoIntervalo = 0;
		while (data.before(fim)) {
			data.add(Calendar.DAY_OF_MONTH, 1);
			diasNoIntervalo++;
		}

		return diasNoIntervalo;
	}

	public int getTotalEncerrados() {
		return total;
	}
}
