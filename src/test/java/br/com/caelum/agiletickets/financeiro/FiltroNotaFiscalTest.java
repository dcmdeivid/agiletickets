package br.com.caelum.agiletickets.financeiro;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FiltroNotaFiscalTest {

	@Test
	public void deveRemoverTodasNFsMenoresQue2000()
	{
		NotaFiscalDAO dao = mock(NotaFiscalDAO.class);
		List<NotaFiscal> menoresQue2000 = Arrays.asList(new NotaFiscal(1000), new NotaFiscal(1500));
		when(dao.pegaTodas()).thenReturn(menoresQue2000);
		
		FiltroNotaFiscal filtroNotaFiscal = new FiltroNotaFiscal(dao);
	
		List<NotaFiscal> listaMaioresQue2000 = filtroNotaFiscal.filtra();
		
		assertEquals(0, listaMaioresQue2000.size());
	}
}
