package br.com.caelum.agiletickets.models;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public interface GeradorDeSessao {
	public List<Sessao> sessoes(Espetaculo espetaculo, LocalDate inicio, LocalDate fim,
			LocalTime horario);
}
