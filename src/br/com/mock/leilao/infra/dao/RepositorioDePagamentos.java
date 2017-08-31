package br.com.mock.leilao.infra.dao;

import br.com.mock.leilao.dominio.Pagamento;

public interface RepositorioDePagamentos {
	
	void salva(Pagamento pagamento);

}
