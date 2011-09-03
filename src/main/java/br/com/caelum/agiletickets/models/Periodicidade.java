package br.com.caelum.agiletickets.models;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Weeks;

public enum Periodicidade implements GeradorDeSessao {
	DIARIA() {
		public List<Sessao> sessoes(Espetaculo espetaculo, LocalDate inicio, LocalDate fim,
				LocalTime horario) {
			List<Sessao> sessoes = new ArrayList<Sessao>();
			for (int i = 0; i <= quantidadeDeSessoes(inicio, fim); i++) {
				sessoes.add(new Sessao(espetaculo, dataHora(inicio, horario, i)));
			}
			return sessoes;
		}

		private DateTime dataHora(LocalDate inicio, LocalTime horario, int i) {
			return inicio.plusDays(i).toDateTime(horario);
		}

		private int quantidadeDeSessoes(LocalDate inicio, LocalDate fim) {
			return Days.daysBetween(inicio, fim).getDays();
		}
	}
	, SEMANAL() {
		public List<Sessao> sessoes(Espetaculo espetaculo,LocalDate inicio, LocalDate fim,
				LocalTime horario) {
			List<Sessao> sessoes = new ArrayList<Sessao>();
			for (int i = 0; i <= quantidadeDeSessoes(inicio, fim); i++) {
				sessoes.add(new Sessao(espetaculo, dataHora(inicio, horario, i)));
			}
			
			return sessoes;
		}

		private DateTime dataHora(LocalDate inicio, LocalTime horario, int i) {
			return inicio.plusWeeks(i).toDateTime(horario);
		}

		private int quantidadeDeSessoes(LocalDate inicio, LocalDate fim) {
			return Weeks.weeksBetween(inicio, fim).getWeeks();
		}
	};

	
}
