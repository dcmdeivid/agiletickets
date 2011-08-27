package br.com.caelum.agiletickets.models;

import java.util.List;

import junit.framework.Assert;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;


public class EspetaculoTest {
	
	private LocalTime horario;
	private LocalDate inicioDoPeriodo;
	private Espetaculo espetaculo;

	@Before
	public void setUp() {
		horario = new LocalTime(10, 10);
		inicioDoPeriodo = new LocalDate();
		espetaculo = new Espetaculo();
	}
	
	@Test
	public void deveCriarUmEspetaculoDiarioPara1Dia()
	{
		
		LocalDate fimDoPeriodo = new LocalDate();
		
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicioDoPeriodo, fimDoPeriodo, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());
		Assert.assertEquals(new Sessao(espetaculo, inicioDoPeriodo.toDateTime(horario)), sessoes.get(0));	
	}
	
	@Test
	public void deveCriarEspetaculosDiariosPara2Dias()
	{
		LocalDate fimDoPeriodo = new LocalDate().plusDays(1);
		
		List<Sessao> sessoes = espetaculo.criaSessoes(inicioDoPeriodo, fimDoPeriodo, horario, Periodicidade.DIARIA);
		
		Assert.assertEquals(2, sessoes.size());
		Assert.assertEquals(new Sessao(espetaculo, inicioDoPeriodo.toDateTime(horario)), sessoes.get(0));
		
		Assert.assertEquals(new Sessao(espetaculo, fimDoPeriodo.toDateTime(horario)), sessoes.get(1));
		
	}
	
}
