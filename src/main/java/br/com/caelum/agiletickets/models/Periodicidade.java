package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public enum Periodicidade implements GeradorDeSessao {
	DIARIA() {
		public List<Sessao> sessoes(Espetaculo espetaculo, LocalDate inicio, LocalDate fim,
				LocalTime horario) {
			List<Sessao> sessoes = new ArrayList<Sessao>();
			for (int i = 0; i <= Days.daysBetween(inicio, fim).getDays(); i++) {
				sessoes.add(new Sessao(espetaculo, inicio.plusDays(i).toDateTime(horario)));
			}
			return sessoes;
		}
	}
	, SEMANAL() {
		public List<Sessao> sessoes(Espetaculo espetaculo,LocalDate inicio, LocalDate fim,
				LocalTime horario) {
			List<Sessao> sessoes = new ArrayList<Sessao>();
			for (int i = 0; i <= Weeks.weeksBetween(inicio, fim).getWeeks(); i++) {
				sessoes.add(new Sessao(espetaculo, inicio.plusWeeks(i).toDateTime(horario)));
			}
			
			return sessoes;
		}
	};

	
}
