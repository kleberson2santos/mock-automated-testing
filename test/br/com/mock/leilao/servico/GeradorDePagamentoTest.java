package br.com.mock.leilao.servico;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import br.com.mock.leilao.builder.CriadorDeLeilao;
import br.com.mock.leilao.dominio.Leilao;
import br.com.mock.leilao.dominio.Pagamento;
import br.com.mock.leilao.dominio.Usuario;
import br.com.mock.leilao.infra.dao.RepositorioDeLeiloes;
import br.com.mock.leilao.infra.dao.RepositorioDePagamentos;

public class GeradorDePagamentoTest {
	
	@Test
    public void deveGerarPagamentoParaUmLeilaoEncerrado() {

        RepositorioDeLeiloes leiloes = mock(RepositorioDeLeiloes.class);
        RepositorioDePagamentos pagamentos = mock(RepositorioDePagamentos.class);

        Leilao leilao = new CriadorDeLeilao()
            .para("Playstation")
            .lance(new Usuario("José da Silva"), 2000.0)
            .lance(new Usuario("Maria Pereira"), 2500.0)
            .constroi();

        when(leiloes.encerrados()).thenReturn(Arrays.asList(leilao));

        GeradorDePagamento gerador = 
            new GeradorDePagamento(leiloes, pagamentos, new Avaliador());
        gerador.gera();

        ArgumentCaptor<Pagamento> argumento = ArgumentCaptor.forClass(Pagamento.class);
        verify(pagamentos).salva(argumento.capture());
        Pagamento pagamentoGerado = argumento.getValue();
        assertEquals(2500.0, pagamentoGerado.getValor(), 0.00001);
    }

}
