package br.com.mock.leilao.infra.email;

import br.com.mock.leilao.dominio.Leilao;

public interface EnviadorDeEmail {
	 
	void envia(Leilao leilao);
}
