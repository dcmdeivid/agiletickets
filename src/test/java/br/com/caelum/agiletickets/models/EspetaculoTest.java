package br.com.caelum.agiletickets.models;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;


public class EspetaculoTest {
	
	@Test
	public void deveCriarUmEspetaculoDiarioPara1Dia()
	{
		LocalTime horario = new LocalTime(10, 10);
		
		LocalDate inicioDoPeriodo = new LocalDate();
		LocalDate fimDoPeriodo = new LocalDate();
		
		Espetaculo espetaculo = new Espetaculo();
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicioDoPeriodo, fimDoPeriodo, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(new Sessao(inicioDoPeriodo.toDateTime(horario)), sessoes.get(0));	
	}
	
	
	
	

}
