package br.com.caelum.agiletickets.financeiro;

import java.util.ArrayList;
import java.util.List;

public class FiltroNotaFiscal {

	private NotaFiscalDAO dao;

	public FiltroNotaFiscal(NotaFiscalDAO dao) {
		this.dao = dao;
	}

	public List<NotaFiscal> filtra() {
		List<NotaFiscal> filtradas = new ArrayList<NotaFiscal>();
		List<NotaFiscal> todasNotaFiscal = this.dao.pegaTodas();
		
		for (NotaFiscal notaFiscal : todasNotaFiscal) {
			if(notaFiscal.getValor() > 2000)
			{
				filtradas.add(notaFiscal);
			}
		}
		return filtradas;
	}

}
